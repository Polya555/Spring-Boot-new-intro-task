package mate.academy.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.BookDto;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.mapper.BookMapper;
import mate.academy.service.BookService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public BookDto createBook(@RequestBody CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id, @RequestBody CreateBookRequestDto bookDto) {
        return bookService.update(id, bookDto);
    }

    @DeleteMapping("/{id}")
    public BookDto deleteById(@PathVariable Long id) {
        return bookService.deleteById(id);
    }
}
