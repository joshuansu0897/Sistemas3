package proyecto.model;

import java.time.LocalDate;

public class Empleado {

    private long id;
    private String name;
    private String username;
    private String password;
    private LocalDate createAt;
    private LocalDate updateAt;

    public Empleado() {
        this.id = -1;
    }

    public Empleado(String name, String username, String password, LocalDate createAt, LocalDate updateAt) {
        this.id = -1;
        this.name = name;
        this.username = username;
        this.password = password;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Empleado(long id, String name, String username, String password, LocalDate createAt, LocalDate updateAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
