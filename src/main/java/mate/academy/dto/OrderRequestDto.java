package mate.academy.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private String shippingAddress;

    private List<OrderItemRequestDto> orderItems;
}

