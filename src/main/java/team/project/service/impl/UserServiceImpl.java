package team.project.service.impl;

import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.exception.EntityNotFoundException;
import team.project.exception.RegistrationException;
import team.project.mapper.UserMapper;
import team.project.model.Role;
import team.project.model.RoleName;
import team.project.model.User;
import team.project.repository.role.RoleRepository;
import team.project.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.project.service.UserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository ;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with this email already exists");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role role = roleRepository.findByName(RoleName.PARENT)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        user.setRoles(Set.of(role));
        User savedUser  = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
