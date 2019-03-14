package ventasds3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import ventasds3.model.Articulo;
import ventasds3.config.Configuration;

public class ArticuloDB extends DB {

    private static ArticuloDB instance;

    public ArticuloDB(String host, String inst, String port, String db, String usuario, String password) throws Exception {
        super(host, inst, port, db, usuario, password);
    }

    public static ArticuloDB getInstance() {
        if (instance == null) {
            try {
                Configuration c = Configuration.getInstance();

                instance = new ArticuloDB(
                        c.getConfiguracion("Host"),
                        c.getConfiguracion("DataBase"),
                        c.getConfiguracion("Port"),
                        c.getConfiguracion("NameDataBase"),
                        c.getConfiguracion("User"),
                        c.getConfiguracion("Password")
                );
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        return instance;
    }

    public ArrayList<Articulo> getArticulos() throws Exception {
        ArrayList<Articulo> list = new ArrayList<>();

        String query = "SELECT id, nombre, cantidad, precio, activo FROM Articulos";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {

            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    Articulo a = new Articulo();

                    a.setId(rsSer.getLong("id"));
                    a.setNombre(rsSer.getString("nombre"));
                    a.setCantidad(rsSer.getInt("cantidad"));
                    a.setPrecio(rsSer.getDouble("precio"));
                    a.setActivo(rsSer.getBoolean("activo"));

                    list.add(a);
                }
            }
        }
        
        return list;
    }
}
