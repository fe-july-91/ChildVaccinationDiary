package team.project.mapper;

import org.mapstruct.Mapper;
import team.project.config.MapperConfig;
import team.project.model.Weight;

@Mapper(config = MapperConfig.class, uses = ChildMapper.class)
public interface WeightMapper extends JournalMapper<Weight> {
}
