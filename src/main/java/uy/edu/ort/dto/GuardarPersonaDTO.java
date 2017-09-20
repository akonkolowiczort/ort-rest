package uy.edu.ort.dto;

public class GuardarPersonaDTO {

    private String nombre;

    private String apellido;

    private GuardarPersonaDTO() {
    }

    public GuardarPersonaDTO(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

}
