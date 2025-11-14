package mk.ukim.finki.wp.lab1_1.web.controller;

import mk.ukim.finki.wp.lab1_1.model.BookReservation;
import mk.ukim.finki.wp.lab1_1.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReservationController {

    private final BookReservationService bookReservationService;

    public ReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @GetMapping("/lab")
    public String getReservations(@RequestParam(required = false) String bookTitle, Model model) {
        boolean showAll = bookTitle == null || bookTitle.isBlank();

        List<BookReservation> reservations = showAll
                ? bookReservationService.listAllReservations()
                : bookReservationService.getReservationForBook(bookTitle);

        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("showAll", showAll);
        model.addAttribute("reservations", reservations);

        return "reservation";
    }
}

