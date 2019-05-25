package proyecto.model;

public class VistaVenta {

    private long idVenta;
    private String producto;
    private float precio;
    private float cantidad;

    public VistaVenta() {
    }

    public VistaVenta(String producto, float precio, float cantidad) {
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public VistaVenta(long idVenta, String producto, float precio, float cantidad) {
        this.idVenta = idVenta;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
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

}
