package team.project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.dto.eye.EyeDto;
import team.project.dto.eye.UpdateEyeRequestDto;
import team.project.mapper.EyeMapper;
import team.project.model.Child;
import team.project.model.Eye;
import team.project.repository.EyeRepository;
import team.project.service.EyeService;

@RequiredArgsConstructor
@Service
public class EyeServiceImpl implements EyeService {
    private final EyeRepository eyeRepo;
    private final EyeMapper eyeMapper;

    @Override
    @Transactional
    public void createDefault(Child child) {
        if (eyeRepo.findByChildId(child.getId()).isEmpty()) {
            Eye eye = eyeMapper.toModel(child);
            eye.setLeftEye(1.0f);
            eye.setRightEye(1.0f);
            eyeRepo.save(eye);
        }
    }

    @Override
    @Transactional
    public EyeDto updateById(Long childId, UpdateEyeRequestDto requestDto) {
        Eye eyeFromDB = eyeRepo.findByChildId(childId)
                .orElseThrow(() -> new EntityNotFoundException(
                String.format("Значення зору з id = %s не знайдено для цієї дитини.", childId)));
        return eyeMapper.toDto(eyeRepo.save(eyeMapper.updateFromDto(eyeFromDB, requestDto)));
    }

    @Override
    public EyeDto getEyeByChildId(Long childId) {
        return (eyeRepo.findByChildId(childId).isPresent())
                ? eyeMapper.toDto(eyeRepo.findByChildId(childId).get())
                : new EyeDto();
    }

    @Override
    @Transactional
    public void deleteByChildId(Long childId) {
        eyeRepo.deleteByChildId(childId);
    }
}
