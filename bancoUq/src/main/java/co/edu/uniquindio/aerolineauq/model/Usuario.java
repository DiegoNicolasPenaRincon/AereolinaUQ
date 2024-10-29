package co.edu.uniquindio.aerolineauq.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Usuario extends Persona {
    //lista de tiquetes comprados


    public Usuario(String id, String nombre, String apellido, String direccion, LocalDate fechaNacimiento, String correo, String contrasenia) {
        super(id, nombre, apellido, direccion, fechaNacimiento, correo, contrasenia);
    }

    public Usuario() {
    }
}
