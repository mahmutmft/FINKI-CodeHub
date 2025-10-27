package mk.ukim.finki.wp.lab1_1.service;

import mk.ukim.finki.wp.lab1_1.model.BookReservation;

public interface BookReservationService {
    BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies);
}