package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("url connection");

            st = conn.createStatement();
            rs = st.executeQuery("SELECT expediente, nombre, sexo, activo FROM table_name");

            System.out.println("expediente \t nombre \t sexo \t activo");
            while (rs.next()) {
                int expediente = rs.getInt("expediente");
                String nombre = rs.getString("nombre");
                String sexo = rs.getString("sexo");
                boolean activo = rs.getBoolean("activo");
                System.out.println(expediente + " \t " + nombre + " \t " + sexo + " \t " + activo);
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
