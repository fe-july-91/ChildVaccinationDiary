package team.project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.dto.child.UpdateChildRequestDto;
import team.project.dto.height.CreateHeightRequestDto;
import team.project.dto.height.HeightDto;
import team.project.dto.height.UpdateHeightRequestDto;
import team.project.mapper.ChildMapper;
import team.project.model.Child;
import team.project.model.User;
import team.project.repository.child.ChildRepository;
import team.project.service.ChildService;
import team.project.service.HeightService;

@RequiredArgsConstructor
@Service
public class ChildServiceImpl implements ChildService {
    private final ChildRepository childRepo;
    private final ChildMapper childMapper;
    private final HeightService heightService;

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

    @Override
    @Transactional
    public List<ChildDto> getAll(Pageable pageable) {
        return childRepo.findAll(pageable).stream()
                .map(childMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ChildDto getChildById(Long id) {
        return childRepo.findById(id)
                .map(childMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Child with id: %s not found", id)));
    }

    @Override
    @Transactional
    public ChildDto getChildByIdAndUserId(Long userId, Long childId) {
        return childRepo.findByIdAndUserId(childId, userId)
                .map(childMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Child with id = %s not found for this parent", childId)));
    }

    @Override
    @Transactional
    public ChildDto updateChildByIdAndUserId(Long userId, Long childId,
                                             UpdateChildRequestDto newRequestDto) {
        Child child = childRepo.findByIdAndUserId(childId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Child with id = %s not found for this parent", childId)));
        child = childMapper.updateFromDto(child, newRequestDto);
        return childMapper.toDto(childRepo.save(child));
    }

    @Override
    @Transactional
    public List<HeightDto> getAllHeightByChildId(Long userId, Long childId) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? heightService.getAllByChildId(childId) : List.of();
    }

    @Override
    public HeightDto saveHeight(Long userId, Long childId, CreateHeightRequestDto requestDto) {
        Child child = childRepo.findByIdAndUserId(childId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Child with id = %s not found for this parent", childId)));
        return heightService.save(child, requestDto);
    }

    @Override
    public HeightDto updateHeight(Long userId, Long childId, Long heightId,
                                  UpdateHeightRequestDto requestDto) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? heightService.update(childId, heightId, requestDto) : new HeightDto();
    }
}
