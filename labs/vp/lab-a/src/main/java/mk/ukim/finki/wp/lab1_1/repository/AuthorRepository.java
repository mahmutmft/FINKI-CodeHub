package mk.ukim.finki.wp.lab1_1.repository;

import mk.ukim.finki.wp.lab1_1.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
