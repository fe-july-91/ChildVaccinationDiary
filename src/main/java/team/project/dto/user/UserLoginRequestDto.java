package team.project.dto.user;

import team.project.validation.EmailValues;
import team.project.validation.PasswordValues;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @EmailValues
    private String email;
    @PasswordValues
    private String password;
}
