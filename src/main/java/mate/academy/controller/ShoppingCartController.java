package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.AddToCartRequestDto;
import mate.academy.dto.ShoppingCartDto;
import mate.academy.dto.UpdateCartItemDto;
import mate.academy.entity.User;
import mate.academy.service.ShoppingCartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cart")
@RestController
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartServiceImpl shoppingCartService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get a shopping cart", description = "Returns shopping cart")
    @ApiResponse(responseCode = "200", description = "Successfully returned shopping cart")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    public ShoppingCartDto getCart(@AuthenticationPrincipal User user) {
        return shoppingCartService.getCart(user);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Add a new book", description = "Adds a new book in a shopping cart")
    @ApiResponse(responseCode = "201", description = "Book added successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    public ShoppingCartDto addBook(@RequestBody @Valid AddToCartRequestDto request,
                                   @AuthenticationPrincipal User user) {
        return shoppingCartService.addBook(request, user);
    }

    @PutMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Update an item quantity", description
            = "Updates and returns new item quantity in shopping cart")
    @ApiResponse(responseCode = "200", description = "Quantity updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @ApiResponse(responseCode = "404", description = "Item not found")
    public ShoppingCartDto updateItemQuantity(@PathVariable Long cartItemId,
                                              @RequestBody @Valid UpdateCartItemDto itemDto,
                                              @AuthenticationPrincipal User user) {
        return shoppingCartService.update(cartItemId, itemDto, user);
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove an item", description = "Removes an item from shopping cart")
    @ApiResponse(responseCode = "204", description = "Item deleted successfully")
    @ApiResponse(responseCode = "404", description = "Item not found")
    public ShoppingCartDto removeItem(@PathVariable Long cartItemId,
                                      @AuthenticationPrincipal User user) {
        return shoppingCartService.removeItem(cartItemId, user);
    }
}
