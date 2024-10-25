package team.project.repository.role;

import team.project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import team.project.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}
