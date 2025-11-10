package mk.ukim.finki.wp.lab1_1.repository;

import mk.ukim.finki.wp.lab1_1.model.BookReservation;

import java.util.List;

public interface BookReservationRepository {
    BookReservation save(BookReservation reservation);
    List<BookReservation> findAllByBookTitle(String bookTitile);
    List<BookReservation> findAll();
}
