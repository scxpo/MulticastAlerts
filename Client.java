import java.net.*;
import java.io.*;

public class Client {
    private static final String MULTICAST_ADDRESS = "230.0.0.1";
    private static final int PORT = 4446;

    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            socket.joinGroup(group);
            
            System.out.println("Client in ascolto sui messaggi di emergenza...");
            byte[] buffer = new byte[256];
            
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Messaggio ricevuto: " + received);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}