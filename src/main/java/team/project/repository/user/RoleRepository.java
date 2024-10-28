package team.project.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.Role;
import team.project.model.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}
