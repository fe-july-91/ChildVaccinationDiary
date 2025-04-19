package team.project.dto.suppport;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

public record CreateEmailRequestDto(@NotBlank String name,
                                    @NotBlank @Email String email,
                                    @NotBlank String message) implements Serializable {}
