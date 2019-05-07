package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 8080);
            System.out.println("Soy el cliente, ya me conecte.");

            DataInputStream dis = new DataInputStream(cliente.getInputStream());
            DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());

            dos.writeUTF("Soy el cliente y te mando este mensaje.");
            dos.flush();

            System.out.println("Recibi: " + dis.readUTF());

            dos.close();
            dis.close();
            cliente.close();
            System.out.println("Cliente Desconectado");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }

    }
}
