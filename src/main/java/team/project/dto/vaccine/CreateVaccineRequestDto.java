package team.project.dto.vaccine;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

public record CreateVaccineRequestDto(
        @NotBlank String type,
        @NotBlank String date) implements Serializable {
}
