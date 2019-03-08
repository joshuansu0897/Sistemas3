package practicabasededatos.db;

import practicabasededatos.config.Configuration;
import practicabasededatos.model.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EstudianteDB extends DB {

    private static EstudianteDB instance;

    public EstudianteDB(String host, String inst, String port, String db, String usuario, String password) throws Exception {
        super(host, inst, port, db, usuario, password);
    }

    public static EstudianteDB getInstance() {
        if (instance == null) {
            try {
                Configuration c = Configuration.getInstance();
                instance = new EstudianteDB(
                        c.getConfiguracion("Server"),
                        c.getConfiguracion("DataBase"),
                        c.getConfiguracion("Port"),
                        c.getConfiguracion("NameDataBase"),
                        c.getConfiguracion("User"),
                        c.getConfiguracion("Password")
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return instance;
    }

    public ArrayList<Estudiante> getEstudents() throws Exception {
        ArrayList<Estudiante> list = new ArrayList<>();

        String query = "SELECT id, activo, expediente, sexo, nombre FROM Estudiante";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {

            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    Estudiante e = new Estudiante();

                    e.setActivo(rsSer.getBoolean("activo"));
                    e.setExpediente(rsSer.getInt("expediente"));
                    e.setSexo(rsSer.getString("sexo"));
                    e.setNombre(rsSer.getString("nombre"));
                    e.setId(rsSer.getInt("id"));

                    list.add(e);
                }
            }
        }
        return list;
    }

    public void saveEstudiante(Estudiante e) throws Exception {
        boolean nueva = e.getId() == -1;

        String query;
        try (Connection con = getConnection()) {
            PreparedStatement stm;
            if (nueva) {
                query = "INSERT INTO Estudiante(expediente, "
                        + "    nombre,"
                        + "    sexo,"
                        + "    activo)"
                        + "    VALUES (?, ?, ?, ?)";
                stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            } else {
                query = "UPDATE Estudiante "
                        + "    SET expediente=?,"
                        + "    nombre=?,"
                        + "    sexo=?,"
                        + "    activo=?"
                        + "    WHERE id=?";
                stm = con.prepareStatement(query);
                stm.setLong(5, e.getId());
            }

            stm.setLong(1, e.getExpediente());
            stm.setString(2, e.getNombre());
            stm.setString(3, e.getSexo());
            stm.setBoolean(4, e.isActivo());

            stm.executeUpdate();
            stm.close();
        }
    }

    public Estudiante getEstudianteById(long id) throws Exception {
        Estudiante estudiante = null;

        String query = "SELECT activo, expediente, sexo, nombre FROM Estudiante WHERE id=?";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    estudiante = new Estudiante();

                    estudiante.setActivo(rsSer.getBoolean("activo"));
                    estudiante.setExpediente(rsSer.getInt("expediente"));
                    estudiante.setSexo(rsSer.getString("sexo"));
                    estudiante.setNombre(rsSer.getString("nombre"));
                    estudiante.setId(id);

                }
            }
        }

        return estudiante;
    }

    public void deleteEstudiante(long id) throws Exception {
        String query = "DELETE FROM Estudiante WHERE id = ?";
        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            stmSer.executeUpdate();
        }
    }
}
