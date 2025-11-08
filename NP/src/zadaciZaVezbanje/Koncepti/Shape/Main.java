package zadaciZaVezbanje.Koncepti.Shape;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle("red", 5));
        shapes.add(new Rectangle("blue", 4, 6));

        for (Shape s : shapes) {
            System.out.println(s);
        }

        double totalArea = 0;
        for (Shape s : shapes) {
            totalArea += s.area();
        }

        System.out.printf("Total area: %.2f\n", totalArea);

        System.out.println("ALL TESTS PASSED");
    }
}

