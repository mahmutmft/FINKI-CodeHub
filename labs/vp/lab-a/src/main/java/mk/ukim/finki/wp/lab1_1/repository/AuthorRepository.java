package mk.ukim.finki.wp.lab1_1.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab1_1.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AuthorRepository {

    private final List<Author> authors = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void init() {
        authors.add(new Author(idGenerator.getAndIncrement(), "Robert", "Martin", "USA", "Known as Uncle Bob, author of Clean Code."));
        authors.add(new Author(idGenerator.getAndIncrement(), "Andrew", "Hunt", "USA", "Co-author of The Pragmatic Programmer."));
        authors.add(new Author(idGenerator.getAndIncrement(), "Patrick", "Rothfuss", "USA", "Fantasy novelist, author of The Name of the Wind."));
    }

    public List<Author> findAll() {
        return authors;
    }

    public Optional<Author> findById(Long id) {
        return authors.stream().filter(a -> a.getId().equals(id)).findFirst();
    }
}

