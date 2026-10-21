package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.OrderDto;
import mate.academy.dto.OrderItemDto;
import mate.academy.dto.OrderRequestDto;
import mate.academy.dto.UpdateOrderRequestDto;
import mate.academy.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management", description = "Endpoints for managing orders")
@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create order", description = "Creates and returns a new order")
    @ApiResponse(responseCode = "200", description = "Successfully created order")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    public OrderDto createOrder(@Valid @RequestBody OrderRequestDto orderDto) {
        return orderService.save(orderDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Get all orders", description = "Returns list of orders")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved order")
    @ApiResponse(responseCode = "400", description = "Invalid parameters")
    public Page<OrderDto> getAll(Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @GetMapping("/{orderId}/items")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all order items", description = "Returns list of items")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved order items")
    @ApiResponse(responseCode = "400", description = "Invalid parameters")
    public List<OrderItemDto> getOrderItems(@PathVariable Long orderId, @PathVariable Long userId) {
        return orderService.getOrderItems(orderId, userId);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get order item by ID", description = "Returns an item by its ID")
    @ApiResponse(responseCode = "200", description = "Item found")
    @ApiResponse(responseCode = "404", description = "Item not found")
    public OrderItemDto getOrderItem(@PathVariable Long orderItemId, @PathVariable Long orderId,
                                     @PathVariable Long userId) {
        return orderService.getOrderItem(orderItemId, orderId, userId);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update status of an order", description = "Updates status")
    @ApiResponse(responseCode = "200", description = "Status updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public OrderDto updateStatus(@PathVariable Long id,
                                 @RequestBody UpdateOrderRequestDto request) {
        return orderService.updateOrderStatus(id, request);
    }
}
