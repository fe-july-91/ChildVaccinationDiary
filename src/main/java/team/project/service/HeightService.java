package team.project.service;

import java.util.List;
import team.project.dto.height.CreateHeightRequestDto;
import team.project.dto.height.HeightDto;
import team.project.dto.height.UpdateHeightRequestDto;
import team.project.model.Child;

public interface HeightService {
    List<HeightDto> getAllByChildId(Long childId);

    HeightDto save(Child child, CreateHeightRequestDto requestDto);

    HeightDto update(Long childId, Long heightId, UpdateHeightRequestDto requestDto);

    String delete(Long childId, Long heightId);

    List<HeightDto> getAllByYearAndChildId(Long childId, int year);
}
