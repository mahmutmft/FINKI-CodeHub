package mk.ukim.finki.wp.lab1_1.repository.impl;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab1_1.model.BookReservation;
import mk.ukim.finki.wp.lab1_1.repository.BookReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {

    private final List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init() {
        reservations.add(new BookReservation("Clean Code", "Test User", "Skopje", 1L));
    }

    @Override
    public BookReservation save(BookReservation reservation){
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public List<BookReservation> findAllByBookTitle(String bookTitle){
        return reservations.stream()
                .filter(c -> c.getBookTitle().equals(bookTitle))
                .toList();
    }

    @Override
    public List<BookReservation> findAll() {
        return reservations;
    }

}
