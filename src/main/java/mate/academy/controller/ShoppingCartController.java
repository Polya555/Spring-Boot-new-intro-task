package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.AddToCartRequestDto;
import mate.academy.dto.ShoppingCartDto;
import mate.academy.entity.User;
import mate.academy.service.ShoppingCartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/cart")
@RestController
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartServiceImpl shoppingCartService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Get a shopping cart", description = "Returns shopping cart")
    @ApiResponse(responseCode = "200", description = "Successfully returned shopping cart")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    public ShoppingCartDto getCart(@AuthenticationPrincipal User user) {
        return shoppingCartService.getCart(user);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Add a new book", description = "Adds a new book in a shopping cart")
    @ApiResponse(responseCode = "201", description = "Book added successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    public ShoppingCartDto addBook(@RequestBody AddToCartRequestDto request,
                                   @AuthenticationPrincipal User user) {
        return shoppingCartService.addBook(request, user);
    }

    @PutMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Update an item quantity", description
            = "Updates and returns new item quantity in shopping cart")
    @ApiResponse(responseCode = "200", description = "Quantity updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @ApiResponse(responseCode = "404", description = "Item not found")
    public ShoppingCartDto updateItemQuantity(Long itemId, @RequestBody int quantity,
                                              @AuthenticationPrincipal User user) {
        return shoppingCartService.update(itemId, quantity, user);
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove an item", description = "Removes an item from shopping cart")
    @ApiResponse(responseCode = "204", description = "Item deleted successfully")
    @ApiResponse(responseCode = "404", description = "Item not found")
    public ShoppingCartDto removeItem(Long itemId, @AuthenticationPrincipal User user) {
        return shoppingCartService.removeItem(itemId, user);
    }
}
