package practicabasededatos.model;

public class Estudiante {

    private boolean activo;
    private long expediente;
    private String nombre;
    private String sexo;
    private long id;

    public Estudiante() {
        this.id = -1;
    }

    public Estudiante(long id, boolean activo, long expediente, String nombre, String sexo) {
        this.id = id;
        this.activo = activo;
        this.expediente = expediente;
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public long getExpediente() {
        return expediente;
    }

    public void setExpediente(long expediente) {
        this.expediente = expediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "activo:" + activo + ", expediente:" + expediente + ", nombre:" + nombre + ", sexo:" + sexo + ", id:" + id;
    }
}
