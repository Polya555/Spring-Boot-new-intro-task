package mate.academy.service;

import lombok.RequiredArgsConstructor;
import mate.academy.dto.BookDto;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.entity.Book;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.BookMapper;
import mate.academy.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id " + id)));
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto bookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find book to update by id "
                        + id));
        bookMapper.updateBookFromDto(bookDto, existingBook);
        return bookMapper.toDto(bookRepository.save(existingBook));
    }

    @Override
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't find book to delete by id " + id);
        }
        bookRepository.deleteById(id);
    }
}
