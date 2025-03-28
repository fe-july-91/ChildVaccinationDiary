package team.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.Type;
import team.project.model.TypeName;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByTypeName(TypeName byType);
}
