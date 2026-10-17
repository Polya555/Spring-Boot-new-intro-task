package mate.academy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCartItemDto {
    @NotNull
    private Long bookId;

    private int quantity;
}
