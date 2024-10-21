package team.project.dto.child;

import team.project.model.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ChildSearchParametersDto {
    private String[] names;
    private String[] surnames;
    private Long[] userIds;
    private LocalDate[] birthDates;
    private Gender[] genders;
    private String[] images;
}
