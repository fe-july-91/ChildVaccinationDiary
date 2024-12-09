package team.project.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import team.project.config.MapperConfig;
import team.project.dto.vaccine.CreateVaccineRequestDto;
import team.project.dto.vaccine.UpdateVaccineRequestDto;
import team.project.dto.vaccine.VaccineDto;
import team.project.model.Vaccine;

@Mapper(config = MapperConfig.class, uses = ChildMapper.class)
public interface VaccineMapper {
    int YEAR = 2;
    int MONTH = 1;
    int DAY = 0;

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "date", ignore = true)
    Vaccine toModel(CreateVaccineRequestDto requestDto);

    @Mapping(source = "type.description", target = "type")
    @Mapping(target = "date", ignore = true)
    VaccineDto toDto(Vaccine vaccine);

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "date", ignore = true)
    Vaccine updateFromDto(@MappingTarget Vaccine vaccine,
                                        UpdateVaccineRequestDto requestDto);

    @AfterMapping
    default void setDate(@MappingTarget Vaccine vaccine,
                         CreateVaccineRequestDto requestDto) {
        if (requestDto.date() != null) {
            String[] dates = requestDto.date().split("-");
            vaccine.setDate(LocalDate.of(Integer.parseInt(dates[YEAR]),
                    Integer.parseInt(dates[MONTH]),
                    Integer.parseInt(dates[DAY])));
        }
    }

    @AfterMapping
    default void setDate(@MappingTarget Vaccine vaccine,
                         UpdateVaccineRequestDto requestDto) {
        if (requestDto.date() != null) {
            String[] dates = requestDto.date().split("-");
            vaccine.setDate(LocalDate.of(Integer.parseInt(dates[YEAR]),
                    Integer.parseInt(dates[MONTH]),
                    Integer.parseInt(dates[DAY])));
        }
    }

    @AfterMapping
    default void setDate(@MappingTarget VaccineDto vaccineDto,
                         Vaccine vaccine) {
        if (vaccine.getDate() != null) {
            vaccineDto.setDate(vaccine.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
    }
}
