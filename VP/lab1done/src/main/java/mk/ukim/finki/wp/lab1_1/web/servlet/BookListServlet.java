package mk.ukim.finki.wp.lab1_1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab1_1.model.Book;
import mk.ukim.finki.wp.lab1_1.repository.impl.InMemoryBookRepository;
import mk.ukim.finki.wp.lab1_1.service.BookService;
import mk.ukim.finki.wp.lab1_1.service.impl.BookServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookListServlet", urlPatterns = "/book")
public class BookListServlet extends HttpServlet {

    private BookService bookService;
    private SpringTemplateEngine springTemplateEngine;

    public BookListServlet(BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String searchText = req.getParameter("searchText");
        String searchRating = req.getParameter("searchRating");

        List<Book> books;
        if (searchText != null && !searchText.isEmpty() && searchRating != null && !searchRating.isEmpty()) {
            double rating = Double.parseDouble(searchRating);
            books = bookService.searchBooks(searchText, rating);
        } else {
            books = bookService.listAll();
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("books", books);

        springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }
}

