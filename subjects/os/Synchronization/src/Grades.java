package src;

import java.util.Arrays;
import java.util.Random;

public class Grades {

    static double average = 0;
    // DEFINE OTHER GLOBAL VARIABLES

    static final BoundedRandomGenerator random = new BoundedRandomGenerator();

    private static final int ARRAY_LENGTH = 10000000;

    private static final int NUM_THREADS = 10;


    // TODO: Define sychronization elements
    static long totalSum = 0;
    static void init() {
       totalSum = 0;
    }

    // DO NOT CHANGE
    public static int[] getSubArray(int[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end);
    }

    public static void main(String[] args) {

        init();

        int[] arr = ArrayGenerator.generate(ARRAY_LENGTH);

        // TODO: Make the CalculateThread class a thread and start 10 instances
        // Each instance should take a subarray from the original array with equal length

        CalculateThread calculateThread = new CalculateThread(arr,0,ARRAY_LENGTH); //change this if necessary

        // TODO: Create and start 10 threads for calculating the average grade

        // Replace the call to calculateAverageGrade below with calculateAverageGradeParallel
        average = calculateThread.calculateAverageGrade();

        // TODO: Update the value of the global variable average

        // DO NOT CHANGE

        System.out.println("Your calculated average grade is: " + average);
        System.out.println("The actual average grade is: " + ArrayGenerator.actualAvg);

        SynchronizationChecker.checkResult();

    }


    // TO DO: Make the CalculateThread class a thread, you can add methods and attributes
    static class CalculateThread extends Thread {

        private int[] arr;
        int startSearch;
        int endSearch;

        public CalculateThread(int[] arr, int startSearch, int endSearch) {
            this.arr = arr;
            this.startSearch=startSearch;
            this.endSearch=endSearch;
        }

        public Double calculateAverageGrade() {
            return Arrays.stream(arr).average().getAsDouble();
        }

        @Override
        public void run(){
            long localSum = 0;
            for (int i = startSearch; i < endSearch ; i++) {
                localSum+=arr[i];
            }

            synchronized (Grades.class){
                totalSum+=localSum;
            }
        }
        public void calculateAverageGradeParallel() throws InterruptedException {

            // TO DO: Implement and run this method from the thread
            // The method should not return a result
            // Take care of the propper synchronization
            Thread[] threads = new Thread[NUM_THREADS];
            int chunkSize = arr.length / NUM_THREADS;
            for (int i = 0; i < NUM_THREADS; i++) {
                int start = i * chunkSize;
                int end = (i == NUM_THREADS - 1) ? arr.length : start + chunkSize;

                threads[i] = new CalculateThread(arr, start ,end);
                threads[i].start();
            }

            for (int i = 0; i < NUM_THREADS; i++) {
                threads[i].join();
            }

            average = (double) totalSum / arr.length;
        }
    }

    /******************************************************
     // DO NOT CHANGE THE CODE BELOW TO THE END OF THE FILE
     *******************************************************/

    static class BoundedRandomGenerator {
        static final Random random = new Random();
        static final int RANDOM_BOUND_UPPER = 10;
        static final int RANDOM_BOUND_LOWER = 6;

        public int nextInt() {
            return random.nextInt(RANDOM_BOUND_UPPER - RANDOM_BOUND_LOWER) + RANDOM_BOUND_LOWER;
        }

    }

    static class ArrayGenerator {

        private static double actualAvg = 0;

        static int[] generate(int length) {
            int[] array = new int[length];

            for (int i = 0; i < length; i++) {
                int grade = Grades.random.nextInt();
                actualAvg += grade;
                array[i] = grade;
            }

            actualAvg /= array.length;

            return array;
        }
    }

    static class SynchronizationChecker {
        public static void checkResult() {
            if (ArrayGenerator.actualAvg != average) {
                throw new RuntimeException("The calculated result is not equal to the actual average grade!");
            }
        }
    }
}