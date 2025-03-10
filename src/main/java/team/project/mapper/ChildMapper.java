package team.project.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    int YEAR = 2;
    int MONTH = 1;
    int DAY = 0;

    @Mapping(target = "genderName", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "birth", ignore = true)
    Child toModel(CreateChildRequestDto requestDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.email", target = "userEmail")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(target = "birth", ignore = true)
    ChildDto toDto(Child child);

    @Mapping(target = "genderName", ignore = true)
    @Mapping(target = "birth", ignore = true)
    @Mapping(target = "image", ignore = true)
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

    @AfterMapping
    default void setImage(@MappingTarget Child child, CreateChildRequestDto requestDto) {
        if (requestDto.image() != null) {
            child.setImage(requestDto.image());
        } else {
            child.setImage("1");
        }
    }

    @AfterMapping
    default void setImage(@MappingTarget Child child, UpdateChildRequestDto requestDto) {
        if (requestDto.image() != null) {
            child.setImage(requestDto.image());
        } else {
            child.setImage("1");
        }
    }

    @AfterMapping
    default void setBirth(@MappingTarget ChildDto childDto, Child child) {
        if (child.getBirth() != null) {
            childDto.setBirth(child.getBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
    }

    @AfterMapping
    default void setBirth(@MappingTarget Child child, CreateChildRequestDto requestDto) {
        if (requestDto.birth() != null) {
            String[] birthday = requestDto.birth().split("-");
            child.setBirth(LocalDate.of(Integer.parseInt(birthday[YEAR]),
                    Integer.parseInt(birthday[MONTH]),
                    Integer.parseInt(birthday[DAY])));
        }
    }

    @AfterMapping
    default void setBirth(@MappingTarget Child child, UpdateChildRequestDto requestDto) {
        if (requestDto.birth() != null) {
            String[] birthday = requestDto.birth().split("-");
            child.setBirth(LocalDate.of(Integer.parseInt(birthday[YEAR]),
                    Integer.parseInt(birthday[MONTH]),
                    Integer.parseInt(birthday[DAY])));
        }
    }
}
