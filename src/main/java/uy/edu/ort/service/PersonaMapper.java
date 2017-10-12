package uy.edu.ort.service;

import org.springframework.stereotype.Component;
import uy.edu.ort.dto.GuardarPersonaDTO;
import uy.edu.ort.dto.PersonaDTO;
import uy.edu.ort.model.Persona;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonaMapper {

    public PersonaDTO toDTO(Persona persona) {
        return new PersonaDTO(persona.getId(), persona.getNombre(), persona.getApellido());
    }

    public Persona toEntity(PersonaDTO dto) {
        return new Persona(dto.getId(), dto.getNombre(), dto.getApellido());
    }

    public Persona toEntity(GuardarPersonaDTO dto) {
        return new Persona(dto.getNombre(), dto.getApellido());
    }

    public List<PersonaDTO> toDTOs(List<Persona> personas) {
        return personas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Persona> toEntities(List<PersonaDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
