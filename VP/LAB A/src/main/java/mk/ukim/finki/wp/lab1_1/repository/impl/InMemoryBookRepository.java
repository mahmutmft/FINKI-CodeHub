package mk.ukim.finki.wp.lab1_1.repository.impl;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab1_1.model.Author;
import mk.ukim.finki.wp.lab1_1.model.Book;
import mk.ukim.finki.wp.lab1_1.repository.AuthorRepository;
import mk.ukim.finki.wp.lab1_1.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final List<Book> books = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final AuthorRepository authorRepository;

    public InMemoryBookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void init() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            return;
        }
        books.add(new Book(idGenerator.getAndIncrement(), "Clean Code", "Programming", 4.8, authors.get(0)));
        books.add(new Book(idGenerator.getAndIncrement(), "The Pragmatic Programmer", "Programming", 4.7, authors.get(Math.min(1, authors.size() - 1))));
        books.add(new Book(idGenerator.getAndIncrement(), "Design Patterns", "Software Engineering", 4.5, authors.get(0)));
        books.add(new Book(idGenerator.getAndIncrement(), "The Hobbit", "Fantasy", 4.6, authors.get(Math.min(2, authors.size() - 1))));
        books.add(new Book(idGenerator.getAndIncrement(), "1984", "Dystopian", 4.4, authors.get(Math.min(1, authors.size() - 1))));
        books.add(new Book(idGenerator.getAndIncrement(), "Dune", "Sci-Fi", 4.7, authors.get(Math.min(2, authors.size() - 1))));
        books.add(new Book(idGenerator.getAndIncrement(), "Atomic Habits", "Self-help", 4.3, authors.get(Math.min(1, authors.size() - 1))));
        books.add(new Book(idGenerator.getAndIncrement(), "Sapiens", "History", 4.4, authors.get(Math.min(0, authors.size() - 1))));
        books.add(new Book(idGenerator.getAndIncrement(), "The Martian", "Sci-Fi", 4.2, authors.get(Math.min(2, authors.size() - 1))));
        books.add(new Book(idGenerator.getAndIncrement(), "The Name of the Wind", "Fantasy", 4.5, authors.get(Math.min(2, authors.size() - 1))));
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return books.stream()
                .filter(book -> text == null || text.isBlank() || book.getTitle().toLowerCase().contains(text.toLowerCase()))
                .filter(book -> rating == null || book.getAverageRating() >= rating)
                .toList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.getAndIncrement());
            books.add(book);
            return book;
        }

        deleteById(book.getId());
        books.add(book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }
}
