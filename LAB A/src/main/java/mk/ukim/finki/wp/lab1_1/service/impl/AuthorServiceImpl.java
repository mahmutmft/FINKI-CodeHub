package mk.ukim.finki.wp.lab1_1.service.impl;

import mk.ukim.finki.wp.lab1_1.model.Author;
import mk.ukim.finki.wp.lab1_1.repository.AuthorRepository;
import mk.ukim.finki.wp.lab1_1.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }
}

