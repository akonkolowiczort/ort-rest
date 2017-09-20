package uy.edu.ort.dto;

public class ErrorDTO {

    private int codigo;

    private String mensaje;

    private ErrorDTO() {
    }

    public ErrorDTO(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }
}
