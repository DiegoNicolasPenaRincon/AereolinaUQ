package co.edu.uniquindio.aerolineauq.model;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import java.io.Serializable;

public class Aerolinea implements Serializable {

    ListaSimple<Usuario> listaUsuarios = new ListaSimple<>();
    ListaSimple<Tripulante> listaTripulantes = new ListaSimple<>();

    public ListaSimple<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ListaSimple<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ListaSimple<Tripulante> getListaTripulantes() {
        return listaTripulantes;
    }

    public void setListaTripulantes(ListaSimple<Tripulante> listaTripulantes) {
        this.listaTripulantes = listaTripulantes;
    }

    public void registrarUsuario(Usuario usuario) {
        listaUsuarios.agregar(usuario);
    }

    // Método para validar el inicio de sesión
    public boolean validarInicioSesion(String id, String contrasenia) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return true; // Inicio de sesión válido
        }
        return false; // Usuario no encontrado o contraseña incorrecta
    }

    public Usuario buscarUsuarioPorId(String id) {
        return listaUsuarios.buscarUsuarioPorId(id);
    }

}
