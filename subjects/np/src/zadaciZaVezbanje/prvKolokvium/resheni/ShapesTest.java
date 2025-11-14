package zadaciZaVezbanje.prvKolokvium.resheni;

import java.util.ArrayList;
import java.util.Scanner;

public class ShapesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Canvas canvas = new Canvas();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            int type = Integer.parseInt(parts[0]);
            String id = parts[1];
            if (type == 1) {
                Color color = Color.valueOf(parts[2]);
                float radius = Float.parseFloat(parts[3]);
                canvas.add(id, color, radius);
            } else if (type == 2) {
                Color color = Color.valueOf(parts[2]);
                float width = Float.parseFloat(parts[3]);
                float height = Float.parseFloat(parts[4]);
                canvas.add(id, color, width, height);
            } else if (type == 3) {
                float scaleFactor = Float.parseFloat(parts[2]);
                System.out.println("ORIGNAL:");
                System.out.print(canvas);
                canvas.scale(id, scaleFactor);
                System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
                System.out.print(canvas);
            }

        }
    }

    public enum Color {
        RED, GREEN, BLUE
    }

    public static interface Scalable {
        void scale(float scaleFactor);
    }

    public static interface Stackable {
        float weight();
    }

    public static class Canvas {
        ArrayList<Shape> shapes;

        public Canvas() {
            this.shapes = new ArrayList<>();
        }

        void add(String id, Color color, float radius) {
            Shape s = new Circle(id, color, radius);
            shapes.add(s);
            sort(shapes);
        }

        void add(String id, Color color, float width, float height) {
            Square s = new Square(id, color, width, height);
            shapes.add(s);
            sort(shapes);
        }

        public void scale(String id, float scaleFactor) {
            sort(shapes);
            for (Shape shape : shapes) {
                if (shape.getId().equals(id)) {
                    shape.scale(scaleFactor);
                }
            }
        }

        private void sort(ArrayList<Shape> list) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = 0; j < list.size() - i - 1; j++) {
                    if (list.get(j).weight() < list.get(j + 1).weight()) {
                        Shape temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Shape s : shapes) {
                sb.append(String.format("%c: %-3s %10s %.2f3%n", s.typeCode(), s.getId(), s.getColor(), s.weight()));
            }
            return sb.toString();
        }

    }

    public static class Circle extends Shape {
        float radius;

        public Circle(String id, Color color, float radius) {
            super(id, color);
            this.radius = radius;
        }

        @Override
        public float weight() {
            return (float) (Math.PI * radius * radius);
        }

        @Override
        public void scale(float scaleFactor) {
            radius *= scaleFactor;
        }

        @Override
        public char typeCode() {
            return 'C';
        }

    }

    public abstract static class Shape implements Stackable, Scalable {
        String id;
        Color color;

        public Shape(String id, Color color) {
            this.id = id;
            this.color = color;
        }

        public String getId() {
            return id;
        }

        public Color getColor() {
            return color;
        }

        @Override
        public void scale(float scaleFactor) {

        }

        @Override
        public float weight() {
            return 0;
        }

        public abstract char typeCode();
    }

    public static class Square extends Shape {

        float widht;
        float height;

        public Square(String id, Color color, float widht, float height) {
            super(id, color);
            this.widht = widht;
            this.height = height;
        }

        public Square(String id, Color color) {
            super(id, color);
        }

        @Override
        public float weight() {
            return widht * height;
        }

        @Override
        public void scale(float scaleFactor) {
            this.height = this.height * scaleFactor;
            this.widht = this.widht * scaleFactor;
        }

        @Override
        public char typeCode() {
            return 'R';
        }

    }
}
