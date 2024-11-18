package team.project.dto.child;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

public record UpdateChildRequestDto(@NotBlank String name,
                                    @NotBlank String surname,
                                    @NotBlank String birth,
                                    @NotBlank String genderName,
                                    String image) implements Serializable {
}
