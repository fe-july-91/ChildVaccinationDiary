package team.project.service.impl;

import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.exception.EntityAlreadyExistsException;
import team.project.exception.RegistrationException;
import team.project.mapper.UserMapper;
import team.project.model.Role;
import team.project.model.RoleName;
import team.project.model.User;
import team.project.repository.role.RoleRepository;
import team.project.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.project.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findUserByEmail(requestDto.getEmail()).isPresent()) {
            throw new EntityAlreadyExistsException("User with email: "
                    + requestDto.getEmail() + " is register");
        }
        if (!requestDto.getPassword().equals(requestDto.getRepeatPassword())) {
            throw new RegistrationException("Invalid passwords");
        }
        User newUser = new User();
        newUser.setEmail(requestDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        newUser.setLastName(requestDto.getLastName());
        newUser.setFirstName(requestDto.getFirstName());
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(RoleName.PARENT));
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return userMapper.toDto(newUser);
    }
}
