package team.project.dto.child;

import lombok.Getter;
import lombok.Setter;
import team.project.model.Gender;
import java.time.LocalDate;

@Getter
@Setter
public class ChildSearchParametersDto {
    private String[] names;
    private String[] surnames;
    private Long[] userIds;
    private LocalDate[] birthDates;
    private Gender[] genders;
    private String[] images;
}
