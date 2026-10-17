package mate.academy.service;

import mate.academy.dto.AddToCartRequestDto;
import mate.academy.dto.ShoppingCartDto;
import mate.academy.dto.UpdateCartItemDto;
import mate.academy.entity.ShoppingCart;
import mate.academy.entity.User;

public interface ShoppingCartService {
    ShoppingCart createCart(User user);

    ShoppingCartDto addBook(AddToCartRequestDto request, User user);

    ShoppingCartDto getCart(User user);

    ShoppingCartDto update(Long cartItemId, UpdateCartItemDto itemDto, User user);

    ShoppingCartDto removeItem(Long cartItemId, User user);
}
