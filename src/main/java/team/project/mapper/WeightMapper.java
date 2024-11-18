package team.project.mapper;

import java.time.Year;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import team.project.config.MapperConfig;
import team.project.dto.weight.CreateWeightRequestDto;
import team.project.dto.weight.UpdateWeightRequestDto;
import team.project.dto.weight.WeightDto;
import team.project.model.Weight;

@Mapper(config = MapperConfig.class, uses = ChildMapper.class)
public interface WeightMapper {
    @Mapping(target = "year", ignore = true)
    Weight toModel(CreateWeightRequestDto requestDto);

    WeightDto toDto(Weight weight);

    Weight updateFromDto(@MappingTarget Weight weight, UpdateWeightRequestDto requestDto);

    @AfterMapping
    default void setYear(@MappingTarget Weight weight,
                         CreateWeightRequestDto requestDto) {
        String strYear = requestDto.year();
        for (int i = 0; i < strYear.length(); i++) {
            if (!Character.isDigit(strYear.charAt(i))) {
                weight.setYear(Year.now().getValue());
            }
        }
        weight.setYear(Integer.parseInt(requestDto.year()));
    }
}
