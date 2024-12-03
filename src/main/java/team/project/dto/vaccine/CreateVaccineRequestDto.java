package team.project.dto.vaccine;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;

public record CreateVaccineRequestDto(
        @NotBlank String typeDescription,
        @PastOrPresent LocalDate date) implements Serializable {
}
