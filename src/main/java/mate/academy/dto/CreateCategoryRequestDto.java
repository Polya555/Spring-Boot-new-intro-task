package mate.academy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCategoryRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;
}
