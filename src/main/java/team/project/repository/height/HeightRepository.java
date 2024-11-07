package team.project.repository.height;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.Height;

public interface HeightRepository extends JpaRepository<Height, Long> {
    List<Height> findAllByChildId(Long childId);

    Optional<Height> findByIdAndChildId(Long heightId, Long childId);
}
