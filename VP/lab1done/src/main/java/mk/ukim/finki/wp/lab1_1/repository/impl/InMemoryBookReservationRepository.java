package mk.ukim.finki.wp.lab1_1.repository.impl;

import mk.ukim.finki.wp.lab1_1.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab1_1.model.BookReservation;
import mk.ukim.finki.wp.lab1_1.repository.BookReservationRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {

    @Override
    public BookReservation save(BookReservation reservation){
        DataHolder.reservations.add(reservation);
        return reservation;
    }

    @Override
    public List<BookReservation> findAllByBookTitle(String bookTitile){
        return DataHolder.reservations.stream().filter(c -> c.getBookTitle().equals(bookTitile)).toList();
    }

}
