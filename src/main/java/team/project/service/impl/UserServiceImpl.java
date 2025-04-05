package team.project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.project.dto.user.UserRecoveryRequestDto;
import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResetDataRequestDto;
import team.project.dto.user.UserResetPasswordRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.exception.EntityNotFoundCustomException;
import team.project.exception.RegistrationCustomException;
import team.project.mapper.UserMapper;
import team.project.model.Role;
import team.project.model.RoleName;
import team.project.model.TokenConfirmation;
import team.project.model.User;
import team.project.password.PasswordGenerator;
import team.project.repository.RoleRepository;
import team.project.repository.UserRepository;
import team.project.service.EmailService;
import team.project.service.TokenConfirmationService;
import team.project.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final String DEFAULT_ROLE = "PARENT";
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepo;
    private final EmailService emailService;
    private final PasswordGenerator passwordGenerator;
    private final TokenConfirmationService tokenConfirmationService;

    @Override
    @Transactional
    public UserResponseDto register(UserRegistrationRequestDto requestDto, String urlHttp)
            throws RegistrationCustomException {
        if (userRepo.existsByEmail(requestDto.email())) {
            throw new RegistrationCustomException(
                    "Користувача із такою електронною поштою вже зареєстровано");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(generateDefaultSetRoles());
        User savedUser = userRepo.save(user);
        TokenConfirmation tokenConfirmation = tokenConfirmationService.createToken(savedUser);
        emailService.sendTokenConformation(savedUser,
                generteUrlWithToken(urlHttp, tokenConfirmation.getToken()));
        return userMapper.toResponseDto(savedUser);
    }

    private String generteUrlWithToken(String urlHttp, String token) {
        return urlHttp + "/auth/verify-email?token=" + token;
    }

    @Override
    @Transactional
    public String recoveryPassword(UserRecoveryRequestDto requestDto) {
        if (!userRepo.existsByEmail(requestDto.email())) {
            throw new EntityNotFoundCustomException(
                    "Користувач із такою електронною поштою не зареєстрований.");
        }
        String newPassword = passwordGenerator.generatePassword(8);
        updatePassword(requestDto.email(), newPassword);
        emailService.sendPasswordReset(requestDto.email(), newPassword);
        return "Новий пароль було відправлено на вашу електронну пошту.";
    }

    @Override
    @Transactional
    public void updatePassword(String email, String newPassword) {
        User user = (User) userRepo.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Користувача з електронною поштою %s не знайдено.", email)));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }

    @Override
    @Transactional
    public String resetPassword(User user, UserResetPasswordRequestDto requestDto) {
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        userRepo.save(user);
        return "Пароль було успішно скинуто.";
    }

    @Override
    @Transactional
    public UserResponseDto resetData(User user, UserResetDataRequestDto requestDto) {
        return userMapper.toResponseDto(userRepo.save(userMapper.updateFromDto(user, requestDto)));
    }

    @Transactional
    @Override
    public String verifyEmail(String token) {
        TokenConfirmation tokenConfirmation = tokenConfirmationService.getByToken(token);
        User user = tokenConfirmation.getUser();
        if (user.isEnabled()) {
            return "Цей обліковий запис уже підтверджено.";
        } else if (LocalDateTime.now().isBefore(tokenConfirmation.getExpireDate())) {
            user.setVerified(true);
            userRepo.save(user);
            return "Ваш обліковий запис було успішно підтверджено!";
        }
        return "Пройшло занадто багато часу - посилання вже не дійсне";
    }

    private Set<Role> generateDefaultSetRoles() {
        Role roleFromDB = roleRepo.findByName(RoleName.getByType(DEFAULT_ROLE))
                .orElseThrow(() -> new EntityNotFoundCustomException(
                        String.format("Не вдається знайти %s у таблиці ролей доступу",
                                DEFAULT_ROLE)));
        Set<Role> roles = new HashSet<>();
        roles.add(roleFromDB);
        return roles;
    }
}
