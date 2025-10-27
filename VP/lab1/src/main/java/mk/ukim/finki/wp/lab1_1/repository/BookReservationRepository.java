package mk.ukim.finki.wp.lab1_1.repository;

import mk.ukim.finki.wp.lab1_1.model.BookReservation;

public interface BookReservationRepository {
    BookReservation save(BookReservation reservation);
}
