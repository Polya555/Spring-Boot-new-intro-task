package mate.academy.service;

import java.util.List;
import mate.academy.dto.CategoryDto;
import mate.academy.dto.CreateCategoryRequestDto;

public interface CategoryService {
    List<CategoryDto> findAll();

    CategoryDto getById(Long id);

    CategoryDto save(CreateCategoryRequestDto categoryDto);

    CategoryDto update(Long id, CreateCategoryRequestDto categoryDto);

    void deleteById(Long id);
}
