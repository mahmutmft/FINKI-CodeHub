package mk.ukim.finki.wp.lab1_1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab1_1.model.BookReservation;
import mk.ukim.finki.wp.lab1_1.repository.impl.InMemoryBookReservationRepository;
import mk.ukim.finki.wp.lab1_1.service.BookReservationService;
import mk.ukim.finki.wp.lab1_1.service.impl.BookReservationServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {

    private BookReservationService bookReservationService;
    private SpringTemplateEngine springTemplateEngine;

    @Override
    public void init() throws ServletException {
        InMemoryBookReservationRepository bookReservationRepository = new InMemoryBookReservationRepository();
        this.bookReservationService = new BookReservationServiceImpl(bookReservationRepository);

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");

        this.springTemplateEngine = new SpringTemplateEngine();
        this.springTemplateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        String numCopies = req.getParameter("numCopies");

        int numberOfCopies = Integer.parseInt(numCopies);
        String clientIp = req.getRemoteAddr();

        BookReservation reservation = bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numberOfCopies);

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("readerName", readerName);
        context.setVariable("clientIp", clientIp);
        context.setVariable("bookTitle", bookTitle);
        context.setVariable("numberOfCopies", numberOfCopies);

        springTemplateEngine.process("reservationConfirmation.html", context, resp.getWriter());
    }
}

