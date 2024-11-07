package team.project.mapper;

import java.time.Year;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import team.project.config.MapperConfig;
import team.project.dto.height.CreateHeightRequestDto;
import team.project.dto.height.HeightDto;
import team.project.dto.height.UpdateHeightRequestDto;
import team.project.model.Height;

@Mapper(config = MapperConfig.class, uses = ChildMapper.class)
public interface HeightMapper {
    @Mapping(target = "year", ignore = true)
    Height toModel(CreateHeightRequestDto requestDto);

    HeightDto toDto(Height height);

    Height updateFromDto(@MappingTarget Height height, UpdateHeightRequestDto requestDto);

    @AfterMapping
    default void setYear(@MappingTarget Height height,
                         CreateHeightRequestDto requestDto) {
        String strYear = requestDto.year();
        for (int i = 0; i < strYear.length(); i++) {
            if (!Character.isDigit(strYear.charAt(i))) {
                height.setYear(Year.now().getValue());
            }
        }
        height.setYear(Integer.parseInt(requestDto.year()));
    }
}
