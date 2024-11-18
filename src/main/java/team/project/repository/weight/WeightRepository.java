package team.project.repository.weight;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.Weight;

public interface WeightRepository extends JpaRepository<Weight, Long> {
    Optional<Weight> findByIdAndChildId(Long weightId, Long childId);

    boolean existsByIdAndChildId(Long weightId, Long childId);

    List<Weight> findAllByYearAndChildId(int year, Long childId);

    List<Weight> findAllByChildId(Long childId);

}
