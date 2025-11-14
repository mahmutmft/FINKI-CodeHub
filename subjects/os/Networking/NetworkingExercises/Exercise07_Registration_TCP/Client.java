package NetworkingExercises.Exercise07_Registration_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 5006;
    private static final String ADDRESS = "localhost";

    public static void main(String[] args) {
        try (Socket socket = new Socket(ADDRESS, PORT);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            String serverMessage;
            while ((serverMessage = bufferedReader.readLine()) != null) {
                System.out.println("Server: " + serverMessage);

                if (!serverMessage.toLowerCase().contains("successful") &&
                        !serverMessage.toLowerCase().contains("invalid")) {
                    String userInput = scanner.nextLine();
                    printWriter.println(userInput);
                    printWriter.flush();
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Client Error: " + e.getMessage());
        }
    }
}
