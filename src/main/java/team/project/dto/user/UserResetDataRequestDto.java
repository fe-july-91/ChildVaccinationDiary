package team.project.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserResetDataRequestDto(
        @NotBlank String name,
        @NotBlank @Email String email) {}
