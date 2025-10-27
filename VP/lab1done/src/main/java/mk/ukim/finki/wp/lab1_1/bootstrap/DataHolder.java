package mk.ukim.finki.wp.lab1_1.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab1_1.model.Book;
import mk.ukim.finki.wp.lab1_1.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DataHolder {

    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init(){
        books.add(new Book("Clean Code", "Programming", 4.8));
        books.add(new Book("The Pragmatic Programmer", "Programming", 4.7));
        books.add(new Book("Design Patterns", "Software Engineering", 4.5));
        books.add(new Book("The Hobbit", "Fantasy", 4.6));
        books.add(new Book("1984", "Dystopian", 4.4));
        books.add(new Book("Dune", "Sci-Fi", 4.7));
        books.add(new Book("Atomic Habits", "Self-help", 4.3));
        books.add(new Book("Sapiens", "History", 4.4));
        books.add(new Book("The Martian", "Sci-Fi", 4.2));
        books.add(new Book("The Name of the Wind", "Fantasy", 4.5));

        reservations.add(new BookReservation("test","test", "test",(long)10));
    }




}
