package mate.academy.service;

import java.util.HashSet;
import java.util.Optional;
import mate.academy.dto.AddToCartRequestDto;
import mate.academy.dto.ShoppingCartDto;
import mate.academy.entity.Book;
import mate.academy.entity.CartItem;
import mate.academy.entity.ShoppingCart;
import mate.academy.entity.User;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.ShoppingCartMapper;
import mate.academy.repository.BookRepository;
import mate.academy.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    private ShoppingCart createCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCartDto addBook(AddToCartRequestDto request, User user) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id"));
        ShoppingCart cart = shoppingCartRepository.findByUser(user)
                .orElseGet(() -> createCart(user));
        if (cart.getCartItems() == null) {
            cart.setCartItems(new HashSet<>());
        }
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(request.getBookId()))
                .findFirst();
        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + request.getQuantity());
        } else {
            CartItem newItem = new CartItem();
            newItem.setBook(book);
            newItem.setQuantity(request.getQuantity());
            newItem.setShoppingCart(cart);
            cart.getCartItems().add(newItem);
        }
        return shoppingCartMapper.toDto(shoppingCartRepository.save(cart));
    }

    public ShoppingCartDto getCart(User user) {
        return shoppingCartMapper.toDto(shoppingCartRepository.findByUser(user)
                .orElseGet(() -> createCart(user)));
    }

    public ShoppingCartDto update(Long itemId, int quantity, User user) {
        ShoppingCart cart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        CartItem item = cart.getCartItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));
        item.setQuantity(quantity);
        return shoppingCartMapper.toDto(shoppingCartRepository.save(cart));
    }

    public ShoppingCartDto removeItem(Long itemId, User user) {
        ShoppingCart cart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        CartItem item = cart.getCartItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));
        cart.getCartItems().remove(item);
        return shoppingCartMapper.toDto(shoppingCartRepository.save(cart));
    }
}
