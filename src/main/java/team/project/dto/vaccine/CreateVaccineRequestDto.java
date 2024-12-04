package team.project.dto.vaccine;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import org.checkerframework.checker.index.qual.Positive;

public record CreateVaccineRequestDto(
        @NotBlank String type,
        @NotNull @Positive Byte orderNumber,
        @NotBlank String date) implements Serializable {
}
