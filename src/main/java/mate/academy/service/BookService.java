package mate.academy.service;

import mate.academy.dto.BookDto;
import mate.academy.dto.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    BookDto save(CreateBookRequestDto book);

    BookDto update(Long id, CreateBookRequestDto bookDto);

    void deleteById(Long id);
}
