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
import mate.academy.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Override
    public OrderDto save(OrderRequestDto orderDto) {
        Order savedOrder = orderRepository.save(orderMapper.toEntity(orderDto));
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDto getById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order by id " + id)));
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderItemDto> getOrderItems(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order by id " + orderId));
        return order.getOrderItems()
                .stream()
                .map(orderMapper::toItemDto)
                .toList();
    }

    @Override
    public OrderItemDto getOrderItem(Long orderId, Long itemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order by id " + orderId));
        OrderItem item = order.getOrderItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Can't find item by id " + itemId));
        return orderMapper.toItemDto(item);
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, UpdateOrderRequestDto request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order by id " + orderId));
        order.setStatus(Status.valueOf(request.getStatus()));
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }
}
