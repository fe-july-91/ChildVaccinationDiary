package team.project.dto.child;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChildDto implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private LocalDate birth;
    private String genderName;
    private String image;
    private Long userId;
    private String userEmail;
    private String userName;
}
