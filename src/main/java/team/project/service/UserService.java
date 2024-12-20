package team.project.service;

import org.springframework.http.ResponseEntity;
import team.project.dto.user.UserRecoveryRequestDto;
import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResetPasswordRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.exception.RegistrationException;
import team.project.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    ResponseEntity<String> recoveryPassword(UserRecoveryRequestDto requestDto);

    void updatePassword(String email, String newPassword);

    ResponseEntity<String> resetPassword(User user, UserResetPasswordRequestDto requestDto);
}
