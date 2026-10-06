package mate.academy.repository;

import java.util.Optional;
import mate.academy.entity.Role;
import mate.academy.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
