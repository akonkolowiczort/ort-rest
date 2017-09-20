package uy.edu.ort.service;

import uy.edu.ort.dto.GuardarPersonaDTO;
import uy.edu.ort.dto.PersonaDTO;

import java.util.List;

public interface PersonaService {

    long guardarPersona(GuardarPersonaDTO persona);

    void actualizarPersona(long id, GuardarPersonaDTO persona);

    void borrarPersona(long personaId);

    List<PersonaDTO> obtenerPersonas();

    PersonaDTO obtenerPersona(long personaId);

}
