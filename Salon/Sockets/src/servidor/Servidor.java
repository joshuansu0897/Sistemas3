/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author joshuansu
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(8080);
            System.out.println("Esperando conexion....");

            Socket miSocket = servidor.accept();
            System.out.println("Soy el servidor.");

            DataInputStream dis = new DataInputStream(miSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(miSocket.getOutputStream());

            System.out.println("Recibi: " + dis.readUTF());
            dos.writeUTF("Mensaje recibido. Hasta luego.");
            dos.flush();

            dos.close();
            dis.close();

            miSocket.close();
            System.out.println("Socket Desconectado");
            servidor.close();
            System.out.println("Muere Server");

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

}
