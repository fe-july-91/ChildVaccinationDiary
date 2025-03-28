package team.project.dto.journal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JournalDto {
    private Long id;
    private int year;
    private String month;
    private short value;
}
