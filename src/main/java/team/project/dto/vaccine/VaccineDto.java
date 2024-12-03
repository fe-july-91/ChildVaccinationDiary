package team.project.dto.vaccine;

import java.io.Serializable;
import java.time.LocalDate;
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
    private Long typeId;
    private String typeDescription;
    private byte orderNumber;
    private LocalDate date;
    private Long childId;
}
