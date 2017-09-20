package uy.edu.ort.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uy.edu.ort.dto.GuardarPersonaDTO;
import uy.edu.ort.dto.PersonaDTO;
import uy.edu.ort.exceptions.PersonaNotFoundException;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonaServiceImpl implements PersonaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaServiceImpl.class);

    private Map<Long, PersonaDTO> personas = new LinkedHashMap<>();

    private AtomicLong idCounter = new AtomicLong();

    @Override
    public long guardarPersona(GuardarPersonaDTO persona) {
        long id = idCounter.incrementAndGet();
        PersonaDTO nuevaPersona = new PersonaDTO(id, persona.getNombre(), persona.getApellido());
        personas.put(id, nuevaPersona);
        return id;
    }

    @Override
    public void actualizarPersona(long id, GuardarPersonaDTO persona) {
        //Para validar que exista
        obtenerPersona(id);
        personas.put(id , new PersonaDTO(id, persona.getNombre(), persona.getApellido()));
    }

    @Override
    public void borrarPersona(long personaId) {
        PersonaDTO personaBorrada = personas.remove(personaId);
        if(personaBorrada == null){
            throw new PersonaNotFoundException();
        }
    }

    @Override
    public List<PersonaDTO> obtenerPersonas() {
        return new ArrayList<>(personas.values());
    }

    @Override
    public PersonaDTO obtenerPersona(long personaId) {
        PersonaDTO persona = personas.get(personaId);
        if(persona == null){
            throw new PersonaNotFoundException();
        }
        return persona;
    }
}
