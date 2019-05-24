package proyecto.model;

import java.time.LocalDate;

public class Producto {

    private long id;
    private String name;
    private String description;
    private float precio;
    private boolean perecederos;
    private long idProveedor;
    private LocalDate createAt;
    private LocalDate updateAt;

    public Producto() {
        this.id = -1;
    }

    public Producto(String name, String description, float precio, boolean perecederos, long idProveedor, LocalDate createAt, LocalDate updateAt) {
        this.id = -1;
        this.name = name;
        this.description = description;
        this.precio = precio;
        this.perecederos = perecederos;
        this.idProveedor = idProveedor;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Producto(long id, String name, String description, float precio, boolean perecederos, long idProveedor, LocalDate createAt, LocalDate updateAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.precio = precio;
        this.perecederos = perecederos;
        this.idProveedor = idProveedor;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPerecederos() {
        return perecederos;
    }

    public void setPerecederos(boolean perecederos) {
        this.perecederos = perecederos;
    }

    public long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
