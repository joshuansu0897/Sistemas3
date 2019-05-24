package proyecto.model;

import java.time.LocalDate;

public class Venta {

    private long id;
    private long idCliente;
    private long idSucursal;
    private long idEmpleado;
    private LocalDate createAt;
    private LocalDate updateAt;

    public Venta() {
        this.id = -1;
    }

    public Venta(long idCliente, long idSucursal, long idEmpleado, LocalDate createAt, LocalDate updateAt) {
        this.id = -1;
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;
        this.idEmpleado = idEmpleado;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Venta(long id, long idCliente, long idSucursal, long idEmpleado, LocalDate createAt, LocalDate updateAt) {
        this.id = id;
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;
        this.idEmpleado = idEmpleado;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }
}