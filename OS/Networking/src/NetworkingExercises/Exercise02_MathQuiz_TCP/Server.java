package NetworkingExercises.Exercise02_MathQuiz_TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class MathQuestions extends Thread {

    BufferedReader bufferedReader = null;
    PrintWriter printWriter = null;
    Socket clientSocket = null;

    public MathQuestions(BufferedReader bufferedReader, PrintWriter printWriter, Socket clientSocket) {
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int firstNumber = (int) (Math.random() * 101);
                int secondNumber = (int) (Math.random() * 101);
                printWriter.println("What is " + firstNumber + "+" + secondNumber + "?");
                printWriter.flush();
                System.out.println("Question " + firstNumber + "+" + secondNumber + "?");

                String input = bufferedReader.readLine();

                if (input == null) {
                    System.out.println("Client disconnected.");
                    break;
                }

                try {
                    int answer = Integer.parseInt(input);
                    if (firstNumber + secondNumber == answer) {
                        printWriter.println("The answer is right");
                    } else {
                        printWriter.println("The answer is not right");
                    }
                } catch (NumberFormatException e) {
                    printWriter.println("Invalid input! Please enter a number.");
                }

                printWriter.flush();

            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

public class Server {
    private static final int PORT = 5002;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is started on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected from " + clientSocket.getInetAddress());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
                Thread thread = new MathQuestions(bufferedReader, printWriter, clientSocket);
                thread.start();
            }
        } catch (
                IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
