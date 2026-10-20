package mate.academy.dto;

import mate.academy.entity.Book;
import mate.academy.entity.Order;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Long id;

    private Order order;

    private Book book;

    private int quantity;

    private BigDecimal price;
}
