package NetworkingExercises.Exercise01_StringReversal_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    private static final int PORT = 5001;
    private static final String ADDRESS = "localhost";

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(ADDRESS);
            Scanner scanner = new Scanner(System.in);

            while (true){
                String sendMessage = scanner.nextLine();

                if (sendMessage.equals("exit")){
                    break;
                }

                byte[] buffer = sendMessage.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
                socket.send(packet);

                byte[] messageBack = new byte[1024];
                DatagramPacket messagePacket = new DatagramPacket(messageBack, messageBack.length);
                socket.receive(messagePacket);

                String response = new String(messagePacket.getData(), 0 ,messagePacket.getLength());
                System.out.println("Server responded: " + response);

            }


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
