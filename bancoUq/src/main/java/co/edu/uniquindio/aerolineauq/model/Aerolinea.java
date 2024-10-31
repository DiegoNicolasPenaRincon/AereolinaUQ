package co.edu.uniquindio.aerolineauq.model;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Aerolinea implements Serializable {

    ListaSimple<Usuario> listaUsuarios = new ListaSimple<>();
    ListaSimple<Tripulante> listaTripulantes = new ListaSimple<>();
    ListaSimple<Tiquete> listaTiquetes=new ListaSimple<>();
    public Usuario usuario;


    public void registrarUsuario(Usuario usuario) {
        listaUsuarios.agregar(usuario);
    }

    // Método para validar el inicio de sesión
    public boolean validarInicioSesion(String id, String contrasenia) {
        Usuario usuario = buscarUsuarioPorId(id);
        this.usuario=usuario;
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return true; // Inicio de sesión válido
        }
        return false; // Usuario no encontrado o contraseña incorrecta
    }

    public Usuario buscarUsuarioPorId(String id) {
        return listaUsuarios.buscarUsuarioPorId(id);
    }

}
