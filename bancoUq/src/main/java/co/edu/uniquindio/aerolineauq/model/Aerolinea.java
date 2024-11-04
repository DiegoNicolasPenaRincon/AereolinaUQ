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

    public Usuario usuario;

    public ListaSimple<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }


    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }


    public void registrarUsuario(Usuario usuario) {
        listaUsuarios.agregar(usuario);
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
/*
    public void asignarTripulacion(Avion avion) {
        String tipoAvion = avion.getTipoAvion();
        ListaSimple<Tripulante> tripulacionAsignada = new ListaSimple<>();

        int numAuxiliares = 0;

        // Determinar la cantidad de auxiliares de vuelo necesarios
        if (tipoAvion.equalsIgnoreCase("Airbus A320")) {
            numAuxiliares = 3;
        } else if (tipoAvion.equalsIgnoreCase("Airbus A330") || tipoAvion.equalsIgnoreCase("Boeing 787")) {
            numAuxiliares = 7;
        }

        // Buscar y asignar el piloto y copiloto
        for (Tripulante tripulante : listaTripulantes) {
            if (tripulante.getRolTripulante().equalsIgnoreCase("Piloto")) {
                tripulacionAsignada.agregar(tripulante);
                break; // Solo necesitamos un piloto
            }
        }

        for (Tripulante tripulante : listaTripulantes) {
            if (tripulante.getRolTripulante().equalsIgnoreCase("Copiloto")) {
                tripulacionAsignada.agregar(tripulante);
                break; // Solo necesitamos un copiloto
            }
        }

        // Buscar y asignar los auxiliares de vuelo necesarios
        int auxiliaresAgregados = 0;
        for (Tripulante tripulante : listaTripulantes) {
            if (tripulante.getRolTripulante().equalsIgnoreCase("Auxiliar de vuelo") && auxiliaresAgregados < numAuxiliares) {
                tripulacionAsignada.agregar(tripulante);
                auxiliaresAgregados++;
            }
        }

        // Aquí puedes asignar la lista `tripulacionAsignada` al vuelo o al avión, según sea necesario
        System.out.println("Tripulación asignada para el avión " + tipoAvion + ": " + tripulacionAsignada);
    }

 */

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
