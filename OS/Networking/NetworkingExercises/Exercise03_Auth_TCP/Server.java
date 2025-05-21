package NetworkingExercises.Exercise03_Auth_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 5003;

    private static final Map<String, String> credentials = new HashMap<>();

    static {
        credentials.put("alice", "1234");
        credentials.put("bob", "qwerty");
        credentials.put("charlie", "pass");
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server is starting on port " + PORT);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());

                String response = bufferedReader.readLine();
                System.out.println("Message received" + response);



                if (response != null && response.startsWith("LOGIN")) {
                    String[] parts = response.split(" ");
                    if (parts.length == 3) {
                        String username = parts[1];
                        String password = parts[2];
                        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
                            printWriter.println("SUCCESS");
                            printWriter.flush();
                            System.out.println("Login SUCCESS for user: " + username);
                        } else {
                            printWriter.println("FAILED");
                            printWriter.flush();
                            System.out.println("Login SUCCESS for user: " + username);
                        }
                    } else {
                        printWriter.println("ERROR Invalid format. Use: LOGIN username password");
                        printWriter.flush();
                    }
                } else {
                    printWriter.println("ERROR Expected LOGIN command");
                    printWriter.flush();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
