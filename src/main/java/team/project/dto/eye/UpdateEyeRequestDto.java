package team.project.dto.eye;

import java.io.Serializable;

public record UpdateEyeRequestDto(float leftEye,
                                  float rightEye) implements Serializable {
}
