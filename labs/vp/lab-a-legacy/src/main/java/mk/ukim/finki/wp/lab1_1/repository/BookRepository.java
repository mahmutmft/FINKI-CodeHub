package mk.ukim.finki.wp.lab1_1.repository;

import mk.ukim.finki.wp.lab1_1.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    Book save(Book book);
    void deleteById(Long id);
    Optional<Book> findById(Long id);
}
