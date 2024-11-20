package team.project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.dto.foot.CreateFootRequestDto;
import team.project.dto.foot.FootDto;
import team.project.dto.foot.UpdateFootRequestDto;
import team.project.mapper.FootMapper;
import team.project.model.Child;
import team.project.model.Foot;
import team.project.repository.foot.FootRepository;
import team.project.service.FootService;

@RequiredArgsConstructor
@Service
public class FootServiceImpl implements FootService {
    private final FootRepository footRepo;
    private final FootMapper footMapper;

    @Override
    public List<FootDto> getAllByChildId(Long childId) {
        List<Foot> result = footRepo.findAllByChildId(childId);
        return result.stream()
                .map(footMapper::toDto)
                .toList();
    }

    @Override
    public FootDto save(Child child, CreateFootRequestDto requestDto) {
        Foot foot = footMapper.toModel(requestDto);
        foot.setChild(child);
        return footMapper.toDto(footRepo.save(foot));
    }

    @Override
    @Transactional
    public FootDto update(Long childId, Long footId, UpdateFootRequestDto requestDto) {
        return footMapper.toDto(footRepo.save(
                footMapper.updateFromDto(getFootOfChild(footId, childId), requestDto)));
    }

    @Override
    @Transactional
    public void delete(Long childId, Long footId) {
        footRepo.delete(getFootOfChild(footId, childId));
    }

    @Override
    public List<FootDto> getAllByYearAndChildId(Long childId, int year) {
        List<Foot> result = footRepo.findAllByYearAndChildId(year, childId);
        return result.stream()
                .map(footMapper::toDto)
                .toList();
    }

    private Foot getFootOfChild(Long footId, Long childId) {
        return footRepo.findByIdAndChildId(footId, childId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Foot with id = %s not found for this childId = %s",
                                footId, childId)));
    }
}
