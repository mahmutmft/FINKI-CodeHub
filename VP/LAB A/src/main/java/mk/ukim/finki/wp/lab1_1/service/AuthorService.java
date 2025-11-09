package mk.ukim.finki.wp.lab1_1.service;

import mk.ukim.finki.wp.lab1_1.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
}

