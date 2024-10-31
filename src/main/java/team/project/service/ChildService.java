package team.project.service;

import java.util.List;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.model.User;

public interface ChildService {
    ChildDto save(User user, CreateChildRequestDto requestDto);

    List<ChildDto> getChildsByUser(Long userId);
}
