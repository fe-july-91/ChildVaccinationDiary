package team.project.dto.height;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;

public record CreateHeightRequestDto(
        @NotBlank String year,
        @NotBlank String month,
        @Positive @NotNull short value) implements Serializable {
}
