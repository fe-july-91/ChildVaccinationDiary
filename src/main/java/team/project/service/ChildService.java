package team.project.service;

import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.model.User;

public interface ChildService {
    ChildDto save(User user, CreateChildRequestDto requestDto);
}
