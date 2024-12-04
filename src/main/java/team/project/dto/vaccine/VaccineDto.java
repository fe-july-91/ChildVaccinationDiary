package team.project.dto.vaccine;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class VaccineDto implements Serializable {
    private Long id;
    private String type;
    private byte orderNumber;
    private String date;
}
