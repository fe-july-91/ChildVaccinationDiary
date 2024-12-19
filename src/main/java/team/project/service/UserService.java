package team.project.service;

import org.springframework.http.ResponseEntity;
import team.project.dto.user.UserRecoveryRequestDto;
import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    ResponseEntity<String> recoveryPassword(UserRecoveryRequestDto requestDto);

    ResponseEntity<String> resetPassword(String token, String newPassword);

    void updatePassword(String email, String newPassword);
}
