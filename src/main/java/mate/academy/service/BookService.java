package mate.academy.service;

import java.util.List;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.entity.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();

    Book getBookById(long id);

    Book createBook(CreateBookRequestDto bookDto);
}
