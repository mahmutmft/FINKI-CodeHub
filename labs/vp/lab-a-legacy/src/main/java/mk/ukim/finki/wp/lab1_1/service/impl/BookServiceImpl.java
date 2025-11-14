package mk.ukim.finki.wp.lab1_1.service.impl;

import mk.ukim.finki.wp.lab1_1.model.Author;
import mk.ukim.finki.wp.lab1_1.model.Book;
import mk.ukim.finki.wp.lab1_1.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.lab1_1.repository.AuthorRepository;
import mk.ukim.finki.wp.lab1_1.repository.BookRepository;
import mk.ukim.finki.wp.lab1_1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text, rating);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book create(String title, String genre, Double averageRating, Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(InvalidArgumentsException::new);

        validateBookArguments(title, genre, averageRating);

        Book book = new Book(null, title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long bookId, String title, String genre, Double averageRating, Long authorId) {
        bookRepository.findById(bookId).orElseThrow(InvalidArgumentsException::new);

        Author author = authorRepository.findById(authorId)
                .orElseThrow(InvalidArgumentsException::new);

        validateBookArguments(title, genre, averageRating);

        Book updatedBook = new Book(bookId, title, genre, averageRating, author);
        return bookRepository.save(updatedBook);
    }

    @Override
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    private void validateBookArguments(String title, String genre, Double averageRating) {
        if (title == null || title.isBlank() ||
                genre == null || genre.isBlank() ||
                averageRating == null || averageRating < 0) {
            throw new InvalidArgumentsException();
        }
    }
}
