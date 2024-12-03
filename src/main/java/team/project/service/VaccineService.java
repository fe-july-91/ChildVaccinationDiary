package team.project.service;

import team.project.dto.vaccine.CreateVaccineRequestDto;
import team.project.dto.vaccine.VaccineDto;
import team.project.model.Child;

public interface VaccineService {
    VaccineDto save(Child child, CreateVaccineRequestDto requestDto);
}
