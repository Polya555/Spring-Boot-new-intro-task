package mate.academy.repository;

import java.util.List;
import mate.academy.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByCategoryId(Long categoryId);
}
