package team.project.service;

import java.util.List;
import team.project.dto.journal.CreateJournalRequestDto;
import team.project.dto.journal.JournalDto;
import team.project.dto.journal.UpdateJournalRequestDto;
import team.project.model.Child;
import team.project.model.Journal;

public interface JournalService<T extends Journal> {
    List<JournalDto> getAllByChildId(Long childId);

    JournalDto save(Child child, CreateJournalRequestDto requestDto);

    JournalDto update(Long childId, Long journalId, UpdateJournalRequestDto requestDto);

    void delete(Long childId, Long journalId);

    List<JournalDto> getAllByYearAndChildId(Long childId, int year);

    T createDefault(Child child, int currentYear, String currentMonth);

    void deleteAllByChildId(Long childId);
}
