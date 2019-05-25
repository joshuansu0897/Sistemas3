/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.db;

import proyecto.config.Configuration;
import proyecto.model.Empleado;
import proyecto.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class EmpleadoDB extends DB {

    private final static Logger logger = Logger.getLogger(EmpleadoDB.class);
    private static EmpleadoDB instance;
    private static Empleado empleado;

    private EmpleadoDB(String host, String inst, String port, String db, String usuario, String password) throws Exception {
        super(host, inst, port, db, usuario, password);
    }

    public static EmpleadoDB getInstance() {
        if (instance == null) {
            try {
                Configuration c = Configuration.getInstance();
                instance = new EmpleadoDB(
                        c.getConfiguracion("Server"),
                        c.getConfiguracion("DataBase"),
                        c.getConfiguracion("Port"),
                        c.getConfiguracion("NameDataBase"),
                        c.getConfiguracion("User"),
                        c.getConfiguracion("Password")
                );
            } catch (Exception e) {
                logger.error("Error al conectar con base de datos.", e);
            }
        }
        return instance;
    }

    public boolean autenticacion(String usuario, String contraseña) throws Exception {
        if (usuario == null || usuario.equals("")) {
            return false;
        }

        empleado = new Empleado();

        String query = "SELECT password, id"
                + "	FROM Empleado WHERE username=?";

        String password = null;
        try (Connection conS = getConnection(); PreparedStatement stmS = conS.prepareStatement(query)) {
            stmS.setString(1, usuario);
            try (ResultSet rs = stmS.executeQuery()) {
                while (rs.next()) {
                    password = rs.getString("password");
                    empleado.setUsername(usuario);
                    empleado.setId(rs.getLong("id"));
                }
            }
        }

        String sha = Util.encrypt(contraseña);
        return sha.equals(password);
    }

    public boolean empleadoExist(String uss) throws Exception {

        String password = null;
        String username = null;

        String query = "SELECT username, password "
                + "	FROM Empleado WHERE username = ?";

        try (Connection conS = getConnection(); PreparedStatement stmS = conS.prepareStatement(query)) {
            stmS.setString(1, uss);
            try (ResultSet rs = stmS.executeQuery()) {
                while (rs.next()) {
                    password = rs.getString("password");
                    username = rs.getString("username");
                }
            }
        }
        return (password != null && username != null);
    }

    public boolean saveEmpleado(Empleado user) throws Exception {
        boolean nueva = user.getId() == -1;

        if (empleadoExist(user.getUsername()) && nueva) {
            JOptionPane.showMessageDialog(null, "username was already taken");
            return false;
        }

        String query;
        try (Connection con = getConnection()) {
            PreparedStatement stm;

            if (nueva) {
                query = "INSERT INTO Empleado("
                        + "	name, username, password) "
                        + "	VALUES (?, ?, ?);";
                stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            } else {
                query = "UPDATE Empleado"
                        + "	SET name=?, username=?, password=?"
                        + "	WHERE id=?";
                stm = con.prepareStatement(query);
                stm.setLong(4, user.getId());
            }

            stm.setString(1, user.getName());
            stm.setString(2, user.getUsername());
            stm.setString(3, user.getPassword());

            stm.executeUpdate();
            stm.close();
        }
        return true;
    }

    public ArrayList<Empleado> getAllEmpleados() throws Exception {
        ArrayList<Empleado> users = new ArrayList();

        String query = "SELECT id, name, username, password, createAt, updateAt "
                + "	FROM Empleado";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    Empleado U = new Empleado();
                    U.setId(rsSer.getLong("id"));
                    U.setName(rsSer.getString("name"));
                    U.setUsername(rsSer.getString("username"));
                    U.setPassword(rsSer.getString("password"));
                    U.setCreateAt(rsSer.getObject("createAt", LocalDate.class));
                    U.setUpdateAt(rsSer.getObject("updateAt", LocalDate.class));
                    users.add(U);
                }
            }
        }
        return users;
    }

    public Empleado getEmpleadoByUsername(String str) throws Exception {
        Empleado user = null;

        String query = "SELECT id, name, username, password, createAt, updateAt "
                + "	FROM Empleado WHERE username=?";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setString(1, str);
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    user = new Empleado();
                    user.setId(rsSer.getLong("id"));
                    user.setName(rsSer.getString("name"));
                    user.setUsername(rsSer.getString("username"));
                    user.setPassword(rsSer.getString("password"));
                    user.setCreateAt(rsSer.getObject("createAt", LocalDate.class));
                    user.setUpdateAt(rsSer.getObject("updateAt", LocalDate.class));
                }
            }
        }
        return user;
    }

    public void deleteEmpleado(Long id) throws Exception {
        String query = "DELETE FROM Empleado"
                + "	WHERE id = ?";
        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            stmSer.executeUpdate();
        }
    }

    public Empleado getEmpleado() {
        return empleado;
    }
}
