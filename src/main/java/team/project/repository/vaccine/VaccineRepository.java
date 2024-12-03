package team.project.repository.vaccine;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    Optional<Vaccine> findByChildIdAndTypeId(Long childId, Long typeId);

    List<Vaccine> findAllByChildIdAndTypeId(Long childId, Long typeId);
}
