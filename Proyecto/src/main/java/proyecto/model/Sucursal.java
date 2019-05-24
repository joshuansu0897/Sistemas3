package proyecto.model;

import java.time.LocalDate;

public class Sucursal {

    private long id;
    private String name;
    private String description;
    private LocalDate createAt;
    private LocalDate updateAt;

    public Sucursal() {
        this.id = -1;
    }

    public Sucursal(String name, String description, LocalDate createAt, LocalDate updateAt) {
        this.id = -1;
        this.name = name;
        this.description = description;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Sucursal(long id, String name, String description, LocalDate createAt, LocalDate updateAt) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
