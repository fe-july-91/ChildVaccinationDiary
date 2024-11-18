package team.project.service;

import java.util.List;
import team.project.dto.weight.CreateWeightRequestDto;
import team.project.dto.weight.UpdateWeightRequestDto;
import team.project.dto.weight.WeightDto;
import team.project.model.Child;

public interface WeightService {
    List<WeightDto> getAllByChildId(Long childId);

    WeightDto save(Child child, CreateWeightRequestDto requestDto);

    WeightDto update(Long childId, Long weightId, UpdateWeightRequestDto requestDto);

    void delete(Long childId, Long weightId);

    List<WeightDto> getAllByYearAndChildId(Long childId, int year);
}
