package uy.edu.ort.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name="persona")
@XmlAccessorType(XmlAccessType.NONE)
public class PersonaXMLDTO implements Serializable{

    @XmlAttribute
    private long id;

    @XmlElement
    private String nombre;

    @XmlElement
    private String apellido;

    private PersonaXMLDTO() {
    }

    public PersonaXMLDTO(long id, String nombre, String apellido) {
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

        return Objects.equals(id, ((PersonaXMLDTO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
