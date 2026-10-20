package mate.academy.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import mate.academy.entity.OrderItem;
import mate.academy.entity.Status;
import mate.academy.entity.User;

@Getter
@Setter
public class OrderDto {
    private Long id;

    private User user;

    private Status status;

    private BigDecimal total;

    private LocalDateTime orderDate;

    private String shippingAddress;

    private Set<OrderItem> orderItems;
}
