package team.project.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.dto.child.UpdateChildRequestDto;
import team.project.dto.height.CreateHeightRequestDto;
import team.project.dto.height.HeightDto;
import team.project.dto.height.UpdateHeightRequestDto;
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
}
