package proyecto.db;

import proyecto.config.Configuration;
import proyecto.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class ClienteDB extends DB {

    private final static Logger logger = Logger.getLogger(ClienteDB.class);
    private static ClienteDB instance;

    private ClienteDB(String host, String inst, String port, String db, String usuario, String password) throws Exception {
        super(host, inst, port, db, usuario, password);
    }

    public static ClienteDB getInstance() {
        if (instance == null) {
            try {
                Configuration c = Configuration.getInstance();
                instance = new ClienteDB(
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

    public boolean clienteExist(String name) throws Exception {

        String resName = null;
        String description = null;

        String query = "SELECT name, description "
                + "	FROM Cliente WHERE name = ?";

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

    public boolean saveCliente(Cliente cliente) throws Exception {
        boolean nueva = cliente.getId() == -1;

        if (clienteExist(cliente.getName()) && nueva) {
            JOptionPane.showMessageDialog(null, "ya tienes un cliente con ese nombre");
            return false;
        }

        String query;
        try (Connection con = getConnection()) {
            PreparedStatement stm;

            if (nueva) {
                query = "INSERT INTO Cliente("
                        + "	name, description) "
                        + "	VALUES (?, ?);";
                stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            } else {
                query = "UPDATE Cliente"
                        + "	SET name=?, description=?"
                        + "	WHERE id=?";
                stm = con.prepareStatement(query);
                stm.setLong(3, cliente.getId());
            }

            stm.setString(1, cliente.getName());
            stm.setString(2, cliente.getDescription());

            stm.executeUpdate();
            stm.close();
        }
        return true;
    }

    public ArrayList<Cliente> getAllClientes() throws Exception {
        ArrayList<Cliente> proveedores = new ArrayList();

        String query = "SELECT id, name, description, createAt, updateAt "
                + "	FROM Cliente";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rsSer.getLong("id"));
                    cliente.setName(rsSer.getString("name"));
                    cliente.setDescription(rsSer.getString("description"));
                    cliente.setCreateAt(rsSer.getObject("createAt", LocalDate.class));
                    cliente.setUpdateAt(rsSer.getObject("updateAt", LocalDate.class));
                    proveedores.add(cliente);
                }
            }
        }
        return proveedores;
    }

    public Cliente getClienteByName(String str) throws Exception {
        Cliente cliente = null;

        String query = "SELECT id, name, description, createAt, updateAt "
                + "	FROM Cliente WHERE name=?";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setString(1, str);
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    cliente = new Cliente();
                    cliente.setId(rsSer.getLong("id"));
                    cliente.setName(rsSer.getString("name"));
                    cliente.setDescription(rsSer.getString("description"));
                    cliente.setCreateAt(rsSer.getObject("createAt", LocalDate.class));
                    cliente.setUpdateAt(rsSer.getObject("updateAt", LocalDate.class));
                }
            }
        }
        return cliente;
    }

    public Cliente getClienteById(long id) throws Exception {
        Cliente cliente = null;

        String query = "SELECT id, name, description, createAt, updateAt "
                + "	FROM Cliente WHERE id=?";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    cliente = new Cliente();
                    cliente.setId(rsSer.getLong("id"));
                    cliente.setName(rsSer.getString("name"));
                    cliente.setDescription(rsSer.getString("description"));
                    cliente.setCreateAt(rsSer.getObject("createAt", LocalDate.class));
                    cliente.setUpdateAt(rsSer.getObject("updateAt", LocalDate.class));
                }
            }
        }
        return cliente;
    }

    public void deleteProveedor(Long id) throws Exception {
        String query = "DELETE FROM Cliente"
                + "	WHERE id = ?";
        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, id);
            stmSer.executeUpdate();
        }
    }

}
