package mk.ukim.finki.wp.lab1_1.service;

import mk.ukim.finki.wp.lab1_1.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    Optional<Book> findById(Long id);
    Book create(String title, String genre, Double averageRating, Long authorId);
    Book update(Long bookId, String title, String genre, Double averageRating, Long authorId);
    void delete(Long bookId);
}
