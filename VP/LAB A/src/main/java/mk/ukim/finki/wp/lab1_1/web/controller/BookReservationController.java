package mk.ukim.finki.wp.lab1_1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab1_1.model.BookReservation;
import mk.ukim.finki.wp.lab1_1.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookReservationController {

    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping("/bookReservation")
    public String createReservation(@RequestParam String bookTitle,
                                    @RequestParam String readerName,
                                    @RequestParam String readerAddress,
                                    @RequestParam int numCopies,
                                    HttpServletRequest request,
                                    Model model) {

        BookReservation reservation = bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);
        String clientIp = request.getRemoteAddr();

        model.addAttribute("readerName", reservation.getReaderName());
        model.addAttribute("clientIp", clientIp);
        model.addAttribute("bookTitle", reservation.getBookTitle());
        model.addAttribute("numberOfCopies", reservation.getNumberOfCopies());

        return "reservationConfirmation";
    }
}

