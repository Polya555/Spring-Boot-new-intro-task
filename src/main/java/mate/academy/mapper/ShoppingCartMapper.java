package mate.academy.mapper;

import mate.academy.dto.ShoppingCartDto;
import mate.academy.entity.ShoppingCart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    ShoppingCartDto toDto(ShoppingCart shoppingCart);
}

