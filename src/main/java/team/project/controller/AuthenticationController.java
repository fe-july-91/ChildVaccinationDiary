package team.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.project.dto.user.UserLoginRequestDto;
import team.project.dto.user.UserLoginResponseDto;
import team.project.dto.user.UserRecoveryRequestDto;
import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.exception.RegistrationCustomException;
import team.project.model.TokenConfirmation;
import team.project.model.User;
import team.project.security.AuthenticationService;
import team.project.service.TokenConfirmationService;
import team.project.service.UserService;

@Tag(name = "Authentication manager", description = "Endpoints for managing users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Validated
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final TokenConfirmationService tokenService;

    @PostMapping("/registration")
    @Operation(summary = "Create a new user",
            description = "Register a new user and send verification email")
    public String register(@RequestBody @Valid UserRegistrationRequestDto requestDto,
                           final HttpServletRequest requestHttp)
            throws RegistrationCustomException {
        String urlHttp = generateUrl(requestHttp);
        UserResponseDto responseDto = userService.register(requestDto);
        User user = userService.getById(responseDto.id());
        TokenConfirmation token = tokenService.createToken(user);
        userService.sendEmailVerification(token, urlHttp);
        return "Для завершення реєстрації перевірте ваш імейл та пройдіть веріфікацію";
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user",
            description = "Login a user that exists in the database")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/forgot-password")
    @Operation(summary = "Recovery password",
            description = "Send an email with a password reset link to the provided email address")
    public String forgotPassword(
            @RequestBody @Valid UserRecoveryRequestDto requestDto) {
        return userService.recoveryPassword(requestDto);
    }

    @GetMapping("/verify-email")
    @Operation(summary = "Verification an email",
            description = "Verify an email by token")
    public String verifyEmail(@RequestParam("token") String token) {
        return userService.verifyEmail(token);
    }

    public String generateUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
