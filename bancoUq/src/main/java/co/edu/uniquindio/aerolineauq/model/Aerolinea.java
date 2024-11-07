package co.edu.uniquindio.aerolineauq.model;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;

import java.io.Serializable;
import java.util.Iterator;


public class Aerolinea implements Serializable {
    private static final long serialVersionUID = 1L;

    ListaSimple<Usuario> listaUsuarios = new ListaSimple<>();
    ListaSimple<Tripulante> listaTripulantes = new ListaSimple<>();
    ListaSimple<Tiquete> listaTiquetes=new ListaSimple<>();
    ListaSimple<Ruta> rutasAerolinea=new ListaSimple<>();
    ListaSimple<Avion> listaAviones=new ListaSimple<>();

    public ListaSimple<Ruta> getRutasAerolinea() {
        return rutasAerolinea;
    }

    public void setRutasAerolinea(ListaSimple<Ruta> rutasAerolinea) {
        this.rutasAerolinea = rutasAerolinea;
    }

    public Usuario usuario;

    public ListaSimple<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }


    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }


    public void registrarUsuario(Usuario usuario) {
        listaUsuarios.agregar(usuario);
        Persistencia.guardaRegistroLog("Se ha registrado al usuario"+usuario.getNombre()+"con ID:"+usuario.getId(), 1, "Registro Usuario");
    }

    // metodo para validar el inicio de sesión
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

    public boolean verificarUsuarioExistente(String id) throws Exception {
        if (this.usuarioExiste(id)) {
            throw new Exception("El Usuario con cedula: " + id + " ya existe");
        } else {
            return false;
        }
    }
    public boolean usuarioExiste(String id) {
        boolean usuarioEncontrado = false;
        Iterator var3 = this.getListaUsuarios().iterator();

        while(var3.hasNext()) {
            Usuario user = (Usuario) var3.next();
            if (user.getId().equalsIgnoreCase(id)) {
                usuarioEncontrado = true;
                break;
            }
        }

        return usuarioEncontrado;
    }

    public void registrarEquipaje(Tiquete tiquete, double peso, boolean esMascota, double pesoMascota, String categoriaViaje) {
        Equipaje equipaje = new Equipaje(peso, esMascota, pesoMascota, categoriaViaje, tiquete.getClaseVuelo());
        tiquete.setEquipaje(equipaje);
        registrarAccionesSistema("Equipaje registrado para tiquete: " + tiquete, 1, "Registro Equipaje");
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

    public ListaSimple<Avion> getListaAviones() {
        return listaAviones;
    }

    public void setListaAviones(ListaSimple<Avion> listaAviones) {
        this.listaAviones = listaAviones;
    }
}
