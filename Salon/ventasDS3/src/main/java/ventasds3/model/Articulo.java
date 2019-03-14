package ventasds3.model;

public class Articulo {

    private long id;
    private String nombre;
    private long cantidad;
    private double precio;
    private boolean activo;

    public Articulo() {
        this.id = -1;
    }

    public Articulo(String nombre, long cantidad, double precio, boolean activo) {
        this.id = -1;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.activo = activo;
    }

    public Articulo(long id, String nombre, long cantidad, double precio, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.activo = activo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
