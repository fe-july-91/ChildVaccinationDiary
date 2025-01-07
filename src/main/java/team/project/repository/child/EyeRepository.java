package team.project.repository.child;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.project.model.Eye;

public interface EyeRepository extends JpaRepository<Eye, Long> {
    @Query(value = "SELECT e.child_id, e.left_eye, e.right_eye, e.is_deleted "
            + "FROM eyes e WHERE e.child_id = :childId AND is_deleted = FALSE",
            nativeQuery = true)
    Optional<Eye> findByChildId(@Param("childId") Long childId);
}
