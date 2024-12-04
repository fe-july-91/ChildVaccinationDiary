package team.project.repository.vaccine;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    Optional<Vaccine> findByChildIdAndTypeIdAndOrderNumberAndIsDeletedFalse(Long childId,
                                                                            Long typeId,
                                                                            byte orderNumbe);

    boolean existsByChildIdAndTypeIdAndOrderNumberAndIsDeletedFalse(Long childId,
                                                                    Long typeId,
                                                                    byte orderNumber);

    List<Vaccine> findAllByChildIdAndTypeId(Long childId, Long typeId);

    List<Vaccine> findAllByChildId(Long childId);

    Optional<Vaccine> findByIdAndChildIdAndIsDeletedFalse(Long vaccineId, Long childId);

}
