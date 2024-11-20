package team.project.service;

import java.util.List;
import team.project.dto.foot.CreateFootRequestDto;
import team.project.dto.foot.FootDto;
import team.project.dto.foot.UpdateFootRequestDto;
import team.project.model.Child;
import team.project.model.Foot;

public interface FootService {
    List<FootDto> getAllByChildId(Long childId);

    FootDto save(Child child, CreateFootRequestDto requestDto);

    FootDto update(Long childId, Long footId, UpdateFootRequestDto requestDto);

    void delete(Long childId, Long footId);

    List<FootDto> getAllByYearAndChildId(Long childId, int year);

    Foot createDefault(Child child);
}
