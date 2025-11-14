package monitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TemperatureMonitor {
    public static void main(String[] args) {
        while (true) {
            ArrayList<Integer> average = new ArrayList<>();
            try {
                File readFile = new File("temperature.txt");
                Scanner myReader = new Scanner(readFile);

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] numbers = data.split(" ");
                    for (String num : numbers) {
                        try {
                            int temperature = Integer.parseInt(num);
                            average.add(temperature);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number format: " + num);
                        }
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            if (!average.isEmpty()) {
                int sum = 0;
                for (Integer number : average) {
                    sum += number;
                }

                double avg = (double) sum / average.size();
                System.out.println("Average temperature: " + avg);

                String level = "";
                if (avg >= 5 && avg < 19) {
                    level = "Low";
                } else if (avg >= 19 && avg <= 35) {
                    level = "Medium";
                } else if (avg > 35) {
                    level = "High";
                } else {
                    level = "Unknown";
                }
                
                try {
                    FileWriter writeToFile = new FileWriter("/data/temperaturelevel.txt", true);
                    writeToFile.write(level + " ");
                    writeToFile.close();
                    System.out.println("Temperature level (" + level + ") written to file.");
                } catch (IOException e) {
                    System.out.println("Error writing temperature level to file");
                    e.printStackTrace();
                }
            } else {
                System.out.println("No data available to calculate average.");
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
