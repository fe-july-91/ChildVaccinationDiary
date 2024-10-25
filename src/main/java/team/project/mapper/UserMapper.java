package team.project.mapper;

import team.project.config.MapperConfig;
import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
