package mk.ukim.finki.wp.lab1_1.repository.impl;

import mk.ukim.finki.wp.lab1_1.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab1_1.model.Book;
import mk.ukim.finki.wp.lab1_1.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryBookRepository implements BookRepository {

    @Override
    public List<Book> findAll(){
        return DataHolder.books;
    }
    public List<Book> searchBooks(String text, Double rating){
        return DataHolder.books.stream()
                .filter(obj -> obj.getTitle().contains(text) && obj.getAverageRating() >= rating )
                .toList();
    }
}
