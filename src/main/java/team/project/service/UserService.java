package team.project.service;

import team.project.dto.user.UserRecoveryRequestDto;
import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResetDataRequestDto;
import team.project.dto.user.UserResetPasswordRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.exception.RegistrationCustomException;
import team.project.model.TokenConfirmation;
import team.project.model.User;

public interface UserService {

    UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationCustomException;

    String recoveryPassword(UserRecoveryRequestDto requestDto);

    void updatePassword(String email, String newPassword);

    String resetPassword(User user, UserResetPasswordRequestDto requestDto);

    UserResponseDto resetData(User user, UserResetDataRequestDto requestDto);

    String verifyEmail(String token);

    User getById(Long id);

    void sendEmailVerification(TokenConfirmation token, String urlHttp);

    void deleteAccountById(Long id);
}
