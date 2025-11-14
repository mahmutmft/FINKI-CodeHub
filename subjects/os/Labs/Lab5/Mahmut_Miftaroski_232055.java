package OSLab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Mahmut_Miftaroski_232055 {

    final static int PORT = 9753;
    final static String ADDRESS = "194.149.135.49";
    static Socket socket;
    static BufferedReader bufferedReader;
    static PrintWriter printWriter;
    static Scanner scanner;

    static class ServerThread extends Thread {
        private final BufferedReader bufferedReader;

        public ServerThread(BufferedReader bufferedReader) {
            this.bufferedReader = bufferedReader;
        }

        @Override
        public void run() {
            try {
                String serverResponse;
                while ((serverResponse = bufferedReader.readLine()) != null) {
                    System.out.println(serverResponse);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    static class ClientThread extends Thread {
        private final Scanner scanner;
        private final PrintWriter printWriter;

        public ClientThread(Scanner scanner, PrintWriter printWriter) {
            this.scanner = scanner;
            this.printWriter = printWriter;
        }

        @Override
        public void run() {
            while (true) {
                String scannerMessage = scanner.nextLine();
                printWriter.println(scannerMessage);
                printWriter.flush();
                if (scannerMessage.equals("exit")) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            socket = new Socket(ADDRESS, PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            scanner = new Scanner(System.in);
            String serverResponse;

            printWriter.println("login:232055");
            printWriter.flush();

            while (true){
                serverResponse = bufferedReader.readLine();
                if (serverResponse == null || serverResponse.contains("Server says: bad greeting")) {
                    System.out.println("Login failed. Server response:" + serverResponse);
                    Thread.sleep(3000);
                }
                else if (serverResponse.contains("Server says:  welcome")){
                    System.out.println("Login successful");
                    break;
                }
            }

            // Серверот враќа "receiver unreachable!" кога се испраќа порака "hello:232055".
            printWriter.println("hello:232055");
            printWriter.flush();

            // Echo порака на мојот индекс
            printWriter.println("232055:hello");
            printWriter.flush();

            Thread serverMessage = new ServerThread(bufferedReader);
            Thread clientMessage = new ClientThread(scanner, printWriter);

            serverMessage.start();
            clientMessage.start();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
