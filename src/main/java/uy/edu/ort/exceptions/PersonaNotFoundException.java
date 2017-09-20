package uy.edu.ort.exceptions;

public class PersonaNotFoundException extends RuntimeException {
    public PersonaNotFoundException() {
        super("Persona no encontrada");
    }
}
