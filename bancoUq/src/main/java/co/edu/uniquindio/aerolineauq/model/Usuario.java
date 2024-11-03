package co.edu.uniquindio.aerolineauq.model;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;

import java.io.Serializable;
import java.time.LocalDate;

public class Usuario extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private ListaSimple<Tiquete> tiquetesComprados;

    public Usuario(String id, String nombre, String apellido, String direccion, LocalDate fechaNacimiento, String correo, String contrasenia) {
        super(id, nombre, apellido, direccion, fechaNacimiento, correo, contrasenia);
        this.tiquetesComprados = new ListaSimple<>();
    }

    public Usuario() {
        super();
        this.tiquetesComprados = new ListaSimple<>(); // Inicializa la lista de tiquetes comprados
    }
    public ListaSimple<Tiquete> getTiquetesComprados() {
        return tiquetesComprados;
    }
    public void setTiquetesComprados(ListaSimple<Tiquete> tiquetesComprados) {
        this.tiquetesComprados = tiquetesComprados;
    }
    public String getId() {
        return super.getId();
    }
    public void setId(String id) {
        super.setId(id);
    }
    public String getNombre() {
        return super.getNombre();
    }
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }
    public String getApellido() {
        return super.getApellido();
    }
    public void setApellido(String apellido) {
        super.setApellido(apellido);
    }
    public String getDireccion() {
        return super.getDireccion();
    }
    public void setDireccion(String direccion) {
        super.setDireccion(direccion);
    }
    public LocalDate getFechaNacimiento() {
        return super.getFechaNacimiento();
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        super.setFechaNacimiento(fechaNacimiento);
    }
    public String getCorreo() {
        return super.getCorreo();
    }
    public void setCorreo(String correo) {
        super.setCorreo(correo);
    }
    public String getContrasenia() {
        return super.getContrasenia();
    }
    public void setContrasenia(String contrasenia) {
        super.setContrasenia(contrasenia);
    }
}
