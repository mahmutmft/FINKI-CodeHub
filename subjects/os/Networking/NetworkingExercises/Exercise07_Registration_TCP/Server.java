package NetworkingExercises.Exercise07_Registration_TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static final int PORT = 5006;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            File env = new File("src/NetworkingExercises/Exercise07_Registration_TCP/.env");

            if (env.createNewFile()) {
                System.out.println(env.getName() + " file created");
            } else {
                System.out.println(".env file already exists.");
            }

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                             PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
                        ){
                            String askClient = "Do you already have account on our server yes or no?";
                            printWriter.println(askClient);
                            printWriter.flush();

                            String response = bufferedReader.readLine();

                            if (response.equals("yes")) {
                                String username, password;
                                String loginDetails = "Enter your username:";
                                printWriter.println(loginDetails);
                                printWriter.flush();

                                username = bufferedReader.readLine();

                                loginDetails = "Enter your password";
                                printWriter.println(loginDetails);
                                printWriter.flush();

                                password = bufferedReader.readLine();

                                boolean authenticated = false;
                                try (BufferedReader fileReader = new BufferedReader(new FileReader("src/NetworkingExercises/Exercise07_Registration_TCP/.env"))) {
                                    String line;
                                    while ((line = fileReader.readLine()) != null) {
                                        if (line.equals(username + ":" + password)) {
                                            authenticated = true;
                                            break;
                                        }
                                    }
                                }
                                printWriter.println(authenticated ? "Login successful." : "Invalid credentials.");
                                printWriter.flush();

                            } else {
                                String username, password;

                                String loginDetails = "Enter your username:";
                                printWriter.println(loginDetails);
                                printWriter.flush();

                                username = bufferedReader.readLine();

                                loginDetails = "Enter your account password";
                                printWriter.println(loginDetails);
                                printWriter.flush();

                                password = bufferedReader.readLine();

                                try (FileWriter fileWriter = new FileWriter(".env", true)) {
                                    fileWriter.write(username + ":" + password + "\n");
                                }

                                printWriter.println("Registration successful.");
                                printWriter.flush();
                            }

                        }catch (IOException e){
                            System.err.println(e.getMessage());
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
