package team.project.service;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Pageable;
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
import team.project.exception.DuplicateCheckingException;
import team.project.model.User;

public interface ChildService {
    ChildDto save(User user, CreateChildRequestDto requestDto);

    List<ChildDto> getChildsByUser(Long userId);

    List<ChildDto> getAll(Pageable pageable);

    ChildDto getChildById(Long id);

    ChildDto getChildByIdAndUserId(Long userId, Long childId);

    ChildDto updateChildByIdAndUserId(Long userId, Long childId,
                                      UpdateChildRequestDto newRequestDto);

    List<JournalDto> getAllHeightByChildId(Long userId, Long childId);

    JournalDto saveHeight(Long userId, Long childId, CreateJournalRequestDto requestDto);

    JournalDto updateHeight(Long userId, Long childId, Long heightId,
                            @Valid UpdateJournalRequestDto requestDto);

    void deleteHeight(Long userId, Long childId, Long heightId);

    List<JournalDto> getAllHeightByChildIdAndYear(Long userId, Long childId, int year);

    List<JournalDto> getAllWeightByChildId(Long userId, Long childId);

    JournalDto saveWeight(Long userId, Long childId, CreateJournalRequestDto requestDto);

    JournalDto updateWeight(Long userId, Long childId, Long weightId,
                           UpdateJournalRequestDto requestDto);

    void deleteWeight(Long userId, Long childId, Long weightId);

    List<JournalDto> getAllWeightByChildIdAndYear(Long userId, Long childId, int year);

    void deleteChildByIdAndUserId(Long userId, Long childId);

    List<JournalDto> getAllFootByChildId(Long userId, Long childId);

    JournalDto saveFoot(Long userId, Long childId, CreateJournalRequestDto requestDto);

    JournalDto updateFoot(Long userId, Long childId, Long footId,
                          UpdateJournalRequestDto requestDto);

    void deleteFoot(Long userId, Long childId, Long footId);

    List<JournalDto> getAllFootByChildIdAndYear(Long userId, Long childId, int year);

    EyeDto updateEye(Long userId, Long childId, UpdateEyeRequestDto requestDto);

    EyeDto getEye(Long userId, Long childId);

    VaccineDto saveVaccine(Long userId, Long childId, CreateVaccineRequestDto requestDto)
            throws DuplicateCheckingException;

    List<VaccineDto> getAllVaccine(Long userId, Long childId);

    VaccineDto updateVaccine(Long userId, Long childId, Long vaccineId,
                             UpdateVaccineRequestDto requestDto);

    void deleteVaccine(Long userId, Long childId, Long vaccineId);

    void createDefaultData(Long childId);
}
