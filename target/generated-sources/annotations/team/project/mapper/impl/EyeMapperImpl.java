package team.project.mapper.impl;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import team.project.dto.eye.EyeDto;
import team.project.dto.eye.UpdateEyeRequestDto;
import team.project.mapper.EyeMapper;
import team.project.model.Child;
import team.project.model.Eye;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-25T20:02:51+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class EyeMapperImpl implements EyeMapper {

    @Override
    public Eye toModel(Child child) {
        if ( child == null ) {
            return null;
        }

        Eye eye = new Eye();

        if ( child != null ) {
            eye.setChild( child );
        }
        eye.setDeleted( child.isDeleted() );

        return eye;
    }

    @Override
    public Eye updateFromDto(Eye eye, UpdateEyeRequestDto requestDto) {
        if ( requestDto == null ) {
            return eye;
        }

        eye.setLeftEye( requestDto.leftEye() );
        eye.setRightEye( requestDto.rightEye() );

        return eye;
    }

    @Override
    public EyeDto toDto(Eye eye) {
        if ( eye == null ) {
            return null;
        }

        EyeDto eyeDto = new EyeDto();

        Long id = eyeChildId( eye );
        if ( id != null ) {
            eyeDto.setChildId( id );
        }
        if ( eye.getId() != null ) {
            eyeDto.setId( eye.getId() );
        }
        eyeDto.setLeftEye( eye.getLeftEye() );
        eyeDto.setRightEye( eye.getRightEye() );

        return eyeDto;
    }

    private Long eyeChildId(Eye eye) {
        Child child = eye.getChild();
        if ( child == null ) {
            return null;
        }
        return child.getId();
    }
}
