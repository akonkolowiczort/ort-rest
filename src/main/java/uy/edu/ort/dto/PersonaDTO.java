package uy.edu.ort.dto;

import java.io.Serializable;
import java.util.Objects;

public class PersonaDTO implements Serializable {

    private long id;

    private String nombre;

    private String apellido;

    private PersonaDTO() {
    }

    public PersonaDTO(long id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return Objects.equals(id, ((PersonaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "{ " +
                "\"id\": " + id + "," +
                "\"nombre\": " + nombre + "," +
                "\"apellido\": " + apellido +
                " }";
    }
}
