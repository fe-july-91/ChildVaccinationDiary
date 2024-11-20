package team.project.repository.foot;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.Foot;

public interface FootRepository extends JpaRepository<Foot, Long> {
    Optional<Foot> findByIdAndChildId(Long footId, Long childId);

    List<Foot> findAllByYearAndChildId(int year, Long childId);

    List<Foot> findAllByChildId(Long childId);
}
