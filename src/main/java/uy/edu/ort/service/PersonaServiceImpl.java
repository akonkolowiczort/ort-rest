package uy.edu.ort.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.edu.ort.dao.PersonaDao;
import uy.edu.ort.dto.GuardarPersonaDTO;
import uy.edu.ort.dto.PersonaDTO;
import uy.edu.ort.exceptions.PersonaNotFoundException;
import uy.edu.ort.model.Persona;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaServiceImpl.class);

    /**
     * If you want to use spring-data, use [uy.edu.ort.dao.PersonaDaoSpringData] instead.
     */
    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private PersonaMapper personaMapper;

    @Override
    @Transactional
    @CacheEvict(value= "personas", allEntries=true)
    public long guardarPersona(GuardarPersonaDTO persona) {
        Persona nuevaPersona = this.personaMapper.toEntity(persona);
        this.personaDao.guardarOActualizar(nuevaPersona);
        return nuevaPersona.getId();
    }

    @Override
    @Transactional
    @CacheEvict(value= "personas", allEntries=true)
    public void actualizarPersona(long id, GuardarPersonaDTO persona) {
        Persona personaExistente = this.personaDao.obtener(id);
        if(persona == null){
            throw new PersonaNotFoundException();
        }
        personaExistente.setNombre(persona.getNombre());
        personaExistente.setApellido(persona.getApellido());
        this.personaDao.guardarOActualizar(personaExistente);
    }

    @Override
    @Transactional
    @CacheEvict(value= "personas", allEntries=true)
    public void borrarPersona(long personaId) {
        Persona persona = this.personaDao.obtener(personaId);
        if(persona == null){
            throw new PersonaNotFoundException();
        }
        this.personaDao.borrar(persona);
    }

    @Override
    @Cacheable("personas")
    @Transactional(readOnly = true)
    public List<PersonaDTO> obtenerPersonas() {
        LOGGER.info("Executing obtenerPersonas method...");
        return this.personaMapper.toDTOs(this.personaDao.obtenerTodos());
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaDTO obtenerPersona(long personaId) {
        Persona persona = this.personaDao.obtener(personaId);
        if(persona == null){
            throw new PersonaNotFoundException();
        }
        return this.personaMapper.toDTO(persona);
    }
}
