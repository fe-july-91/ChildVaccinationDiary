package team.project.service;

import java.util.List;
import team.project.dto.weight.WeightDto;

public interface WeightService {
    List<WeightDto> getAllByChildId(Long childId);
}
