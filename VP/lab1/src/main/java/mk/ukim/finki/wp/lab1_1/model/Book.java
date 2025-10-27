package mk.ukim.finki.wp.lab1_1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    String title;
    String genre;
    double averageRating;

}
