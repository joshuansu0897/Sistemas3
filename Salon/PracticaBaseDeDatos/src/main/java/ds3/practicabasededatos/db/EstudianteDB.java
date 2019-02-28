package ds3.practicabasededatos.db;

import ds3.practicabasededatos.config.Configuracion;
import ds3.practicabasededatos.model.Estudiante;
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
                Configuracion c = Configuracion.getInstance();
                instance = new EstudianteDB(
                        c.getConfiguracion("servidor"),
                        c.getConfiguracion("tipoBaseDeDatos"),
                        c.getConfiguracion("puerto"),
                        c.getConfiguracion("nombreBaseDeDatos"),
                        c.getConfiguracion("usuario"),
                        c.getConfiguracion("password")
                );
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return instance;
    }

    public ArrayList<Estudiante> getEstudents() throws Exception {
        ArrayList<Estudiante> list = new ArrayList<>();

        String query = "SELECT * FROM Estudiante";

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
                query = "INSERT INTO Estudiante(\"expediente\",\n"
                        + "    \"nombre\",\n"
                        + "    \"sexo\",\n"
                        + "    activo)\n"
                        + "	VALUES (?, ?, ?, ?)";
                stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            } else {
                query = "UPDATE Estudiante\n"
                        + "	SET \"expediente\"=?,\n"
                        + "    \"nombre\"=?,\n"
                        + "    \"sexo\"=?,\n"
                        + "    \"activo\"=?,\n"
                        + "	WHERE id=?";
                stm = con.prepareStatement(query);
                stm.setLong(5, e.getId());
            }

            stm.setInt(1, e.getExpediente());
            stm.setString(2, e.getNombre());
            stm.setString(3, e.getSexo());
            stm.setBoolean(4, e.isActivo());

            stm.executeUpdate();
            stm.close();
        }
    }
}
