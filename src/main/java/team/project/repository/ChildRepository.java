package team.project.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.Child;

public interface ChildRepository extends JpaRepository<Child, Long> {
    List<Child> findAllByUserId(Long userId);

    Optional<Child> findByIdAndUserId(Long childId, Long userId);

    boolean existsByIdAndUserId(Long childId, Long userId);

    void deleteAllByUserId(Long userId);
}
