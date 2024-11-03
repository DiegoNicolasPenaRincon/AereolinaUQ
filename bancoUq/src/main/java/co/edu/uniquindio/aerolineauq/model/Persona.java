package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona   implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String apellido;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String correo;
    private String contrasenia;
}
