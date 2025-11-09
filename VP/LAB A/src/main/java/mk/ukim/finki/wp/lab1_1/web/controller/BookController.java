package mk.ukim.finki.wp.lab1_1.web.controller;

import mk.ukim.finki.wp.lab1_1.model.Book;
import mk.ukim.finki.wp.lab1_1.service.AuthorService;
import mk.ukim.finki.wp.lab1_1.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String redirectToBooks() {
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String getBooksPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String searchText,
                               @RequestParam(required = false) String searchRating,
                               Model model) {

        Double ratingFilter = null;
        if (searchRating != null && !searchRating.isBlank()) {
            try {
                ratingFilter = Double.parseDouble(searchRating);
            } catch (NumberFormatException ignored) {
                ratingFilter = null;
            }
        }

        List<Book> books;
        if (searchText != null && !searchText.isBlank() && ratingFilter != null) {
            books = bookService.searchBooks(searchText, ratingFilter);
        } else {
            books = bookService.listAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("error", error);
        model.addAttribute("searchText", searchText);
        model.addAttribute("searchRating", searchRating);

        return "listBooks";
    }

    @GetMapping("/books/book-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("isEdit", false);
        return "book-form";
    }

    @GetMapping("/books/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        return bookService.findById(id)
                .map(book -> {
                    model.addAttribute("book", book);
                    model.addAttribute("authors", authorService.findAll());
                    model.addAttribute("isEdit", true);
                    return "book-form";
                })
                .orElse("redirect:/books?error=BookNotFound");
    }

    @PostMapping("/books/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.create(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/books/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.update(bookId, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
