package mate.academy.dto;

import lombok.Getter;
import lombok.Setter;
import mate.academy.entity.Book;

@Getter
@Setter
public class OrderItemDto {
    private Long id;

    private Long orderId;

    private Book book;

    private int quantity;
}
