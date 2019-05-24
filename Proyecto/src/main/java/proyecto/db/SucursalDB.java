package proyecto.db;

import proyecto.config.Configuration;
import proyecto.model.Sucursal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class SucursalDB extends DB {

    private final static Logger logger = Logger.getLogger(SucursalDB.class);
    private static SucursalDB instance;

    private SucursalDB(String host, String inst, String port, String db, String usuario, String password) throws Exception {
        super(host, inst, port, db, usuario, password);
    }

    public static SucursalDB getInstance() {
        if (instance == null) {
            try {
                Configuration c = Configuration.getInstance();
                instance = new SucursalDB(
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

    public boolean sucursalExist(String name) throws Exception {

        String resName = null;
        String description = null;

        String query = "SELECT name, description "
                + "	FROM Sucursal WHERE name = ?";

        try (Connection conS = getConnection(); PreparedStatement stmS = conS.prepareStatement(query)) {
            stmS.setString(1, name);
            try (ResultSet rs = stmS.executeQuery()) {
                while (rs.next()) {
                    description = rs.getString("name");
                    resName = rs.getString("description");
                }
            }
        }
        return (description != null && resName != null);
    }

    public boolean saveSucursal(Sucursal sucursal) throws Exception {
        boolean nueva = sucursal.getId() == -1;

        if (sucursalExist(sucursal.getName()) && nueva) {
            JOptionPane.showMessageDialog(null, "ya tienes un sucursal con ese nombre");
            return false;
        }

        String query;
        try (Connection con = getConnection()) {
            PreparedStatement stm;

            if (nueva) {
                query = "INSERT INTO Sucursal("
                        + "	name, description) "
                        + "	VALUES (?, ?);";
                stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            } else {
                query = "UPDATE Sucursal"
                        + "	SET name=?, description=?"
                        + "	WHERE id=?";
                stm = con.prepareStatement(query);
                stm.setLong(3, sucursal.getId());
            }

            stm.setString(1, sucursal.getName());
            stm.setString(2, sucursal.getDescription());

            stm.executeUpdate();
            stm.close();
        }
        return true;
    }

    public ArrayList<Sucursal> getAllSucursales() throws Exception {
        ArrayList<Sucursal> proveedores = new ArrayList();

        String query = "SELECT id, name, description, createAt, updateAt "
                + "	FROM Sucursal";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    Sucursal sucursal = new Sucursal();
                    sucursal.setId(rsSer.getLong("id"));
                    sucursal.setName(rsSer.getString("name"));
                    sucursal.setDescription(rsSer.getString("description"));
                    sucursal.setCreateAt(rsSer.getObject("createAt", LocalDate.class));
                    sucursal.setUpdateAt(rsSer.getObject("updateAt", LocalDate.class));
                    proveedores.add(sucursal);
                }
            }
        }
        return proveedores;
    }

    public Sucursal getSucursalByName(String str) throws Exception {
        Sucursal sucursal = null;

        String query = "SELECT id, name, description, createAt, updateAt "
                + "	FROM Sucursal WHERE name=?";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setString(1, str);
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    sucursal = new Sucursal();
                    sucursal.setId(rsSer.getLong("id"));
                    sucursal.setName(rsSer.getString("name"));
                    sucursal.setDescription(rsSer.getString("description"));
                    sucursal.setCreateAt(rsSer.getObject("createAt", LocalDate.class));
                    sucursal.setUpdateAt(rsSer.getObject("updateAt", LocalDate.class));
                }
            }
        }
        return sucursal;
    }

    public Sucursal getSucursalById(long id) throws Exception {
        Sucursal sucursal = null;

        String query = "SELECT id, name, description, createAt, updateAt "
                + "	FROM Sucursal WHERE id=?";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    sucursal = new Sucursal();
                    sucursal.setId(rsSer.getLong("id"));
                    sucursal.setName(rsSer.getString("name"));
                    sucursal.setDescription(rsSer.getString("description"));
                    sucursal.setCreateAt(rsSer.getObject("createAt", LocalDate.class));
                    sucursal.setUpdateAt(rsSer.getObject("updateAt", LocalDate.class));
                }
            }
        }
        return sucursal;
    }

    public void deleteSucursal(Long id) throws Exception {
        String query = "DELETE FROM Sucursal"
                + "	WHERE id = ?";
        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            stmSer.executeUpdate();
        }
    }

}
