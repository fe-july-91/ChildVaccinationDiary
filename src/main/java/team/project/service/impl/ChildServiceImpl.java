package team.project.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.mapper.ChildMapper;
import team.project.model.Child;
import team.project.model.User;
import team.project.repository.child.ChildRepository;
import team.project.service.ChildService;

@RequiredArgsConstructor
@Service
public class ChildServiceImpl implements ChildService {
    private final ChildRepository childRepo;
    private final ChildMapper childMapper;

    @Override
    public ChildDto save(User user, CreateChildRequestDto requestDto) {
        Child child = childMapper.toModel(requestDto);
        child.setUser(user);
        return childMapper.toDto(childRepo.save(child));
    }

    @Override
    @Transactional
    public List<ChildDto> getChildsByUser(Long userId) {
        return childRepo.findAllByUserId(userId).stream()
                .map(childMapper::toDto).toList();
    }
}
