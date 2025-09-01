package Simulador_CLI_SERV_inseguro;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
        // Conexión con el servidor
        clientSocket = new Socket(serverAddress, serverPort);
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
        System.out.println("Conectado al servidor en " + serverAddress + ":" + serverPort);
    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeUTF(message);
        outputStream.flush();
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

    // Método principal para probar el cliente
    public static void main(String[] args) {
        try {
            TCPClient client = new TCPClient("127.0.0.1", 5000);
            client.connect();

            client.sendMessage("Hola el ususario es : Manuuu12 y la contraseña es: 12345678 ");
            String respuesta = client.receiveMessage();
            System.out.println("Respuesta del servidor: " + respuesta);
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}