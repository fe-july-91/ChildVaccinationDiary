package team.project.dto.user;

import lombok.Getter;
import lombok.Setter;
import team.project.validation.EmailValues;
import team.project.validation.PasswordValues;

@Getter
@Setter
public class UserLoginRequestDto {
    @EmailValues
    private String email;
    @PasswordValues
    private String password;
}
