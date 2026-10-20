package mate.academy.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private String shippingAddress;

    private List<OrderItemRequestDto> orderItems;
}

