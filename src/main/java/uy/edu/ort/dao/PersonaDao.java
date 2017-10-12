package uy.edu.ort.dao;

import uy.edu.ort.model.Persona;

import java.util.List;

public interface PersonaDao {
    void guardarOActualizar(Persona persona);

    void borrar(Persona persona);

    List<Persona> obtenerTodos();

    Persona obtener(long personaId);
}
