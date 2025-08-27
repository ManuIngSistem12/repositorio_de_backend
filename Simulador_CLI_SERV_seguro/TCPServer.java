import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private int port;

    public TCPServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor TCP con RSA iniciado en el puerto " + port);
            while (true) {
                // Esperar cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

                // Recibir mensaje cifrado
                String encrypted = input.readUTF();
                System.out.println("Mensaje recibido (cifrado): " + encrypted);
                // Descifrar con llave privada
                String mensaje = RSAUtil.decrypt(encrypted, RSAUtil.getPrivateKey());
                System.out.println("Mensaje descifrado: " + mensaje);
                // Responder (texto plano, o también lo puedes cifrar si quieres doble vía
                // segura)
                output.writeUTF("Servidor recibió tu mensaje: " + mensaje);
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main
    public static void main(String[] args) {
        TCPServer server = new TCPServer(6000);
        server.start();
    }
}