package mate.academy.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import mate.academy.entity.CartItem;

@Getter
@Setter
public class ShoppingCartDto {
    private Long id;

    private Long userId;

    private Set<CartItem> cartItems;
}
