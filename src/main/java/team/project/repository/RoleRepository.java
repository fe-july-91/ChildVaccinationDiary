package team.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.Role;
import team.project.model.RoleName;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}
