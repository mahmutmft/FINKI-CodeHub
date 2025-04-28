package sensor;

import java.io.FileWriter;
import java.io.IOException;

public class TemperatureSensor {
    public static void main(String[] args) {
        int lowTemp = Integer.parseInt(System.getenv().getOrDefault("LOW_TEMPERATURE", "5"));
        int highTemp = Integer.parseInt(System.getenv().getOrDefault("HIGH_TEMPERATURE", "35"));
        
        System.out.println("Temperature thresholds:");
        System.out.println("LOW_TEMPERATURE: " + lowTemp);
        System.out.println("HIGH_TEMPERATURE: " + highTemp);
        
        while (true) {
            try {
                FileWriter file = new FileWriter("/data/temperature.txt", true);
                for (int i = 0; i < 5; i++) {
                    int random = lowTemp + (int) (Math.random() * (highTemp - lowTemp + 10));
                    file.write(random + " ");
                }
                file.write("\n");
                file.close();
                System.out.println("Temperature data written successfully");
                Thread.sleep(30000);
            }
            catch (IOException e){
                System.out.println("There is an error writing to file");
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                System.out.println("Error during sleep");
                e.printStackTrace();
            }
        }
    }
}