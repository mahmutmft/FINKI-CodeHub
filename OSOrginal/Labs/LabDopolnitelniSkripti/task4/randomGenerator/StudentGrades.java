import java.io.*;
import java.util.*;

public class StudentGrades {
    public static void main(String[] args) {
        String csvFile = "student_grades.csv";
        generateCSV(csvFile);
        calculateAverage(csvFile);
    }

    public static void generateCSV(String fileName) {
        String[] subjects = {"OS", "APS", "SI", "DP", "OOP"};
        Random random = new Random();
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Index,Subject,Points,Grade");
            for (int i = 1; i <= 100; i++) {
                String index = "2023" + String.format("%03d", i);
                String subject = subjects[random.nextInt(subjects.length)];
                int points = random.nextInt(51) + 50; // Points between 50 and 100
                int grade = (points >= 90) ? 10 : (points >= 80) ? 9 : (points >= 70) ? 8 : (points >= 60) ? 7 : 6;
                writer.println(index + "," + subject + "," + points + "," + grade);
            }
            System.out.println("CSV file with 100 students generated successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void calculateAverage(String fileName) {
        double totalGrades = 0;
        int count = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String index = parts[0];
                String subject = parts[1];
                int grade = Integer.parseInt(parts[3]);
                
                if (index.startsWith("2023") && subject.equals("OS") && grade >= 6) {
                    totalGrades += grade;
                    count++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        if (count > 0) {
            System.out.printf("Average grade for 2023 students who passed OS: %.2f\n", totalGrades / count);
        } else {
            System.out.println("No students found who meet the criteria.");
        }
    }
}
