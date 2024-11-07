package team.project.dto.child;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;

public record CreateChildRequestDto(@NotBlank String name,
                                    @NotBlank String surname,
                                    @PastOrPresent @NotNull LocalDate birth,
                                    @NotBlank String genderName,
                                    String image) implements Serializable {
}
