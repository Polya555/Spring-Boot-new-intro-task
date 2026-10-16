package mate.academy.service;

import mate.academy.dto.AddToCartRequestDto;
import mate.academy.dto.ShoppingCartDto;
import mate.academy.entity.User;

public interface ShoppingCartService {
    ShoppingCartDto addBook(AddToCartRequestDto request, User user);

    ShoppingCartDto getCart(User user);

    ShoppingCartDto update(Long itemId, int quantity, User user);

    ShoppingCartDto removeItem(Long itemId, User user);
}
