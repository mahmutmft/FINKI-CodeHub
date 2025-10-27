package mk.ukim.finki.wp.lab1_1.repository;

import mk.ukim.finki.wp.lab1_1.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
}