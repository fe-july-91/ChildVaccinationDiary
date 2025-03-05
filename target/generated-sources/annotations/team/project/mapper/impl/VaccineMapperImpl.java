package team.project.mapper.impl;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import team.project.dto.vaccine.CreateVaccineRequestDto;
import team.project.dto.vaccine.UpdateVaccineRequestDto;
import team.project.dto.vaccine.VaccineDto;
import team.project.mapper.VaccineMapper;
import team.project.model.Type;
import team.project.model.Vaccine;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T17:54:31+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class VaccineMapperImpl implements VaccineMapper {

    @Override
    public Vaccine toModel(CreateVaccineRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Vaccine vaccine = new Vaccine();

        setDate( vaccine, requestDto );

        return vaccine;
    }

    @Override
    public VaccineDto toDto(Vaccine vaccine) {
        if ( vaccine == null ) {
            return null;
        }

        VaccineDto vaccineDto = new VaccineDto();

        String description = vaccineTypeDescription( vaccine );
        if ( description != null ) {
            vaccineDto.setType( description );
        }
        if ( vaccine.getId() != null ) {
            vaccineDto.setId( vaccine.getId() );
        }

        setDate( vaccineDto, vaccine );

        return vaccineDto;
    }

    @Override
    public Vaccine updateFromDto(Vaccine vaccine, UpdateVaccineRequestDto requestDto) {
        if ( requestDto == null ) {
            return vaccine;
        }

        setDate( vaccine, requestDto );

        return vaccine;
    }

    private String vaccineTypeDescription(Vaccine vaccine) {
        Type type = vaccine.getType();
        if ( type == null ) {
            return null;
        }
        return type.getDescription();
    }
}
