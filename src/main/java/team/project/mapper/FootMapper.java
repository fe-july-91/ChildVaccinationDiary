package team.project.mapper;

import java.time.Year;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import team.project.config.MapperConfig;
import team.project.dto.foot.CreateFootRequestDto;
import team.project.dto.foot.FootDto;
import team.project.dto.foot.UpdateFootRequestDto;
import team.project.model.Foot;

@Mapper(config = MapperConfig.class, uses = ChildMapper.class)
public interface FootMapper {
    @Mapping(target = "year", ignore = true)
    Foot toModel(CreateFootRequestDto requestDto);

    FootDto toDto(Foot foot);

    Foot updateFromDto(@MappingTarget Foot foot, UpdateFootRequestDto requestDto);

    @AfterMapping
    default void setYear(@MappingTarget Foot foot,
                         CreateFootRequestDto requestDto) {
        String strYear = requestDto.year();
        for (int i = 0; i < strYear.length(); i++) {
            if (!Character.isDigit(strYear.charAt(i))) {
                foot.setYear(Year.now().getValue());
            }
        }
        foot.setYear(Integer.parseInt(requestDto.year()));
    }
}
