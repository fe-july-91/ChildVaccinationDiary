package team.project.mapper;

import org.mapstruct.Mapper;
import team.project.config.MapperConfig;
import team.project.model.Foot;

@Mapper(config = MapperConfig.class, uses = ChildMapper.class)
public interface FootMapper extends JournalMapper<Foot> {
}
