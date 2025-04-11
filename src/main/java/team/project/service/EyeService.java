package team.project.service;

import team.project.dto.eye.EyeDto;
import team.project.dto.eye.UpdateEyeRequestDto;
import team.project.model.Child;

public interface EyeService {
    void createDefault(Child child);

    EyeDto updateById(Long childId, UpdateEyeRequestDto requestDto);

    EyeDto getEyeByChildId(Long childId);
}
