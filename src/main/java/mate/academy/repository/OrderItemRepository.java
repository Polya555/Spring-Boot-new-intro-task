package mate.academy.repository;

import java.util.Optional;
import mate.academy.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByIdAndOrderIdAndUserId(Long id, Long orderId, Long userId);
}
