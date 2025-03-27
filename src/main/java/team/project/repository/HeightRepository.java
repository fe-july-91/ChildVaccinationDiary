package team.project.repository;

import org.springframework.stereotype.Repository;
import team.project.model.Height;

@Repository
public interface HeightRepository extends JournalRepository<Height> {

    boolean existsByIdAndChildId(Long heightId, Long childId);
}
