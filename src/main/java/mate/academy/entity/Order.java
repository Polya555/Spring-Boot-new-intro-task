package mate.academy.entity;

import java.util.Set;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@Table(name = "orders")
@SQLRestriction("is_deleted = false")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private Status status;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems;

    @Column(nullable = false)
    private boolean isDeleted = false;
}
