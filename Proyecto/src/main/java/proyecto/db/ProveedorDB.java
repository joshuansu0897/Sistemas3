package proyecto.db;

import proyecto.config.Configuration;
import proyecto.model.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class ProveedorDB extends DB {

    private final static Logger logger = Logger.getLogger(ProveedorDB.class);
    private static ProveedorDB instance;

    private ProveedorDB(String host, String inst, String port, String db, String usuario, String password) throws Exception {
        super(host, inst, port, db, usuario, password);
    }

    public static ProveedorDB getInstance() {
        if (instance == null) {
            try {
                Configuration c = Configuration.getInstance();
                instance = new ProveedorDB(
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

    public boolean proveedorExist(String name) throws Exception {

        String resName = null;
        String description = null;

        String query = "SELECT name, description "
                + "	FROM Proveedor WHERE name = ?";

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

    public boolean saveProveedor(Proveedor proveedor) throws Exception {
        boolean nueva = proveedor.getId() == -1;

        if (proveedorExist(proveedor.getName()) && nueva) {
            JOptionPane.showMessageDialog(null, "ya tienes un proveedor con ese nombre");
            return false;
        }

        String query;
        try (Connection con = getConnection()) {
            PreparedStatement stm;

            if (nueva) {
                query = "INSERT INTO Proveedor("
                        + "	name, description) "
                        + "	VALUES (?, ?);";
                stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            } else {
                query = "UPDATE Proveedor"
                        + "	SET name=?, description=?"
                        + "	WHERE id=?";
                stm = con.prepareStatement(query);
                stm.setLong(3, proveedor.getId());
            }

            stm.setString(1, proveedor.getName());
            stm.setString(2, proveedor.getDescription());

            stm.executeUpdate();
            stm.close();
        }
        return true;
    }

    public ArrayList<Proveedor> getAllProveedores() throws Exception {
        ArrayList<Proveedor> proveedores = new ArrayList();

        String query = "SELECT id, name, description, createAt, updateAt "
                + "	FROM Proveedor";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    Proveedor proveedor = new Proveedor();
                    proveedor.setId(rsSer.getLong("id"));
                    proveedor.setName(rsSer.getString("name"));
                    proveedor.setDescription(rsSer.getString("description"));
                    proveedor.setCreateAt(rsSer.getObject("createAt", LocalDate.class));
                    proveedor.setUpdateAt(rsSer.getObject("updateAt", LocalDate.class));
                    proveedores.add(proveedor);
                }
            }
        }
        return proveedores;
    }

    public Proveedor getProveedorByName(String str) throws Exception {
        Proveedor proveedor = null;

        String query = "SELECT id, name, description, createAt, updateAt "
                + "	FROM Proveedor WHERE name=?";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setString(1, str);
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    proveedor = new Proveedor();
                    proveedor.setId(rsSer.getLong("id"));
                    proveedor.setName(rsSer.getString("name"));
                    proveedor.setDescription(rsSer.getString("description"));
                    proveedor.setCreateAt(rsSer.getObject("createAt", LocalDate.class));
                    proveedor.setUpdateAt(rsSer.getObject("updateAt", LocalDate.class));
                }
            }
        }
        return proveedor;
    }

    public Proveedor getProveedorById(long id) throws Exception {
        Proveedor proveedor = null;

        String query = "SELECT id, name, description, createAt, updateAt "
                + "	FROM Proveedor WHERE id=?";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    proveedor = new Proveedor();
                    proveedor.setId(rsSer.getLong("id"));
                    proveedor.setName(rsSer.getString("name"));
                    proveedor.setDescription(rsSer.getString("description"));
                    proveedor.setCreateAt(rsSer.getObject("createAt", LocalDate.class));
                    proveedor.setUpdateAt(rsSer.getObject("updateAt", LocalDate.class));
                }
            }
        }
        return proveedor;
    }

    public void deleteProveedor(Long id) throws Exception {
        String query = "DELETE FROM Proveedor"
                + "	WHERE id = ?";
        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            stmSer.executeUpdate();
        }
    }

}
