package team.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.project.dto.user.UserResetDataRequestDto;
import team.project.dto.user.UserResetPasswordRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.model.User;
import team.project.service.UserService;

@Tag(name = "Users manager", description = "Managing user's information'")
@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
@Validated
public class UserController {
    private final UserService userService;

    @PutMapping("/reset-password")
    @Operation(summary = "Reset user's password",
            description = "Setting a new password by user")
    public String resetPassword(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UserResetPasswordRequestDto newRequestDto) {
        return userService.resetPassword(user, newRequestDto);
    }

    @PutMapping("/reset-data")
    @Operation(summary = "Reset user's data",
            description = "Setting a new name/email of user")
    public UserResponseDto resetData(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UserResetDataRequestDto newRequestDto) {
        return userService.resetData(user, newRequestDto);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete user's account",
            description = "Delete user's account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User's account not found")})
    public ResponseEntity<Void> deleteAccount(@AuthenticationPrincipal User user) {
        userService.deleteAccountById(user.getId());
        return ResponseEntity.noContent().build();
    }
}
