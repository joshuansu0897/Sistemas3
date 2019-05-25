package proyecto.model;

import java.time.LocalDate;

public class VistaVentas {

    private long id;
    private String empleado;
    private String cliente;
    private String sucursal;
    private LocalDate fecha;

    public VistaVentas() {
        this.id = -1;
    }

    public VistaVentas(String empleado, String cliente, String sucursal, LocalDate fecha) {
        this.empleado = empleado;
        this.cliente = cliente;
        this.sucursal = sucursal;
        this.fecha = fecha;
    }

    public VistaVentas(long id, String empleado, String cliente, String sucursal, LocalDate fecha) {
        this.id = id;
        this.empleado = empleado;
        this.cliente = cliente;
        this.sucursal = sucursal;
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
