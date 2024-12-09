package team.project.dto.vaccine;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

public record UpdateVaccineRequestDto(
        @NotBlank String type,
        @NotBlank String date) implements Serializable {
}
