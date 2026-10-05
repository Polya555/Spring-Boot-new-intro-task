package mate.academy.repository;

import java.util.Optional;
import mate.academy.entity.Role;
import mate.academy.entity.RoleName;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @EntityGraph(attributePaths = "roles")
    Optional<Role> findByName(RoleName name);
}
