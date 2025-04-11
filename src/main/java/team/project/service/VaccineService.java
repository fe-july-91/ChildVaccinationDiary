package team.project.service;

import java.util.List;
import team.project.dto.vaccine.CreateVaccineRequestDto;
import team.project.dto.vaccine.UpdateVaccineRequestDto;
import team.project.dto.vaccine.VaccineDto;
import team.project.exception.EntityExistsCustomException;
import team.project.model.Child;

public interface VaccineService {
    VaccineDto save(Child child, CreateVaccineRequestDto requestDto)
            throws EntityExistsCustomException;

    List<VaccineDto> getAllByChildId(Long childId);

    VaccineDto update(Long childId, Long vaccineId, UpdateVaccineRequestDto requestDto);

    void delete(Long childId, Long vaccineId);
}
