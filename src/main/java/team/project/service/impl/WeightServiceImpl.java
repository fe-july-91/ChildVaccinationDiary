package team.project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.dto.weight.CreateWeightRequestDto;
import team.project.dto.weight.UpdateWeightRequestDto;
import team.project.dto.weight.WeightDto;
import team.project.mapper.WeightMapper;
import team.project.model.Child;
import team.project.model.Weight;
import team.project.repository.weight.WeightRepository;
import team.project.service.WeightService;

@RequiredArgsConstructor
@Service
public class WeightServiceImpl implements WeightService {
    private final WeightRepository weightRepo;
    private final WeightMapper weightMapper;

    @Override
    public List<WeightDto> getAllByChildId(Long childId) {
        List<Weight> result = weightRepo.findAllByChildId(childId);
        return result.stream()
                .map(weightMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public WeightDto save(Child child, CreateWeightRequestDto requestDto) {
        Weight weight = weightMapper.toModel(requestDto);
        weight.setChild(child);
        return weightMapper.toDto(weightRepo.save(weight));
    }

    @Override
    @Transactional
    public WeightDto update(Long childId, Long weightId, UpdateWeightRequestDto requestDto) {
        Weight weightFromDB = getWeightByIdAndChildId(weightId, childId);
        return weightMapper.toDto(weightRepo.save(
                weightMapper.updateFromDto(weightFromDB, requestDto)));
    }

    @Override
    @Transactional
    public void delete(Long childId, Long weightId) {
        weightRepo.delete(getWeightByIdAndChildId(weightId, childId));
    }

    @Override
    public List<WeightDto> getAllByYearAndChildId(Long childId, int year) {
        List<Weight> result = weightRepo.findAllByYearAndChildId(year, childId);
        return result.stream()
                .map(weightMapper::toDto)
                .toList();
    }

    @Override
    public Weight createDefault(Child child, int currentYear, String currentMonth) {
        Weight weight = new Weight();
        weight.setChild(child);
        weight.setYear(currentYear);
        weight.setMonth(currentMonth);
        weight.setValue((short) 3);
        return weightRepo.save(weight);
    }

    private Weight getWeightByIdAndChildId(Long weightId, Long childId) {
        return weightRepo.findByIdAndChildId(weightId, childId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Значення ваги з id = %s не знайдено для дитини з id = %s.",
                                weightId, childId)));
    }
}
