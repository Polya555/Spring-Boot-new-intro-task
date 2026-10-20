package mate.academy.service;

import java.util.List;
import mate.academy.dto.OrderDto;
import mate.academy.dto.OrderItemDto;
import mate.academy.dto.OrderRequestDto;
import mate.academy.dto.UpdateOrderRequestDto;

public interface OrderService {
    OrderDto save(OrderRequestDto orderDto);

    OrderDto getById(Long id);

    List<OrderDto> getAll();

    List<OrderItemDto> getOrderItems(Long orderId);

    OrderItemDto getOrderItem(Long orderId, Long itemId);

    OrderDto updateOrderStatus(Long orderId, UpdateOrderRequestDto request);
}
