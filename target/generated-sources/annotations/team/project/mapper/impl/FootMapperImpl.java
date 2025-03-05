package team.project.mapper.impl;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import team.project.dto.foot.CreateFootRequestDto;
import team.project.dto.foot.FootDto;
import team.project.dto.foot.UpdateFootRequestDto;
import team.project.mapper.FootMapper;
import team.project.model.Foot;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T17:54:31+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class FootMapperImpl implements FootMapper {

    @Override
    public Foot toModel(CreateFootRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Foot foot = new Foot();

        if ( requestDto.month() != null ) {
            foot.setMonth( requestDto.month() );
        }
        foot.setValue( requestDto.value() );

        setYear( foot, requestDto );

        return foot;
    }

    @Override
    public FootDto toDto(Foot foot) {
        if ( foot == null ) {
            return null;
        }

        FootDto footDto = new FootDto();

        if ( foot.getId() != null ) {
            footDto.setId( foot.getId() );
        }
        footDto.setYear( foot.getYear() );
        if ( foot.getMonth() != null ) {
            footDto.setMonth( foot.getMonth() );
        }
        footDto.setValue( foot.getValue() );

        return footDto;
    }

    @Override
    public Foot updateFromDto(Foot foot, UpdateFootRequestDto requestDto) {
        if ( requestDto == null ) {
            return foot;
        }

        if ( requestDto.year() != null ) {
            foot.setYear( Integer.parseInt( requestDto.year() ) );
        }
        if ( requestDto.month() != null ) {
            foot.setMonth( requestDto.month() );
        }
        else {
            foot.setMonth( null );
        }
        foot.setValue( requestDto.value() );

        return foot;
    }
}
