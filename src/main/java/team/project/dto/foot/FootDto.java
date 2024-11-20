package team.project.dto.foot;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FootDto implements Serializable {
    private Long id;
    private int year;
    private String month;
    private short value;
}
