package Simulador_CLI_SERV_inseguro;
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
            System.out.println("Servidor TCP iniciado en el puerto " + port);
            while (true) {
                // Esperar conexión de un cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                // Crear streams de entrada/salida
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                // Leer mensaje del cliente
                String mensaje = input.readUTF();
                System.out.println("Mensaje recibido del cliente: " + mensaje);

                // Responder al cliente
                String respuesta = "Servidor recibió: " + mensaje;
                output.writeUTF(respuesta);
                // Cerrar conexión con este cliente
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método principal para ejecutar el servidor
    public static void main(String[] args) {
        TCPServer server = new TCPServer(5000);
        server.start();
    }
}