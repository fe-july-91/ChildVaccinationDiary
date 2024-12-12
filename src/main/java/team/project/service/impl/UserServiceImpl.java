package team.project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.exception.RegistrationException;
import team.project.mapper.UserMapper;
import team.project.model.Role;
import team.project.model.RoleName;
import team.project.model.User;
import team.project.repository.user.RoleRepository;
import team.project.repository.user.UserRepository;
import team.project.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final String DEFAULT_ROLE = "PARENT";
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepo;

    @Override
    @Transactional
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepo.existsByEmail(requestDto.email())) {
            throw new RegistrationException("This user can't be registered");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(generateDefaultSetRoles());
        User savedUser = userRepo.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public ResponseEntity<String> recoveryPassword(String email) {

        return (!userRepo.existsByEmail(email))
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with this email does not exist.")
                : ResponseEntity.ok("Password reset link has been sent to your email.");
    }

    private Set<Role> generateDefaultSetRoles() {
        Role roleFromDB = roleRepo.findByName(RoleName.getByType(DEFAULT_ROLE))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Can't find %s in table roles", DEFAULT_ROLE)));
        Set<Role> roles = new HashSet<>();
        roles.add(roleFromDB);
        return roles;
    }
}
