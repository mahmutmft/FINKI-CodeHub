// src/main/java/mk/ukim/finki/wp/lab1_1/service/impl/BookServiceImpl.java
package mk.ukim.finki.wp.lab1_1.service.impl;

import mk.ukim.finki.wp.lab1_1.model.Author;
import mk.ukim.finki.wp.lab1_1.model.Book;
import mk.ukim.finki.wp.lab1_1.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.lab1_1.repository.AuthorRepository;
import mk.ukim.finki.wp.lab1_1.repository.BookRepository;
import mk.ukim.finki.wp.lab1_1.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
    public List<Book> listAllByAuthorId(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }

    @Override
    public List<Book> searchBooks(String title, String authorName) {
        boolean hasTitle = title != null && !title.isBlank();
        boolean hasAuthorName = authorName != null && !authorName.isBlank();

        if (hasTitle && hasAuthorName) {
            return bookRepository.findByTitleContainingIgnoreCaseAndAuthor_NameContainingIgnoreCase(title, authorName);
        }
        if (hasTitle) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        }
        if (hasAuthorName) {
            return bookRepository.findByAuthor_NameContainingIgnoreCase(authorName);
        }
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book create(String title, String genre, Double averageRating, Long authorId) {
        validateBookArguments(title, genre, averageRating);
        Author author = authorRepository.findById(authorId).orElseThrow();
        Book book = new Book(null, title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long bookId, String title, String genre, Double averageRating, Long authorId) {
        validateBookArguments(title, genre, averageRating);
        Book book = bookRepository.findById(bookId).orElseThrow();
        Author author = authorRepository.findById(authorId).orElseThrow();

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    // NEW:
    @Override
    public Book like(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        book.setLikes(book.getLikes() + 1);
        return bookRepository.save(book);
    }

    private void validateBookArguments(String title, String genre, Double averageRating) {
        if (title == null || title.isBlank() ||
                genre == null || genre.isBlank() ||
                averageRating == null || averageRating < 0) {
            throw new InvalidArgumentsException();
        }
    }
}
