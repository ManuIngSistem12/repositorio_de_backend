import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.PublicKey;

public class TCPClient {
    private String serverAddress;
    private int serverPort;
    private Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public TCPClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect() throws IOException {
        clientSocket = new Socket(serverAddress, serverPort);
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
        System.out.println("Conectado al servidor en " + serverAddress + ":" + serverPort);
    }

    public void sendMessage(String message) {
        try {
            PublicKey serverPublicKey = RSAUtil.getPublicKey();
            String encrypted = RSAUtil.encrypt(message, serverPublicKey);
            outputStream.writeUTF(encrypted);
            outputStream.flush();
            System.out.println("Mensaje cifrado enviado: " + encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() throws IOException {
        return inputStream.readUTF();
    }

    public void disconnect() throws IOException {
        inputStream.close();
        outputStream.close();
        clientSocket.close();
        System.out.println("Conexión cerrada.");
    }

    // Main
    public static void main(String[] args) {
        try {
            TCPClient client = new TCPClient("127.0.0.1", 6000);
            client.connect();
            client.sendMessage("Hola servidor, este mensaje está cifrado con RSA 🔐");
            String respuesta = client.receiveMessage();
            System.out.println("Respuesta del servidor: " + respuesta);
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}