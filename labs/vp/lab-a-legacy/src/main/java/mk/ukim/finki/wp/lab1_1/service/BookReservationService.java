package mk.ukim.finki.wp.lab1_1.service;

import mk.ukim.finki.wp.lab1_1.model.BookReservation;

import java.util.List;

public interface BookReservationService {
    BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies);
    List<BookReservation> getReservationForBook(String bookTitle);
    List<BookReservation> listAllReservations();
}
