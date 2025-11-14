package NetworkingExercises.Exercise05_CommandDispatcher_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 5005;
    private static final String ADDRESS = "localhost";

    public static void main(String[] args) {
        try (Socket socket = new Socket(ADDRESS,PORT)){

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            String command = scanner.nextLine();

            printWriter.println(command);
            printWriter.flush();

            String response = bufferedReader.readLine();
            System.out.println(response);
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
