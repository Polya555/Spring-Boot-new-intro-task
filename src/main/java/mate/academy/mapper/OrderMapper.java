package mate.academy.mapper;

import mate.academy.dto.OrderDto;
import mate.academy.dto.OrderItemDto;
import mate.academy.dto.OrderRequestDto;
import mate.academy.entity.Order;
import mate.academy.entity.OrderItem;

public interface OrderMapper {
    OrderDto toDto(Order order);

    Order toEntity(OrderRequestDto dto);

    OrderItemDto toItemDto(OrderItem orderItem);
}
