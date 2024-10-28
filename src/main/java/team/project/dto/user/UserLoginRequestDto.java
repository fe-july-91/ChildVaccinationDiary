package team.project.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import team.project.validation.EmailValues;
import team.project.validation.PasswordValues;

public record UserLoginRequestDto(
        @NotEmpty
        @Size(min = 8, max = 20)
        @EmailValues
        String email,
        @NotEmpty
        @Size(min = 8, max = 20)
        @PasswordValues
        String password
) {
}
