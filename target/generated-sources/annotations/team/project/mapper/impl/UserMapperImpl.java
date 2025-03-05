package team.project.mapper.impl;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import team.project.dto.user.UserRegistrationRequestDto;
import team.project.dto.user.UserResetDataRequestDto;
import team.project.dto.user.UserResponseDto;
import team.project.mapper.UserMapper;
import team.project.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T17:54:30+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toModel(UserRegistrationRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        User user = new User();

        if ( requestDto.email() != null ) {
            user.setEmail( requestDto.email() );
        }
        if ( requestDto.password() != null ) {
            user.setPassword( requestDto.password() );
        }
        if ( requestDto.name() != null ) {
            user.setName( requestDto.name() );
        }

        return user;
    }

    @Override
    public UserResponseDto toResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String email = null;
        String name = null;

        if ( user.getId() != null ) {
            id = user.getId();
        }
        if ( user.getEmail() != null ) {
            email = user.getEmail();
        }
        if ( user.getName() != null ) {
            name = user.getName();
        }

        UserResponseDto userResponseDto = new UserResponseDto( id, email, name );

        return userResponseDto;
    }

    @Override
    public User updateFromDto(User user, UserResetDataRequestDto requestDto) {
        if ( requestDto == null ) {
            return user;
        }

        if ( requestDto.email() != null ) {
            user.setEmail( requestDto.email() );
        }
        else {
            user.setEmail( null );
        }
        if ( requestDto.name() != null ) {
            user.setName( requestDto.name() );
        }
        else {
            user.setName( null );
        }

        return user;
    }
}
