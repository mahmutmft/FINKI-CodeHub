package NetworkingExercises.Exercise02_MathQuiz_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 5002;
    private static final String ADDRESS = "localhost";

    public static void main(String[] args) {
        try (Socket socket = new Socket(ADDRESS, PORT)) {
            try (
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                    Scanner scanner = new Scanner(System.in);
            ) {
                System.out.println("Connected to server");
                while (true) {
                    String messageFromServer = bufferedReader.readLine();
                    System.out.println(messageFromServer);

                    String response = scanner.nextLine();
                    printWriter.println(response);
                    printWriter.flush();

                    String secondMessage = bufferedReader.readLine();
                    System.out.println(secondMessage);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
