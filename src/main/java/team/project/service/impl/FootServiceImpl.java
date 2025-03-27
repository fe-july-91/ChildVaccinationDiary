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
import team.project.model.Foot;
import team.project.repository.FootRepository;
import team.project.service.FootService;

@RequiredArgsConstructor
@Service
public class FootServiceImpl implements FootService {
    private final FootRepository footRepo;
    private final JournalMapper journalMapper;

    @Override
    public List<JournalDto> getAllByChildId(Long childId) {
        List<Foot> result = footRepo.findAllByChildId(childId);
        return result.stream()
                .map(journalMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public JournalDto save(Child child, CreateJournalRequestDto requestDto) {
        Foot foot = journalMapper.toModel(Foot.class, requestDto);
        foot.setChild(child);
        return journalMapper.toDto(footRepo.save(foot));
    }

    @Override
    @Transactional
    public JournalDto update(Long childId, Long footId, UpdateJournalRequestDto requestDto) {
        return journalMapper.toDto(footRepo.save(
                journalMapper.updateFromDto(getFootOfChild(footId, childId), requestDto)));
    }

    @Override
    @Transactional
    public void delete(Long childId, Long footId) {
        footRepo.delete(getFootOfChild(footId, childId));
    }

    @Override
    public List<JournalDto> getAllByYearAndChildId(Long childId, int year) {
        List<Foot> result = footRepo.findAllByYearAndChildId(year, childId);
        return result.stream()
                .map(journalMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public Foot createDefault(Child child, int currentYear, String currentMonth) {
        Foot foot = new Foot();
        foot.setChild(child);
        foot.setYear(currentYear);
        foot.setMonth(currentMonth);
        foot.setValue((short) 4);
        return footRepo.save(foot);
    }

    private Foot getFootOfChild(Long footId, Long childId) {
        return footRepo.findByIdAndChildId(footId, childId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Значення розміру стопи з id = %s відсутнє для дитини з id = %s",
                        footId, childId)));
    }
}
