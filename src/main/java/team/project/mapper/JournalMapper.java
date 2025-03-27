package team.project.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import team.project.config.MapperConfig;
import team.project.dto.journal.CreateJournalRequestDto;
import team.project.dto.journal.JournalDto;
import team.project.dto.journal.UpdateJournalRequestDto;
import team.project.model.Journal;

import java.time.Year;

@Mapper(config = MapperConfig.class, uses = ChildMapper.class)
public interface JournalMapper {

    @Mapping(target = "year", ignore = true)
    <T extends Journal> T toModel(Class<T> clazz, CreateJournalRequestDto requestDto);

    JournalDto toDto(Journal journal);

    <T extends Journal> T updateFromDto(@MappingTarget T journal, UpdateJournalRequestDto requestDto);

    @AfterMapping
    default void setYear(@MappingTarget Journal journal, CreateJournalRequestDto requestDto) {
        String strYear = requestDto.year();
        if (!strYear.matches("\\d+")) {
            journal.setYear(Year.now().getValue());
        } else {
            journal.setYear(Integer.parseInt(strYear));
        }
    }
}
