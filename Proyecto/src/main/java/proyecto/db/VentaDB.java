package proyecto.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import proyecto.config.Configuration;
import org.apache.log4j.Logger;
import proyecto.model.ProductosDeVenta;
import proyecto.model.Venta;
import proyecto.model.VistaVenta;
import proyecto.model.VistaVentas;

public class VentaDB extends DB {

    private final static Logger logger = Logger.getLogger(VentaDB.class);
    private static VentaDB instance;

    private VentaDB(String host, String inst, String port, String db, String usuario, String password) throws Exception {
        super(host, inst, port, db, usuario, password);
    }

    public static VentaDB getInstance() {
        if (instance == null) {
            try {
                Configuration c = Configuration.getInstance();
                instance = new VentaDB(
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

    public void saveVenta(Venta venta) throws Exception {
        long idVenta = 0;
        try (Connection con = getConnection()) {
            String query = "{ CALL SaveVenta(?,?,?,?) }";

            CallableStatement stm = con.prepareCall(query);

            stm.registerOutParameter(1, Types.INTEGER);
            stm.setLong(2, venta.getIdSucursal());
            stm.setLong(3, venta.getIdEmpleado());
            stm.setFloat(4, venta.getIdCliente());

            stm.execute();

            idVenta = stm.getLong(1);
        }

        for (ProductosDeVenta productosDeVenta : venta.getProductosDeVentas()) {
            productosDeVenta.setIdVenta(idVenta);
        }

        saveProductosDeVenta(venta.getProductosDeVentas());
    }

    private void saveProductosDeVenta(ArrayList<ProductosDeVenta> lista) throws Exception {
        if (lista == null || lista.isEmpty()) {
            return;
        }

        try (Connection con = getConnection()) {
            String query = "{ CALL SaveProductoDeVenta(?,?,?,?) }";
            CallableStatement stm = con.prepareCall(query);
            for (ProductosDeVenta pdv : lista) {
                stm.setLong(1, pdv.getIdProducto());
                stm.setLong(2, pdv.getIdVenta());
                stm.setFloat(3, pdv.getPrecio());
                stm.setFloat(4, pdv.getCantidad());
                stm.addBatch();
            }
            stm.executeBatch();
        }
    }

    public ArrayList<VistaVentas> getAllVetas() throws Exception {
        ArrayList<VistaVentas> ventas = new ArrayList();

        String query = "SELECT id, empleado, cliente, sucursal, fecha"
                + "	FROM VistaVentas";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    VistaVentas vv = new VistaVentas();
                    vv.setId(rsSer.getLong("id"));
                    vv.setEmpleado(rsSer.getString("empleado"));
                    vv.setCliente(rsSer.getString("cliente"));
                    vv.setSucursal(rsSer.getString("sucursal"));
                    vv.setFecha(rsSer.getObject("fecha", LocalDate.class));
                    ventas.add(vv);
                }
            }
        }
        return ventas;
    }

    public ArrayList<VistaVenta> getAllProductosDeVenta(long idVenta) throws Exception {
        ArrayList<VistaVenta> productos = new ArrayList();

        String query = "SELECT producto, precio, cantidad"
                + "	FROM VistaVenta WHERE idVenta=?";

        try (Connection conSer = getConnection(); PreparedStatement stmSer = conSer.prepareStatement(query)) {
            stmSer.setLong(1, idVenta);
            try (ResultSet rsSer = stmSer.executeQuery()) {
                while (rsSer.next()) {
                    VistaVenta vv = new VistaVenta();
                    vv.setProducto(rsSer.getString("producto"));
                    vv.setPrecio(rsSer.getFloat("precio"));
                    vv.setCantidad(rsSer.getFloat("cantidad"));
                    vv.setIdVenta(idVenta);
                    productos.add(vv);
                }
            }
        }
        return productos;
    }

}
