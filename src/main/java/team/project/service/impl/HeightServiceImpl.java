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
        return result.stream()
                .map(heightMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public HeightDto save(Child child, CreateHeightRequestDto requestDto) {
        Height height = heightMapper.toModel(requestDto);
        height.setChild(child);
        return heightMapper.toDto(heightRepo.save(height));
    }

    @Override
    @Transactional
    public HeightDto update(Long childId, Long heightId, UpdateHeightRequestDto requestDto) {
        Height heightFromDB = getHeightByIdAndChildId(heightId, childId);
        return heightMapper.toDto(heightRepo.save(
                heightMapper.updateFromDto(heightFromDB, requestDto)));
    }

    @Override
    @Transactional
    public String delete(Long childId, Long heightId) {
        Height heightFromDB = getHeightByIdAndChildId(heightId, childId);
        heightRepo.delete(heightFromDB);
        return (!heightRepo.existsByIdAndChildId(heightId, childId))
                ? String.format("Значення зросту з id = %s було успішно видалено.", heightId)
                : String.format("Значення зросту з id = %s НЕ було видалено.", heightId);
    }

    @Override
    public List<HeightDto> getAllByYearAndChildId(Long childId, int year) {
        List<Height> result = heightRepo.findAllByYearAndChildId(year, childId);
        return result.stream()
                .map(heightMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public Height createDefault(Child child, int currentYear, String currentMonth) {
        Height height = new Height();
        height.setChild(child);
        height.setYear(currentYear);
        height.setMonth(currentMonth);
        height.setValue((short) 40);
        return heightRepo.save(height);
    }

    private Height getHeightByIdAndChildId(Long heightId, Long childId) {
        return heightRepo.findByIdAndChildId(heightId, childId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Значення зросту з id = %s не знайдено для дитини з id = %s.",
                                heightId, childId)));
    }
}
