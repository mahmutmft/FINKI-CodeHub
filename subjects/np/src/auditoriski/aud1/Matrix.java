package auditoriski.aud1;

import java.util.Scanner;

public class Matrix {

    public static double sum(double[][] a){
        double sum = 0;
        for (double[] doubles : a) {
            for (int j = 0; j < a.length; j++) {
                sum += doubles[j];
            }
        }
        return sum;
    }
    public static double average(double[][] a){
        double average = 0;
        int counter = 0;
        for (double[] doubles : a) {
            for (int j = 0; j < a.length; j++) {
                average += doubles[j];
                counter++;
            }
        }
        return average/counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[][] matrix = new double[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        System.out.println(sum(matrix));
        System.out.println(average(matrix));

    }
}
