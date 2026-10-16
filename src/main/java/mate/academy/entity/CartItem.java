package mate.academy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (nullable = false)
    private ShoppingCart shoppingCart;

    @Column(nullable = false)
    private Book book;

    @Column(nullable = false)
    private int quantity;
}
