/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventasds3.model;

/**
 *
 * @author joshuansu
 */
public class Empleado {
    private long id;
    private String nombre;
    private double salario;

    public Empleado() {
        this.id = -1;
    }
    
    public Empleado(String nombre, double salario) {
        this.id = -1;
        this.nombre = nombre;
        this.salario = salario;
    }

    public Empleado(long id, String nombre, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
