package team.project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.dto.journal.CreateJournalRequestDto;
import team.project.dto.journal.JournalDto;
import team.project.dto.journal.UpdateJournalRequestDto;
import team.project.mapper.JournalMapper;
import team.project.model.Child;
import team.project.model.Weight;
import team.project.repository.WeightRepository;
import team.project.service.WeightService;

@RequiredArgsConstructor
@Service
public class WeightServiceImpl implements WeightService {
    private final WeightRepository weightRepo;
    private final JournalMapper journalMapper;

    @Override
    public List<JournalDto> getAllByChildId(Long childId) {
        List<Weight> result = weightRepo.findAllByChildId(childId);
        return result.stream()
                .map(journalMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public JournalDto save(Child child, CreateJournalRequestDto requestDto) {
        Weight weight = journalMapper.toModel(Weight.class, requestDto);
        weight.setChild(child);
        return journalMapper.toDto(weightRepo.save(weight));
    }

    @Override
    @Transactional
    public JournalDto update(Long childId, Long weightId, UpdateJournalRequestDto requestDto) {
        Weight weightFromDB = getWeightByIdAndChildId(weightId, childId);
        return journalMapper.toDto(weightRepo.save(
                journalMapper.updateFromDto(weightFromDB, requestDto)));
    }

    @Override
    @Transactional
    public void delete(Long childId, Long weightId) {
        weightRepo.delete(getWeightByIdAndChildId(weightId, childId));
    }

    @Override
    public List<JournalDto> getAllByYearAndChildId(Long childId, int year) {
        List<Weight> result = weightRepo.findAllByYearAndChildId(year, childId);
        return result.stream()
                .map(journalMapper::toDto)
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
