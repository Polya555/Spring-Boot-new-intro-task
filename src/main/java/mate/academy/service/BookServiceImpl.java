package mate.academy.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.BookDto;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.entity.Book;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.BookMapper;
import mate.academy.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto book) {
        Book savedBook = bookRepository.save(bookMapper.toEntity(book));
        return bookMapper.toDto(savedBook);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id " + id)));
    }

    @Override
    public BookDto update(BookDto book) {
        Book existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id " + book.getId()));
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPrice(book.getPrice());
        existingBook.setDescription(book.getDescription());
        existingBook.setCoverImage(book.getCoverImage());
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDto(bookRepository.save(updatedBook));
    }
}
