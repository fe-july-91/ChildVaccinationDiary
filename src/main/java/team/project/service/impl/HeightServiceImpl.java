package team.project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.dto.height.CreateHeightRequestDto;
import team.project.dto.height.HeightDto;
import team.project.dto.height.UpdateHeightRequestDto;
import team.project.mapper.HeightMapper;
import team.project.model.Child;
import team.project.model.Height;
import team.project.repository.height.HeightRepository;
import team.project.service.HeightService;

@RequiredArgsConstructor
@Service
public class HeightServiceImpl implements HeightService {
    private final HeightRepository heightRepo;
    private final HeightMapper heightMapper;

    @Override
    public List<HeightDto> getAllByChildId(Long childId) {
        List<Height> result = heightRepo.findAllByChildId(childId);
        return result.stream().map(heightMapper::toDto).toList();
    }

    @Override
    public HeightDto save(Child child, CreateHeightRequestDto requestDto) {
        Height height = heightMapper.toModel(requestDto);
        height.setChild(child);
        return heightMapper.toDto(heightRepo.save(height));
    }

    @Override
    @Transactional
    public HeightDto update(Long childId, Long heightId, UpdateHeightRequestDto requestDto) {
        Height heightFromDB = heightRepo.findByIdAndChildId(heightId, childId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Height with id = %s not found for this childId = %s",
                                heightId, childId)));
        return heightMapper.toDto(heightRepo.save(
                heightMapper.updateFromDto(heightFromDB, requestDto)));
    }
}
