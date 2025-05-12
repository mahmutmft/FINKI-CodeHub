package tcp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
    private static final String server_ip = "194.149.135.49";
    private static final int server_port = 9753;
    private static final String indeks = "233152";

    public static void main(String[] args) {
        while (true) {
            try {
                Socket socket = new Socket(server_ip, server_port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                out.println("hello:" + indeks);
                String response = in.readLine();

                if (response == null) {
                    System.out.println("Login neuspesno. Obidi se povtorno...");
                    socket.close();
                    continue;
                }

                System.out.println("Login uspesen " + response);

                out.println("login:" + indeks);
                String helloResponse = in.readLine();
                System.out.println("Odgovor od serverot: " + helloResponse);

                new MessageReciver(in).start();
                new MessageSender(out, socket).start();

                break;

            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class MessageReciver extends Thread {
        private BufferedReader in;

        public MessageReciver(BufferedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println(msg);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class MessageSender extends Thread {
        private PrintWriter out;
        private Socket socket;

        public MessageSender(PrintWriter out, Socket socket) {
            this.out = out;
            this.socket = socket;
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            try {
                while (true) {
                    String msg = scanner.nextLine();
                    if (msg.equalsIgnoreCase("exit")) {
                        System.out.println("Prekinuvanje na konekcijata...");
                        socket.close();
                        break;
                    }
                    out.println(msg);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}