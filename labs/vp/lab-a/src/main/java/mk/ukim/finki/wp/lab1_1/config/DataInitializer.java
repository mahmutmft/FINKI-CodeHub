package mk.ukim.finki.wp.lab1_1.config;

import mk.ukim.finki.wp.lab1_1.model.Author;
import mk.ukim.finki.wp.lab1_1.model.Book;
import mk.ukim.finki.wp.lab1_1.repository.AuthorRepository;
import mk.ukim.finki.wp.lab1_1.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    @Profile({"prod", "h2"})
    CommandLineRunner initData(AuthorRepository authorRepository, BookRepository bookRepository) {
        return args -> {
            if (authorRepository.count() > 0 || bookRepository.count() > 0) {
                return;
            }

            Author martin = authorRepository.save(new Author(null, "Robert", "Martin", "USA", "Known as Uncle Bob, author of Clean Code."));
            Author hunt = authorRepository.save(new Author(null, "Andrew", "Hunt", "USA", "Co-author of The Pragmatic Programmer."));
            Author rothfuss = authorRepository.save(new Author(null, "Patrick", "Rothfuss", "USA", "Fantasy novelist, author of The Name of the Wind."));

            bookRepository.save(new Book(null, "Clean Code", "Programming", 4.8, martin, 0));
            bookRepository.save(new Book(null, "The Pragmatic Programmer", "Programming", 4.7, hunt, 0));
            bookRepository.save(new Book(null, "Design Patterns", "Software Engineering", 4.5, martin, 0));
            bookRepository.save(new Book(null, "The Hobbit", "Fantasy", 4.6, rothfuss, 0));
            bookRepository.save(new Book(null, "1984", "Dystopian", 4.4, hunt, 0));
            bookRepository.save(new Book(null, "Dune", "Sci-Fi", 4.7, rothfuss, 0));
            bookRepository.save(new Book(null, "Atomic Habits", "Self-help", 4.3, hunt, 0));
            bookRepository.save(new Book(null, "Sapiens", "History", 4.4, martin, 0));
            bookRepository.save(new Book(null, "The Martian", "Sci-Fi", 4.2, rothfuss, 0));
            bookRepository.save(new Book(null, "The Name of the Wind", "Fantasy", 4.5, rothfuss, 0));
        };
    }
}
