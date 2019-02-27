package ds3.practicabasededatos;

import ds3.practicabasededatos.model.Estudiante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DS3", "root", "example");

            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM Estudiante");

            System.out.println("expediente \t nombre \t sexo \t activo");

            ArrayList<Estudiante> list = new ArrayList<>();
            while (rs.next()) {
                Estudiante e = new Estudiante();

                e.setActivo(rs.getBoolean("activo"));
                e.setExpediente(rs.getInt("expediente"));
                e.setSexo(rs.getString("sexo"));
                e.setNombre(rs.getString("nombre"));
                e.setId(rs.getInt("id"));
                System.out.println(e);

                list.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (st != null) {
                    st.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

}
