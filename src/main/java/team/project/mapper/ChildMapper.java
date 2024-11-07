package team.project.mapper;

import java.util.Objects;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import team.project.config.MapperConfig;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.dto.child.UpdateChildRequestDto;
import team.project.model.Child;
import team.project.model.Gender;

@Mapper(config = MapperConfig.class, uses = UserMapper.class)
public interface ChildMapper {
    @Mapping(target = "genderName", ignore = true)
    Child toModel(CreateChildRequestDto requestDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.email", target = "userEmail")
    @Mapping(source = "user.name", target = "userName")
    ChildDto toDto(Child child);

    @Mapping(target = "genderName", ignore = true)
    Child updateFromDto(@MappingTarget Child child, UpdateChildRequestDto requestDto);

    @AfterMapping
    default void setGenderName(@MappingTarget Child child,
                               UpdateChildRequestDto requestDto) {
        if (requestDto.genderName() != null) {
            child.setGenderName(Objects.requireNonNull(
                    Gender.getByType(requestDto.genderName())).getGenderName());
        }
    }

    @AfterMapping
    default void setGenderName(@MappingTarget Child child,
                           CreateChildRequestDto requestDto) {
        if (requestDto.genderName() != null) {
            child.setGenderName(Objects.requireNonNull(
                    Gender.getByType(requestDto.genderName())).getGenderName());
        }
    }
}
