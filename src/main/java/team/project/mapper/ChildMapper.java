package team.project.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import team.project.config.MapperConfig;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.model.Child;
import team.project.model.Gender;

@Mapper(config = MapperConfig.class, uses = UserMapper.class)
public interface ChildMapper {
    @Mapping(target = "gender", ignore = true)
    Child toModel(CreateChildRequestDto requestDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.email", target = "userEmail")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "gender.genderName", target = "genderName")
    ChildDto toDto(Child child);

    @AfterMapping
    default void setGender(@MappingTarget Child child,
                           CreateChildRequestDto requestDto) {
        if (requestDto.genderName() != null) {
            child.setGender(Gender.getByType(requestDto.genderName()));
        }
    }
}