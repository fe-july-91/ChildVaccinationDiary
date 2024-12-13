package team.project.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecoveryRequestDto(@NotBlank @Email String email) {
}
