package team.project.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.dto.child.UpdateChildRequestDto;
import team.project.dto.foot.CreateFootRequestDto;
import team.project.dto.foot.FootDto;
import team.project.dto.foot.UpdateFootRequestDto;
import team.project.dto.height.CreateHeightRequestDto;
import team.project.dto.height.HeightDto;
import team.project.dto.height.UpdateHeightRequestDto;
import team.project.dto.weight.CreateWeightRequestDto;
import team.project.dto.weight.UpdateWeightRequestDto;
import team.project.dto.weight.WeightDto;
import team.project.model.User;

public interface ChildService {
    ChildDto save(User user, CreateChildRequestDto requestDto);

    List<ChildDto> getChildsByUser(Long userId);

    List<ChildDto> getAll(Pageable pageable);

    ChildDto getChildById(Long id);

    ChildDto getChildByIdAndUserId(Long userId, Long childId);

    ChildDto updateChildByIdAndUserId(Long userId, Long childId,
                                      UpdateChildRequestDto newRequestDto);

    List<HeightDto> getAllHeightByChildId(Long userId, Long childId);

    HeightDto saveHeight(Long userId, Long childId, CreateHeightRequestDto requestDto);

    HeightDto updateHeight(Long userId, Long childId, Long heightId,
                           UpdateHeightRequestDto requestDto);

    String deleteHeight(Long userId, Long childId, Long heightId);

    List<HeightDto> getAllHeightByYearAndChildId(Long userId, Long childId, int year);

    List<WeightDto> getAllWeightByChildId(Long userId, Long childId);

    WeightDto saveWeight(Long userId, Long childId, CreateWeightRequestDto requestDto);

    WeightDto updateWeight(Long userId, Long childId, Long weightId,
                           UpdateWeightRequestDto requestDto);

    void deleteWeight(Long userId, Long childId, Long weightId);

    List<WeightDto> getAllWeightByYearAndChildId(Long userId, Long childId, int year);

    void deleteChildByIdAndUserId(Long userId, Long childId);

    List<FootDto> getAllFootByChildId(Long userId, Long childId);

    FootDto saveFoot(Long userId, Long childId, CreateFootRequestDto requestDto);

    FootDto updateFoot(Long userId, Long childId, Long footId, UpdateFootRequestDto requestDto);

    void deleteFoot(Long userId, Long childId, Long footId);

    List<FootDto> getAllFootByYearAndChildId(Long userId, Long childId, int year);
}
