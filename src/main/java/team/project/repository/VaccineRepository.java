package team.project.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findAllByChildId(Long childId);

    Optional<Vaccine> findByIdAndChildId(Long vaccineId, Long childId);

    List<Vaccine> findByChildIdAndTypeIdAndDate(Long childId, Long typeId, LocalDate date);

    boolean existsByChildIdAndTypeIdAndDate(Long childId, Long typeId, LocalDate date);

    void deleteAllByChildId(Long childId);
}
