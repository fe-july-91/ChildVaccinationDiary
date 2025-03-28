package team.project.mapper;

import java.time.Year;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import team.project.dto.journal.CreateJournalRequestDto;
import team.project.dto.journal.JournalDto;
import team.project.dto.journal.UpdateJournalRequestDto;
import team.project.model.Journal;

public interface JournalMapper<T extends Journal> {

    @Mapping(target = "year", ignore = true)
    T toModel(CreateJournalRequestDto requestDto);

    JournalDto toDto(T journal);

    T updateFromDto(@MappingTarget T journal, UpdateJournalRequestDto requestDto);

    @AfterMapping
    default void setYear(@MappingTarget T journal, CreateJournalRequestDto requestDto) {
        String strYear = requestDto.year();
        if (!strYear.matches("\\d+")) {
            journal.setYear(Year.now().getValue());
        } else {
            journal.setYear(Integer.parseInt(strYear));
        }
    }
}
