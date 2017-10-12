package uy.edu.ort.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import uy.edu.ort.model.Persona;

import java.util.List;

@Repository
public class PersonaDaoHibernateImpl implements PersonaDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void guardarOActualizar(Persona persona) {
        this.hibernateTemplate.saveOrUpdate(persona);
    }

    @Override
    public void borrar(Persona persona) {
        this.hibernateTemplate.delete(persona);
    }

    @Override
    public List<Persona> obtenerTodos() {
        return this.hibernateTemplate.loadAll(Persona.class);
    }

    @Override
    public Persona obtener(long personaId) {
        return this.hibernateTemplate.get(Persona.class, personaId);
    }
}
