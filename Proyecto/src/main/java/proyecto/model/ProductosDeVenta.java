package proyecto.model;

import java.time.LocalDate;

public class ProductosDeVenta {

    private long idProducto;
    private long idVenta;
    private float precio;
    private float cantidad;
    private LocalDate createAt;
    private LocalDate updateAt;

    public ProductosDeVenta(long idProducto, long idVenta, float precio, float cantidad, LocalDate createAt, LocalDate updateAt) {
        this.idProducto = idProducto;
        this.idVenta = idVenta;
        this.precio = precio;
        this.cantidad = cantidad;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
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