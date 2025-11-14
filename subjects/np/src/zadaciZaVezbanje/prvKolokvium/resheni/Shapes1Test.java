package zadaciZaVezbanje.prvKolokvium.resheni;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Shapes1Test {
    public static void main(String[] args) throws IOException {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);
    }

    public static class ShapesApplication {
        private final List<Shapes> shapes = new ArrayList<>();
        String id;
        int squareCount;
        int totalSquarePerimeter;

        public int readCanvases(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            int counter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    if (line.isEmpty()) {
                        continue;
                    }
                    int squareCounter = 0;
                    String[] parts = line.trim().split("\\s+");
                    String id = parts[0];
                    int sum = 0;
                    for (int i = 1; i < parts.length; i++) {
                        parts[i] = String.valueOf(Integer.parseInt(parts[i]) * 4);
                        sum += Integer.parseInt(parts[i]);
                    }
                    squareCount = parts.length - 1;
                    counter += parts.length - 1;
                    shapes.add(new Shapes(id, squareCount, sum));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return counter;
        }

        public void printLargestCanvasTo(OutputStream outputStream) {
            if (shapes.isEmpty()) return;
            Shapes best = shapes.get(0);
            for (int i = 1; i < shapes.size(); i++) {
                Shapes s = shapes.get(i);
                if (best.getSum()<s.getSum()){
                    best = s;
                }
            }
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
            printWriter.printf("%s %d %d%n",best.getId(), best.getSquareCount(), best.getSum());
            printWriter.flush();
        }
    }

    public static class Shapes {
        String id;
        int squareCount;
        int sum;

        public Shapes(String id, int squareCount, int sum) {
            this.id = id;
            this.squareCount = squareCount;
            this.sum = sum;
        }

        public String getId() {
            return id;
        }

        public int getSquareCount() {
            return squareCount;
        }

        public int getSum() {
            return sum;
        }

        @Override
        public String toString() {
            return String.format("%s %d %d", id, squareCount, sum);
        }
    }
}
