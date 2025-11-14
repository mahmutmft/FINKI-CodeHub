package NetworkingExercises.Exercise04_Palindrome_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    private static final int PORT = 5004;
    private static final String ADDRESS = "localhost";

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(ADDRESS);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                byte[] buffer = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, address, PORT);
                socket.send(sendPacket);

                byte[] newBuffer = new byte[1024];
                DatagramPacket receivedPacket = new DatagramPacket(newBuffer, newBuffer.length);
                socket.receive(receivedPacket);

                String responseServer = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println(responseServer);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
