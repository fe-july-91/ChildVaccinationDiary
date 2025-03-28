package team.project.mapper;

import org.mapstruct.Mapper;
import team.project.config.MapperConfig;
import team.project.model.Height;

@Mapper(config = MapperConfig.class, uses = ChildMapper.class)
public interface HeightMapper extends JournalMapper<Height> {
}
