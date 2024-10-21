package team.project.dto.user;

import team.project.validation.EmailValues;
import team.project.validation.PasswordValues;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    @NotNull
    @EmailValues
    private String email;
    @PasswordValues
    private String password;
    @PasswordValues
    private String repeatPassword;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}
