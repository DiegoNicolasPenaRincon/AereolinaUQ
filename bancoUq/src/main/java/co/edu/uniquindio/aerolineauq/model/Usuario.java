package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class Usuario extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    //lista de tiquetes comprados

    public Usuario(String id, String nombre, String apellido, String direccion, LocalDate fechaNacimiento, String correo, String contrasenia) {
        super(id, nombre, apellido, direccion, fechaNacimiento, correo, contrasenia);
    }

    public Usuario() {
    }
}
