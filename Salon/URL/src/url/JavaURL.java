package url;

import java.io.DataInputStream;
import java.net.URL;

public class JavaURL {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://google.com");

            System.out.println(url.getHost());
            System.out.println(url.getFile());
            System.out.println(url.getPath());
            System.out.println(url.getPort());
            System.out.println(url.getProtocol());
            System.out.println(url.getQuery());
            System.out.println(url.getRef());
            System.out.println(url.getUserInfo());

            DataInputStream dis = new DataInputStream(url.openStream());
            
            System.out.println(dis.readUTF());
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}