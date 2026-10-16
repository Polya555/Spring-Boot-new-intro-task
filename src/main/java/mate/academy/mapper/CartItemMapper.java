package mate.academy.mapper;

import mate.academy.dto.CartItemDto;
import mate.academy.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDto toDto(CartItem cartItem);
}
