package team.project.repository.vaccine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.project.model.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findAllByChildId(Long childId);

    Optional<Vaccine> findByIdAndChildIdAndIsDeletedFalse(Long vaccineId, Long childId);

    Optional<Vaccine> findByChildIdAndTypeIdAndDate(
            Long childId, Long typeId, LocalDate date);

    @Query("select v from Vaccine v where v.child.id = :childId "
            + "and v.type.id = :typeId and v.date = :date")
    List<Vaccine> findByTypeIdAndDate(@Param("childId") Long childId,
                                                    @Param("typeId") Long typeId,
                                                    @Param("date") LocalDate date);

    boolean existsByChildIdAndTypeIdAndDateAndIsDeletedFalse(
            Long childId, Long typeId, LocalDate date);
}
