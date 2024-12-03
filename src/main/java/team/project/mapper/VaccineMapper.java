package team.project.mapper;

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
    @Mapping(target = "type", ignore = true)
    Vaccine toModel(CreateVaccineRequestDto requestDto);

    VaccineDto toDto(Vaccine vaccine);

    Vaccine updateFromDto(@MappingTarget Vaccine vaccine,
                                        UpdateVaccineRequestDto requestDto);
}
