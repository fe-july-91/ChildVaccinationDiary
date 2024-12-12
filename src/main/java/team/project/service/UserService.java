package team.project.service;

import org.springframework.http.ResponseEntity;
import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    ResponseEntity<String> recoveryPassword(String email);
}
