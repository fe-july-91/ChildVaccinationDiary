package team.project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.dto.vaccine.CreateVaccineRequestDto;
import team.project.dto.vaccine.UpdateVaccineRequestDto;
import team.project.dto.vaccine.VaccineDto;
import team.project.exception.DuplicateCheckingException;
import team.project.mapper.VaccineMapper;
import team.project.model.Child;
import team.project.model.Type;
import team.project.model.TypeName;
import team.project.model.Vaccine;
import team.project.repository.TypeRepository;
import team.project.repository.VaccineRepository;
import team.project.service.VaccineService;

@RequiredArgsConstructor
@Service
public class VaccineServiceImpl implements VaccineService {
    private final VaccineRepository vaccineRepo;
    private final TypeRepository typeRepo;
    private final VaccineMapper vaccineMapper;

    @Override
    @Transactional
    public VaccineDto save(Child child, CreateVaccineRequestDto requestDto)
            throws DuplicateCheckingException {
        Type typeFromDB = getType(requestDto.type());
        Vaccine vaccine = vaccineMapper.toModel(requestDto);
        vaccine.setChild(child);
        vaccine.setType(typeFromDB);
        checkByDate(vaccine.getChild().getId(),
                vaccine.getType().getId(),
                vaccine.getDate());
        return vaccineMapper.toDto(vaccineRepo.save(vaccine));
    }

    @Override
    @Transactional
    public List<VaccineDto> getAllByChildId(Long childId) {
        List<Vaccine> result = vaccineRepo.findAllByChildId(childId);
        return result.stream()
                .map(vaccineMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public VaccineDto update(Long childId, Long vaccineId, UpdateVaccineRequestDto requestDto) {
        Vaccine vaccine = vaccineMapper.updateFromDto(getVaccineOfChild(vaccineId, childId),
                requestDto);
        vaccine.setType(getType(requestDto.type()));

        List<Vaccine> duplicateOfVaccine = vaccineRepo
                .findByChildIdAndTypeIdAndDate(childId,
                vaccine.getType().getId(),
                vaccine.getDate());
        if (!duplicateOfVaccine.isEmpty()) {
            for (Vaccine entity : duplicateOfVaccine) {
                if (!entity.getId().equals(vaccineId)) {
                    vaccineRepo.delete(entity);
                }
            }
        }
        return vaccineMapper.toDto(vaccineRepo.save(vaccine));
    }

    @Override
    @Transactional
    public void delete(Long childId, Long vaccineId) {
        vaccineRepo.delete(getVaccineOfChild(vaccineId, childId));
    }

    private Vaccine getVaccineOfChild(Long vaccineId, Long childId) {
        return vaccineRepo.findByIdAndChildId(vaccineId, childId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Вакцина з id = %s не знайдена для дитини з id = %s.",
                                vaccineId, childId)));

    }

    private Type getType(String name) {
        return typeRepo.findByTypeName(TypeName.getByType(name))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Тип вакцини '%s' відсутній у таблиці вакцин", name)));
    }

    private void checkByDate(Long childId, Long typeId, LocalDate date)
            throws DuplicateCheckingException {
        if (vaccineRepo.existsByChildIdAndTypeIdAndDate(
                childId,
                typeId,
                date)) {
            throw new DuplicateCheckingException("Така вакцинація на цю дату вже була зроблена."
                    + "Необхідно змінити тип вікцинації або вказати іншу дату!");
        }
    }
}
