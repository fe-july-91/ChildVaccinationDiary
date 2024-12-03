package team.project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.dto.vaccine.CreateVaccineRequestDto;
import team.project.dto.vaccine.VaccineDto;
import team.project.mapper.VaccineMapper;
import team.project.model.Child;
import team.project.model.Type;
import team.project.model.TypeName;
import team.project.model.Vaccine;
import team.project.repository.vaccine.TypeRepository;
import team.project.repository.vaccine.VaccineRepository;
import team.project.service.VaccineService;

@RequiredArgsConstructor
@Service
public class VaccineServiceImpl implements VaccineService {
    private final VaccineRepository vaccineRepo;
    private final TypeRepository typeRepo;
    private final VaccineMapper vaccineMapper;

    @Override
    public VaccineDto save(Child child, CreateVaccineRequestDto requestDto) {
        Type typeFromDB = getType(requestDto.typeDescription());
        Vaccine vaccine = vaccineMapper.toModel(requestDto);
        vaccine.setChild(child);
        vaccine.setType(typeFromDB);
        vaccine.setOrderNumber((byte) (getOrderNumberByVaccine(child.getId(),
                typeFromDB.getId()) + 1));
        Vaccine savedVaccine = vaccineRepo.save(vaccine);
        return vaccineMapper.toDto(savedVaccine);
    }

    private Type getType(String name) {
        return typeRepo.findByTypeName(TypeName.getByType(name))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Can't find Type by '%s' in table types", name)));
    }

    private byte getOrderNumberByVaccine(Long childId, Long typeId) {
        List<Vaccine> vaccineList = vaccineRepo.findAllByChildIdAndTypeId(childId, typeId);
        return vaccineList.stream()
                .filter(vaccine -> !vaccine.isDeleted())
                .map(Vaccine::getOrderNumber)
                .max(Byte::compare)
                .orElse((byte) 0);
    }
}
