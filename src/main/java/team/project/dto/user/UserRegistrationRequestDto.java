package team.project.dto.user;

import lombok.Getter;
import lombok.Setter;
import team.project.validation.EmailValues;
import team.project.validation.PasswordValues;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
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
