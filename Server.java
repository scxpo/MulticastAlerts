import java.net.*;
import java.io.*;

public class Server {
    private static final String MULTICAST_ADDRESS = "230.0.0.1";
    private static final int PORT = 4446;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            String message = "ATTENZIONE: Pericolo imminente!";
            DatagramPacket packet = new DatagramPacket(
                message.getBytes(), message.length(), group, PORT);
            
            while (true) {
                socket.send(packet);
                System.out.println("Messaggio di emergenza inviato: " + message);
                Thread.sleep(5000); // Invia un messaggio ogni 5 secondi
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}