package NetworkingExercises.Exercise05_CommandDispatcher_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    private static final int PORT = 5005;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());

                String command = bufferedReader.readLine();
                String[] parts = command.split(" ");
                LocalDateTime now = LocalDateTime.now();
                if (command.equals("DATE")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formatted = now.format(formatter);
                    printWriter.println("DATE: " + formatted);
                    printWriter.flush();
                } else if (command.equals("TIME")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    String formatted = now.format(formatter);
                    printWriter.println("TIME: " + formatted);
                    printWriter.flush();
                } else if (command.contains("UPPER")) {
                    String response = parts[1].toUpperCase();
                    printWriter.println(response);
                    printWriter.flush();
                } else if (command.contains("LOWER")) {
                    String response = parts[1].toLowerCase();
                    printWriter.println(response);
                    printWriter.flush();
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
