package mate.academy.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.AddToCartRequestDto;
import mate.academy.dto.ShoppingCartDto;
import mate.academy.dto.UpdateCartItemDto;
import mate.academy.entity.Book;
import mate.academy.entity.CartItem;
import mate.academy.entity.ShoppingCart;
import mate.academy.entity.User;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.ShoppingCartMapper;
import mate.academy.repository.BookRepository;
import mate.academy.repository.CartItemRepository;
import mate.academy.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final BookRepository bookRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    public ShoppingCart createCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartDto addBook(AddToCartRequestDto request, User user) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id"
                        + request.getBookId()));
        ShoppingCart cart = shoppingCartRepository.findByUser(user)
                .orElseGet(() -> createCart(user));
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(request.getBookId()))
                .findFirst();
        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity()
                    + request.getQuantity());
        } else {
            CartItem newItem = new CartItem();
            newItem.setBook(book);
            newItem.setQuantity(request.getQuantity());
            newItem.setShoppingCart(cart);
            cart.getCartItems().add(newItem);
        }
        return shoppingCartMapper.toDto(shoppingCartRepository.save(cart));
    }

    @Override
    public ShoppingCartDto getCart(User user) {
        return shoppingCartMapper.toDto(shoppingCartRepository.findByUser(user)
                .orElseGet(() -> createCart(user)));
    }

    @Override
    public ShoppingCartDto update(Long cartItemId, UpdateCartItemDto itemDto, User user) {
        ShoppingCart cart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found" + cartItemId));
        CartItem item = cartItemRepository.findByIdAndShoppingCartId(cartItemId, cart.getId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found id: " + cartItemId));
        item.setQuantity(itemDto.getQuantity());
        return shoppingCartMapper.toDto(shoppingCartRepository.save(cart));
    }

    @Override
    public ShoppingCartDto removeItem(Long cartItemId, User user) {
        ShoppingCart cart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found" + cartItemId));
        CartItem item = cartItemRepository.findByIdAndShoppingCartId(cartItemId, cart.getId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found id: " + cartItemId));
        cartItemRepository.delete(item);
        return shoppingCartMapper.toDto(shoppingCartRepository.save(cart));
    }
}
