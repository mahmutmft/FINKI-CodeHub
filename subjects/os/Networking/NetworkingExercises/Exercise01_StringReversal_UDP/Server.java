package NetworkingExercises.Exercise01_StringReversal_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    private static final int PORT = 5001;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {

            System.out.println("Server is started and listening on port " + PORT);

            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + message);

                String reversed = new StringBuilder(message).reverse().toString();
                System.out.println("Sending back: " + reversed);
                byte[] sendBuffer = reversed.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(
                        sendBuffer,
                        sendBuffer.length,
                        packet.getAddress(),
                        packet.getPort()
                );
                socket.send(sendPacket);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
