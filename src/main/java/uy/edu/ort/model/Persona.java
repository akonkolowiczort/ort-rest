package uy.edu.ort.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Persona extends EntityClass {

    @Column
    private String nombre;

    @Column
    private String apellido;

    public Persona() {
        super();
    }

    public Persona(String nombre, String apellido) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Persona(long id, String nombre, String apellido) {
        super();
        this.setId(id);
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
