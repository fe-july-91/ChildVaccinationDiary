package team.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import team.project.config.MapperConfig;
import team.project.dto.eye.EyeDto;
import team.project.dto.eye.UpdateEyeRequestDto;
import team.project.model.Child;
import team.project.model.Eye;

@Mapper(config = MapperConfig.class, uses = ChildMapper.class)
public interface EyeMapper {
    @Mapping(source = "child", target = "child")
    Eye mapChildToEye(Child child);

    Eye updateFromDto(@MappingTarget Eye eye, UpdateEyeRequestDto requestDto);

    @Mapping(source = "child.id", target = "childId")
    EyeDto toDto(Eye eye);
}
