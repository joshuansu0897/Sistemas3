package ventasds3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public ArrayList<Articulo> getAll() throws Exception {
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

    public void save(Articulo articulo) throws SQLException {
        boolean nueva = articulo.getId() == -1;

        String query;
        try (Connection con = getConnection()) {
            PreparedStatement stm;
            if (nueva) {
                query = "INSERT INTO Articulos(nombre, "
                        + "    cantidad,"
                        + "    precio,"
                        + "    activo)"
                        + "    VALUES (?, ?, ?, ?)";
                stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            } else {
                query = "UPDATE Articulos "
                        + "    SET nombre=?,"
                        + "    cantidad=?,"
                        + "    precio=?,"
                        + "    activo=?"
                        + "    WHERE id=?";
                stm = con.prepareStatement(query);
                stm.setLong(5, articulo.getId());
            }

            stm.setString(1, articulo.getNombre());
            stm.setLong(2, articulo.getCantidad());
            stm.setDouble(3, articulo.getPrecio());
            stm.setBoolean(4, articulo.isActivo());

            stm.executeUpdate();
            stm.close();
        }
    }

    public Articulo getById(long id) throws Exception {
        Articulo articulo = null;

        String query = "SELECT nombre, cantidad, precio, activo FROM Articulos WHERE id=?";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    articulo = new Articulo();

                    articulo.setId(id);
                    articulo.setNombre(rsSer.getString("nombre"));
                    articulo.setCantidad(rsSer.getInt("cantidad"));
                    articulo.setPrecio(rsSer.getDouble("precio"));
                    articulo.setActivo(rsSer.getBoolean("activo"));

                }
            }
        }

        return articulo;
    }

    public void delete(long id) throws Exception {
        String query = "DELETE FROM Articulos WHERE id = ?";
        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            stmSer.executeUpdate();
        }
    }
}
