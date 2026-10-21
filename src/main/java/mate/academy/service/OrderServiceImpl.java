package mate.academy.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.OrderDto;
import mate.academy.dto.OrderItemDto;
import mate.academy.dto.OrderRequestDto;
import mate.academy.dto.UpdateOrderRequestDto;
import mate.academy.entity.Order;
import mate.academy.entity.OrderItem;
import mate.academy.entity.Status;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.OrderMapper;
import mate.academy.repository.OrderItemRepository;
import mate.academy.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderDto save(OrderRequestDto orderDto) {
        Order savedOrder = orderRepository.save(orderMapper.toEntity(orderDto));
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(orderMapper::toDto);
    }

    @Override
    public List<OrderItemDto> getOrderItems(Long orderId, Long userId) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order by id "
                        + orderId));
        return order.getOrderItems()
                .stream()
                .map(orderMapper::toItemDto)
                .toList();
    }

    @Override
    public OrderItemDto getOrderItem(Long orderItemId, Long orderId, Long userId) {
        OrderItem item = orderItemRepository.findByIdAndOrderIdAndUserId(orderItemId, orderId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order item by id "
                        + orderItemId));
        return orderMapper.toItemDto(item);
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, UpdateOrderRequestDto request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order by id "
                        + orderId));
        order.setStatus(Status.valueOf(request.getStatus()));
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }
}
