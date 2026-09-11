package mate.academy.repository;

import jakarta.persistence.PersistenceUnit;
import java.util.List;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.entity.Book;
import mate.academy.exception.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BookRepositoryImpl implements BookRepository {
    @PersistenceUnit
    private SessionFactory factory;

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add book :" + book, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Book", Book.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Books can`t be found", e);
        }
    }

    @Override
    public Book getBookById(long id) {
        try (Session session = factory.openSession()) {
            Book book = session.get(Book.class, id);
            if (book == null) {
                throw new EntityNotFoundException("Can't find book by id: " + id);
            }
            return book;
        }
    }

    @Override
    public Book createBook(CreateBookRequestDto bookDto) {
        Session session = null;
        org.hibernate.Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Book book = new Book(bookDto);
            session.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't create " + bookDto, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
