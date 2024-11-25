package team.project.dto.eye;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class EyeDto implements Serializable {
    private Long id;
    private Long childId;
    private float leftEye;
    private float rightEye;
}
