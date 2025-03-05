package team.project.mapper.impl;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import team.project.dto.weight.CreateWeightRequestDto;
import team.project.dto.weight.UpdateWeightRequestDto;
import team.project.dto.weight.WeightDto;
import team.project.mapper.WeightMapper;
import team.project.model.Weight;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T17:54:31+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class WeightMapperImpl implements WeightMapper {

    @Override
    public Weight toModel(CreateWeightRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Weight weight = new Weight();

        if ( requestDto.month() != null ) {
            weight.setMonth( requestDto.month() );
        }
        weight.setValue( requestDto.value() );

        setYear( weight, requestDto );

        return weight;
    }

    @Override
    public WeightDto toDto(Weight weight) {
        if ( weight == null ) {
            return null;
        }

        WeightDto weightDto = new WeightDto();

        if ( weight.getId() != null ) {
            weightDto.setId( weight.getId() );
        }
        weightDto.setYear( weight.getYear() );
        if ( weight.getMonth() != null ) {
            weightDto.setMonth( weight.getMonth() );
        }
        weightDto.setValue( weight.getValue() );

        return weightDto;
    }

    @Override
    public Weight updateFromDto(Weight weight, UpdateWeightRequestDto requestDto) {
        if ( requestDto == null ) {
            return weight;
        }

        if ( requestDto.year() != null ) {
            weight.setYear( Integer.parseInt( requestDto.year() ) );
        }
        if ( requestDto.month() != null ) {
            weight.setMonth( requestDto.month() );
        }
        else {
            weight.setMonth( null );
        }
        weight.setValue( requestDto.value() );

        return weight;
    }
}
