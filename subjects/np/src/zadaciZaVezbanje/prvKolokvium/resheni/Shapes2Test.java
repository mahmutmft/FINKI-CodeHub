package zadaciZaVezbanje.prvKolokvium.resheni;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Shapes2Test {

    public static void main(String[] args) throws IOException {

        ShapesApplication shapesApplication = new ShapesApplication(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        shapesApplication.readCanvases(System.in);

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);

    }

    public static class Shape {
        String id;
        int totalShapes;
        int totalCircles;
        int totalSquares;
        double minArea;
        double maxArea;
        double averageArea;
        double sum;

        public Shape(String id, int totalShapes, int totalCircles, int totalSquares, double minArea, double maxArea, double averageArea, double sum) {
            this.id = id;
            this.totalShapes = totalShapes;
            this.totalCircles = totalCircles;
            this.totalSquares = totalSquares;
            this.minArea = minArea;
            this.maxArea = maxArea;
            this.averageArea = averageArea;
            this.sum = sum;
        }

        public String getId() {
            return id;
        }

        public int getTotalShapes() {
            return totalShapes;
        }

        public int getTotalCircles() {
            return totalCircles;
        }

        public int getTotalSquares() {
            return totalSquares;
        }

        public double getMinArea() {
            return minArea;
        }

        public double getMaxArea() {
            return maxArea;
        }

        public double getAverageArea() {
            return averageArea;
        }

        public double getSum() {
            return sum;
        }

        @Override
        public String toString() {
            return String.format("%s %d %d %d %.2f %.2f %.2f%n",id,totalShapes,totalCircles,totalSquares,minArea,maxArea,averageArea);
        }
    }

    public static class ShapesApplication {

        double maxAreaOrginal;
        ArrayList<Shape> shapeArrayList;

        public ShapesApplication(double maxArea) {
            this.maxAreaOrginal = maxArea;
            shapeArrayList = new ArrayList<>();
        }

        public void readCanvases(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                try {
                    if (line.isEmpty()) break;
                    String[] parts = line.trim().split("\\s+");
                    List<Double> area = new ArrayList<>();
                    String id = parts[0];
                    int totalShapes = 0;
                    int totalCircles = 0;
                    int totalSquares = 0;
                    double minArea = 0;
                    double maxArea = 0;
                    double averageArea = 0;
                    double sum = 0;

                    for (int i = 1; i < parts.length; i++) {
                        if (parts[i].equals("C")) {
                            totalCircles++;
                            double r = Double.parseDouble(parts[++i]);
                            r = Math.PI * r * r;
                            sum+=r;
                            if (r > maxAreaOrginal) {
                                throw new IrregularCanvasException(id,maxAreaOrginal);
                            } else area.add(r);
                        } else if (parts[i].equals("S")) {
                            totalSquares++;
                            double r = Double.parseDouble(parts[++i]);
                            r = r*r;
                            sum+=r;
                            if (r > maxAreaOrginal) {
                                throw new IrregularCanvasException(id,maxAreaOrginal);
                            } else area.add(r);
                        }
                    }
                    for (Double element : area) {
                        averageArea += element;
                    }
                    if (!area.isEmpty()) {
                        minArea = Collections.min(area);
                        maxArea = Collections.max(area);
                        averageArea = area.stream().mapToDouble(Double::doubleValue).sum() / area.size();
                    }
                    totalShapes = totalSquares + totalCircles;
                    shapeArrayList.add(new Shape(id, totalShapes, totalCircles, totalSquares, minArea, maxArea, averageArea, sum));

                } catch (IrregularCanvasException e) {
                    System.out.println(e.getMessage());
                }
            }

        }

        void printCanvases(OutputStream os) {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os));
            shapeArrayList.sort(Comparator.comparingDouble(Shape::getSum).reversed());
            for (Shape element : shapeArrayList){
                printWriter.print(element.toString());
            }
            printWriter.flush();
        }
    }

    public static class IrregularCanvasException extends Exception {
        String eid;
        double area;

        public IrregularCanvasException(String eid, double area) {
            super(String.format("Canvas %s has a shape with area larger than %.2f", eid, area));
            this.eid = eid;
            this.area = area;
        }

        public String getId() {
            return eid;
        }

        public double getArea() {
            return area;
        }
    }
}