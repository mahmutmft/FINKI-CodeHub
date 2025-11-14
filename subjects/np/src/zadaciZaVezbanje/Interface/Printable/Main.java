package zadaciZaVezbanje.Interface.Printable;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Printable> items = new ArrayList<>();
        items.add(new Document("OOP Summary"));
        items.add(new Photo("photo.jpg"));

        for (Printable e: items){
            e.print();
        }

        System.out.println("ALL TEST PASSED");
    }
}
