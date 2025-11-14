package mk.ukim.finki.wp.lab1_1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String genre;
    private double averageRating;
    private Author author;
    private int likes;

    public Book(Long id, String title, String genre, double averageRating, Author author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.author = author;
        this.likes = 0; // default
    }

}
