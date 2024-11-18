package team.project.repository.weight;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.Weight;

public interface WeightRepository extends JpaRepository<Weight, Long> {
    List<Weight> findAllByChildId(Long childId);

}
