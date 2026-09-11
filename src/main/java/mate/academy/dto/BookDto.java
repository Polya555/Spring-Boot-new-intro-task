package mate.academy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mate.academy.entity.Book;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class BookDto {
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private BigDecimal price;

    private String description;

    private String coverImage;

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.price = book.getPrice();
        this.description = book.getDescription();
        this.coverImage = book.getCoverImage();
    }
}
