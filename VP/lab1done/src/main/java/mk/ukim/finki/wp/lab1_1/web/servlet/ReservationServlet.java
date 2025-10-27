package mk.ukim.finki.wp.lab1_1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab1_1.model.BookReservation;
import mk.ukim.finki.wp.lab1_1.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReservationServlet", urlPatterns = "/lab")
public class ReservationServlet extends HttpServlet {

    private BookReservationService bookReservationService;
    private SpringTemplateEngine springTemplateEngine;

    public ReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine springTemplateEngine) {
        this.bookReservationService = bookReservationService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookTitle = req.getParameter("bookTitle");
        List<BookReservation> reservations = bookReservationService.getReservationForBook(bookTitle);

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("bookTitle", bookTitle);
        context.setVariable("reservations", reservations);

        springTemplateEngine.process("reservation.html", context, resp.getWriter());
    }
}
