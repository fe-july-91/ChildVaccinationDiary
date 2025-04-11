package team.project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.dto.child.UpdateChildRequestDto;
import team.project.dto.eye.EyeDto;
import team.project.dto.eye.UpdateEyeRequestDto;
import team.project.dto.journal.CreateJournalRequestDto;
import team.project.dto.journal.JournalDto;
import team.project.dto.journal.UpdateJournalRequestDto;
import team.project.dto.vaccine.CreateVaccineRequestDto;
import team.project.dto.vaccine.UpdateVaccineRequestDto;
import team.project.dto.vaccine.VaccineDto;
import team.project.exception.EntityExistsCustomException;
import team.project.mapper.ChildMapper;
import team.project.model.Child;
import team.project.model.User;
import team.project.repository.ChildRepository;
import team.project.service.ChildService;
import team.project.service.EyeService;
import team.project.service.FootService;
import team.project.service.HeightService;
import team.project.service.VaccineService;
import team.project.service.WeightService;

@RequiredArgsConstructor
@Service
public class ChildServiceImpl implements ChildService {
    private final ChildRepository childRepo;
    private final ChildMapper childMapper;
    private final HeightService heightService;
    private final WeightService weightService;
    private final FootService footService;
    private final EyeService eyeService;
    private final VaccineService vaccineService;

    @Override
    @Transactional
    public ChildDto save(User user, CreateChildRequestDto requestDto) {
        Child child = childMapper.toModel(requestDto);
        child.setUser(user);
        return childMapper.toDto(childRepo.save(child));
    }

    @Override
    public void createDefaultData(Long childId) {
        Child child = childRepo.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException(
                String.format("Дитина з id: %s відсутня", childId)));

        YearMonth currentDate = YearMonth.now();
        String monthLowCases = currentDate.getMonth()
                .getDisplayName(TextStyle.FULL_STANDALONE, new Locale("uk"));
        String formattedMonth = monthLowCases.substring(0, 1).toUpperCase()
                + monthLowCases.substring(1);
        heightService.createDefault(child, currentDate.getYear(), formattedMonth);
        weightService.createDefault(child, currentDate.getYear(), formattedMonth);
        footService.createDefault(child, currentDate.getYear(), formattedMonth);
        eyeService.createDefault(child);
    }

    @Override
    @Transactional
    public void deleteAllByUserId(Long userId) {
        List<Child> kidsList = childRepo.findAllByUserId(userId);
        for (Child kid : kidsList) {
            Long childId = kid.getId();
            weightService.deleteAllByChildId(childId);
            heightService.deleteAllByChildId(childId);
            footService.deleteAllByChildId(childId);
            eyeService.deleteByChildId(childId);
            vaccineService.deleteAllByChildId(childId);
        }
        childRepo.deleteAll(kidsList);
    }

    @Override
    @Transactional
    public List<ChildDto> getChildsByUser(Long userId) {
        return childRepo.findAllByUserId(userId).stream()
                .map(childMapper::toDto).toList();
    }

    @Override
    @Transactional
    public List<ChildDto> getAll(Pageable pageable) {
        return childRepo.findAll(pageable).stream()
                .map(childMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ChildDto getChildById(Long id) {
        return childRepo.findById(id)
                .map(childMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Дитина з id: %s відсутня", id)));
    }

    @Override
    @Transactional
    public ChildDto getChildByIdAndUserId(Long userId, Long childId) {
        return childMapper.toDto(getChildOfUser(childId, userId));
    }

    @Override
    @Transactional
    public ChildDto updateChildByIdAndUserId(Long userId, Long childId,
                                             UpdateChildRequestDto newRequestDto) {
        return childMapper.toDto(childRepo.save(
                childMapper.updateFromDto(getChildOfUser(childId, userId), newRequestDto)));
    }

    @Override
    @Transactional
    public void deleteChildByIdAndUserId(Long userId, Long childId) {
        Child childFromDB = getChildOfUser(childId, userId);
        heightService.deleteAllByChildId(childFromDB.getId());
        weightService.deleteAllByChildId(childFromDB.getId());
        footService.deleteAllByChildId(childFromDB.getId());
        eyeService.deleteByChildId(childFromDB.getId());
        vaccineService.deleteAllByChildId(childFromDB.getId());
        childRepo.delete(childFromDB);
    }

    @Override
    public List<JournalDto> getAllHeightByChildId(Long userId, Long childId) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? heightService.getAllByChildId(childId) : List.of();
    }

    @Override
    public JournalDto saveHeight(Long userId, Long childId, CreateJournalRequestDto requestDto) {
        return heightService.save(getChildOfUser(childId, userId), requestDto);
    }

    @Override
    public JournalDto updateHeight(Long userId, Long childId, Long heightId,
                                   UpdateJournalRequestDto requestDto) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? heightService.update(childId, heightId, requestDto) : new JournalDto();
    }

    @Override
    public void deleteHeight(Long userId, Long childId, Long heightId) {
        if (childRepo.existsByIdAndUserId(childId, userId)) {
            heightService.delete(childId, heightId);
        }
    }

    @Override
    public List<JournalDto> getAllHeightByChildIdAndYear(Long userId, Long childId, int year) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? heightService.getAllByYearAndChildId(childId, year)
                : List.of();
    }

    @Override
    public List<JournalDto> getAllWeightByChildId(Long userId, Long childId) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? weightService.getAllByChildId(childId) : List.of();
    }

    @Override
    public JournalDto saveWeight(Long userId, Long childId, CreateJournalRequestDto requestDto) {
        return weightService.save(getChildOfUser(childId, userId), requestDto);
    }

    @Override
    public JournalDto updateWeight(Long userId, Long childId, Long weightId,
                                  UpdateJournalRequestDto requestDto) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? weightService.update(childId, weightId, requestDto) : new JournalDto();
    }

    @Override
    public void deleteWeight(Long userId, Long childId, Long weightId) {
        if (childRepo.existsByIdAndUserId(childId, userId)) {
            weightService.delete(childId, weightId);
        }
    }

    @Override
    public List<JournalDto> getAllWeightByChildIdAndYear(Long userId, Long childId, int year) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? weightService.getAllByYearAndChildId(childId, year)
                : List.of();
    }

    @Override
    public List<JournalDto> getAllFootByChildId(Long userId, Long childId) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? footService.getAllByChildId(childId)
                : List.of();
    }

    @Override
    public JournalDto saveFoot(Long userId, Long childId, CreateJournalRequestDto requestDto) {
        return footService.save(getChildOfUser(childId, userId), requestDto);
    }

    @Override
    public JournalDto updateFoot(Long userId, Long childId, Long footId,
                              UpdateJournalRequestDto requestDto) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? footService.update(childId, footId, requestDto) : new JournalDto();
    }

    @Override
    public void deleteFoot(Long userId, Long childId, Long footId) {
        if (childRepo.existsByIdAndUserId(childId, userId)) {
            footService.delete(childId, footId);
        }
    }

    @Override
    public List<JournalDto> getAllFootByChildIdAndYear(Long userId, Long childId, int year) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? footService.getAllByYearAndChildId(childId, year)
                : List.of();
    }

    @Override
    public EyeDto updateEye(Long userId, Long childId, UpdateEyeRequestDto requestDto) {
        return eyeService.updateById(getChildOfUser(childId, userId).getId(), requestDto);
    }

    @Override
    public EyeDto getEye(Long userId, Long childId) {
        return eyeService.getEyeByChildId(getChildOfUser(childId, userId).getId());
    }

    @Override
    public VaccineDto saveVaccine(Long userId, Long childId, CreateVaccineRequestDto requestDto)
            throws EntityExistsCustomException {
        return vaccineService.save(getChildOfUser(childId, userId), requestDto);
    }

    @Override
    public List<VaccineDto> getAllVaccine(Long userId, Long childId) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? vaccineService.getAllByChildId(childId)
                : List.of();
    }

    @Override
    public VaccineDto updateVaccine(Long userId, Long childId, Long vaccineId,
                                    UpdateVaccineRequestDto requestDto) {
        return (childRepo.existsByIdAndUserId(childId, userId))
                ? vaccineService.update(childId, vaccineId, requestDto)
                : new VaccineDto();
    }

    @Override
    public void deleteVaccine(Long userId, Long childId, Long vaccineId) {
        if (childRepo.existsByIdAndUserId(childId, userId)) {
            vaccineService.delete(childId, vaccineId);
        }
    }

    private Child getChildOfUser(Long childId, Long userId) {
        return childRepo.findByIdAndUserId(childId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Дитина з id = %s відсутня для цього користувача", childId)));
    }
}
