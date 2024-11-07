package team.project.dto.child;

import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;

public record UpdateChildRequestDto(String name,
                                    String surname,
                                    @PastOrPresent LocalDate birth,
                                    String genderName,
                                    String image) implements Serializable {
}
