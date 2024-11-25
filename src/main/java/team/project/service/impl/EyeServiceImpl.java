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
import team.project.repository.child.EyeRepository;
import team.project.service.EyeService;

@RequiredArgsConstructor
@Service
public class EyeServiceImpl implements EyeService {
    private final EyeRepository eyeRepo;
    private final EyeMapper eyeMapper;

    @Override
    @Transactional
    public void createDefault(Child child) {
        if (eyeRepo.existsById(child.getId())) {
            return;
        }
        Eye eye = eyeMapper.mapChildToEye(child);
        eye.setLeftEye(1.0f);
        eye.setRightEye(1.0f);
        eyeRepo.save(eye);
    }

    @Override
    @Transactional
    public EyeDto updateById(Long childId, UpdateEyeRequestDto requestDto) {
        Eye eyeFromDB = eyeRepo.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException(
                String.format("Eye with id = %s not found for this child", childId)));
        return eyeMapper.toDto(eyeRepo.save(eyeMapper.updateFromDto(eyeFromDB, requestDto)));
    }

    @Override
    public EyeDto getEyeById(Long childId) {
        return (eyeRepo.existsById(childId))
                ? eyeMapper.toDto(eyeRepo.findById(childId).get())
                : new EyeDto();
    }
}
