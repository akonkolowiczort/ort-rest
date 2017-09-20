package uy.edu.ort.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


@XmlRootElement(name="personas")
public class PersonasXMLDTO implements Serializable {

    @XmlElement
    private List<PersonaXMLDTO> personas;

    private PersonasXMLDTO() {
    }

    public PersonasXMLDTO(List<PersonaXMLDTO> personas) {
        this.personas = personas;
    }

    public List<PersonaXMLDTO> getPersonas() {
        return personas;
    }
}
