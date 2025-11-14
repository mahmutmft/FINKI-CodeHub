package NetworkingExercises.Exercise04_Palindrome_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

    public static boolean isPalindrome(String input) {
        String clean = input.replaceAll("\\s+", "").toLowerCase();

        int left = 0;
        int right = clean.length() - 1;

        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    private static final int PORT = 5004;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivedPacket);

                String clientMessage = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("We got the word " + clientMessage);
                String palindrome;

                if (isPalindrome(clientMessage)) {
                    palindrome = "Yes, it's a palindrome.";
                } else {
                    palindrome = "No, it's not a palindrome.";
                }

                byte[] sendBuffer = palindrome.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, receivedPacket.getAddress(), receivedPacket.getPort());
                socket.send(sendPacket);
            }


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
