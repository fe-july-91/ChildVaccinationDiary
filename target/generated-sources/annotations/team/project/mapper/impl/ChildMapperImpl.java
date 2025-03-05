package team.project.mapper.impl;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.dto.child.UpdateChildRequestDto;
import team.project.mapper.ChildMapper;
import team.project.model.Child;
import team.project.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T17:54:31+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class ChildMapperImpl implements ChildMapper {

    @Override
    public Child toModel(CreateChildRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Child child = new Child();

        if ( requestDto.name() != null ) {
            child.setName( requestDto.name() );
        }
        if ( requestDto.surname() != null ) {
            child.setSurname( requestDto.surname() );
        }

        setGenderName( child, requestDto );
        setImage( child, requestDto );
        setBirth( child, requestDto );

        return child;
    }

    @Override
    public ChildDto toDto(Child child) {
        if ( child == null ) {
            return null;
        }

        ChildDto childDto = new ChildDto();

        Long id = childUserId( child );
        if ( id != null ) {
            childDto.setUserId( id );
        }
        String email = childUserEmail( child );
        if ( email != null ) {
            childDto.setUserEmail( email );
        }
        String name = childUserName( child );
        if ( name != null ) {
            childDto.setUserName( name );
        }
        if ( child.getId() != null ) {
            childDto.setId( child.getId() );
        }
        if ( child.getName() != null ) {
            childDto.setName( child.getName() );
        }
        if ( child.getSurname() != null ) {
            childDto.setSurname( child.getSurname() );
        }
        if ( child.getGenderName() != null ) {
            childDto.setGenderName( child.getGenderName() );
        }
        if ( child.getImage() != null ) {
            childDto.setImage( child.getImage() );
        }

        setBirth( childDto, child );

        return childDto;
    }

    @Override
    public Child updateFromDto(Child child, UpdateChildRequestDto requestDto) {
        if ( requestDto == null ) {
            return child;
        }

        if ( requestDto.name() != null ) {
            child.setName( requestDto.name() );
        }
        else {
            child.setName( null );
        }
        if ( requestDto.surname() != null ) {
            child.setSurname( requestDto.surname() );
        }
        else {
            child.setSurname( null );
        }

        setGenderName( child, requestDto );
        setImage( child, requestDto );
        setBirth( child, requestDto );

        return child;
    }

    private Long childUserId(Child child) {
        User user = child.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private String childUserEmail(Child child) {
        User user = child.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getEmail();
    }

    private String childUserName(Child child) {
        User user = child.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getName();
    }
}
