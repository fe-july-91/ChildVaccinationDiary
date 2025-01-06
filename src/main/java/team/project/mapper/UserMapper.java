package team.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import team.project.config.MapperConfig;
import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResetDataRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toModel(UserRegistrationRequestDto requestDto);

    UserResponseDto toResponseDto(User user);

    User updateFromDto(@MappingTarget User user, UserResetDataRequestDto requestDto);
}
