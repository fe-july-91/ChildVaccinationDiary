package team.project.service;

import java.util.List;
import team.project.dto.weight.CreateWeightRequestDto;
import team.project.dto.weight.WeightDto;
import team.project.model.Child;

public interface WeightService {
    List<WeightDto> getAllByChildId(Long childId);

    WeightDto save(Child child, CreateWeightRequestDto requestDto);
}
