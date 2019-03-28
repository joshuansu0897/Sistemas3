package ventasds3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ventasds3.config.Configuration;
import ventasds3.model.Empleado;

public class EmpleadoDB extends DB {

    private static EmpleadoDB instance;

    public EmpleadoDB(String host, String inst, String port, String db, String usuario, String password) throws Exception {
        super(host, inst, port, db, usuario, password);
    }

    public static EmpleadoDB getInstance() {
        if (instance == null) {
            try {
                Configuration c = Configuration.getInstance();

                instance = new EmpleadoDB(
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

    public ArrayList<Empleado> getAll() throws Exception {
        ArrayList<Empleado> list = new ArrayList<>();

        String query = "SELECT id, nombre, salario FROM Empleado";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {

            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    Empleado a = new Empleado();

                    a.setId(rsSer.getLong("id"));
                    a.setNombre(rsSer.getString("nombre"));
                    a.setSalario(rsSer.getDouble("salario"));

                    list.add(a);
                }
            }
        }

        return list;
    }

    public void save(Empleado empleado) throws SQLException {
        boolean nueva = empleado.getId() == -1;

        String query;
        try (Connection con = getConnection()) {
            PreparedStatement stm;
            if (nueva) {
                query = "INSERT INTO Empleado(nombre, "
                        + "    salario)"
                        + "    VALUES (?, ?)";
                stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            } else {
                query = "UPDATE Empleado "
                        + "    SET nombre=?,"
                        + "    salario=?"
                        + "    WHERE id=?";
                stm = con.prepareStatement(query);
                stm.setLong(3, empleado.getId());
            }

            stm.setString(1, empleado.getNombre());
            stm.setDouble(2, empleado.getSalario());

            stm.executeUpdate();
            stm.close();
        }
    }

    public Empleado getById(long id) throws Exception {
        Empleado empleado = null;

        String query = "SELECT nombre, salario FROM Empleado WHERE id=?";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    empleado = new Empleado();

                    empleado.setId(id);
                    empleado.setNombre(rsSer.getString("nombre"));
                    empleado.setSalario(rsSer.getDouble("salario"));

                }
            }
        }

        return empleado;
    }

    public void delete(long id) throws Exception {
        String query = "DELETE FROM Empleado WHERE id = ?";
        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            stmSer.executeUpdate();
        }
    }
}
