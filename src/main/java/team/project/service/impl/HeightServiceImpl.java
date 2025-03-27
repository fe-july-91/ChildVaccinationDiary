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
import team.project.model.Height;
import team.project.repository.HeightRepository;
import team.project.service.HeightService;

@RequiredArgsConstructor
@Service
public class HeightServiceImpl implements HeightService {
    private final HeightRepository heightRepo;
    private final JournalMapper journalMapper;

    @Override
    public List<JournalDto> getAllByChildId(Long childId) {
        List<Height> result = heightRepo.findAllByChildId(childId);
        return result.stream()
                .map(journalMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public JournalDto save(Child child, CreateJournalRequestDto requestDto) {
        Height height = journalMapper.toModel(Height.class, requestDto);
        height.setChild(child);
        return journalMapper.toDto(heightRepo.save(height));
    }

    @Override
    @Transactional
    public JournalDto update(Long childId, Long heightId, UpdateJournalRequestDto requestDto) {
        Height heightFromDB = getHeightByIdAndChildId(heightId, childId);
        return journalMapper.toDto(heightRepo.save(
                journalMapper.updateFromDto(heightFromDB, requestDto)));
    }

    @Override
    @Transactional
    public void delete(Long childId, Long heightId) {
        heightRepo.delete(getHeightByIdAndChildId(heightId, childId));
    }

    @Override
    public List<JournalDto> getAllByYearAndChildId(Long childId, int year) {
        List<Height> result = heightRepo.findAllByYearAndChildId(year, childId);
        return result.stream()
                .map(journalMapper::toDto)
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
