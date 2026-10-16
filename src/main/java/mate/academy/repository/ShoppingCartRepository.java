package mate.academy.repository;

import java.util.Optional;
import mate.academy.entity.ShoppingCart;
import mate.academy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUser(User user);
}

