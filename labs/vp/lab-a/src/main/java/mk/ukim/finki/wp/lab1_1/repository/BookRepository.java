package mk.ukim.finki.wp.lab1_1.repository;

import mk.ukim.finki.wp.lab1_1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthor_NameContainingIgnoreCase(String authorName);

    List<Book> findByTitleContainingIgnoreCaseAndAuthor_NameContainingIgnoreCase(String title, String authorName);

    List<Book> findAllByAuthor_Id(Long authorId);
}
