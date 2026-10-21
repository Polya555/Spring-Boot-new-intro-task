package mate.academy.service;

import java.util.List;
import mate.academy.dto.OrderDto;
import mate.academy.dto.OrderItemDto;
import mate.academy.dto.OrderRequestDto;
import mate.academy.dto.UpdateOrderRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDto save(OrderRequestDto orderDto);

    Page<OrderDto> getAll(Pageable pageable);

    List<OrderItemDto> getOrderItems(Long orderId, Long userId);

    OrderItemDto getOrderItem(Long orderItemId, Long orderId, Long userId);

    OrderDto updateOrderStatus(Long orderId, UpdateOrderRequestDto request);
}
