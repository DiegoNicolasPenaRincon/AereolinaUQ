package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


public class Tripulante  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String apellido;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String correo;
    private String estudios;
    private RolTripulante rolTripulante;
    private Avion avionAsignado;

    public Tripulante(String id, String nombre, String apellido ,String direccion, LocalDate fechaNacimiento, String correo, String estudios, RolTripulante rolTripulante) {
        this.id = id;
        this.nombre = nombre;
        this.apellido= apellido;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.estudios = estudios;
        this.rolTripulante = rolTripulante;
    }

    public Tripulante() {
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public RolTripulante getRolTripulante() {
        return rolTripulante;
    }

    public void setRolTripulante(RolTripulante rolTripulante) {
        this.rolTripulante = rolTripulante;
    }

    public Avion getAvionAsignado() {
        return avionAsignado;
    }

    public void setAvionAsignado(Avion avionAsignado) {
        this.avionAsignado = avionAsignado;
    }
}
