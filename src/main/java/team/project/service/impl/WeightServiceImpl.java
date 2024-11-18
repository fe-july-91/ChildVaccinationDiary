package team.project.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.project.dto.weight.WeightDto;
import team.project.mapper.WeightMapper;
import team.project.model.Weight;
import team.project.repository.weight.WeightRepository;
import team.project.service.WeightService;

@RequiredArgsConstructor
@Service
public class WeightServiceImpl implements WeightService {
    private final WeightRepository weightRepo;
    private final WeightMapper weightMapper;

    @Override
    public List<WeightDto> getAllByChildId(Long childId) {
        List<Weight> result = weightRepo.findAllByChildId(childId);
        return result.stream()
                .map(weightMapper::toDto)
                .toList();
    }
}
