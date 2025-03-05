package team.project.mapper.impl;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import team.project.dto.height.CreateHeightRequestDto;
import team.project.dto.height.HeightDto;
import team.project.dto.height.UpdateHeightRequestDto;
import team.project.mapper.HeightMapper;
import team.project.model.Height;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T17:54:31+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class HeightMapperImpl implements HeightMapper {

    @Override
    public Height toModel(CreateHeightRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Height height = new Height();

        if ( requestDto.month() != null ) {
            height.setMonth( requestDto.month() );
        }
        height.setValue( requestDto.value() );

        setYear( height, requestDto );

        return height;
    }

    @Override
    public HeightDto toDto(Height height) {
        if ( height == null ) {
            return null;
        }

        HeightDto heightDto = new HeightDto();

        if ( height.getId() != null ) {
            heightDto.setId( height.getId() );
        }
        heightDto.setYear( height.getYear() );
        if ( height.getMonth() != null ) {
            heightDto.setMonth( height.getMonth() );
        }
        heightDto.setValue( height.getValue() );

        return heightDto;
    }

    @Override
    public Height updateFromDto(Height height, UpdateHeightRequestDto requestDto) {
        if ( requestDto == null ) {
            return height;
        }

        if ( requestDto.year() != null ) {
            height.setYear( Integer.parseInt( requestDto.year() ) );
        }
        if ( requestDto.month() != null ) {
            height.setMonth( requestDto.month() );
        }
        else {
            height.setMonth( null );
        }
        height.setValue( requestDto.value() );

        return height;
    }
}
