package co.edu.uniquindio.aerolineauq.model;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.exceptions.ExcesoDeTripulantesException;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;


public class Aerolinea implements Serializable {
    private static final long serialVersionUID = 1L;

    ListaSimple<Usuario> listaUsuarios = new ListaSimple<>();
    ListaSimple<Tripulante> listaTripulantes = new ListaSimple<>();
    ListaSimple<Tiquete> listaTiquetes=new ListaSimple<>();
    ListaSimple<Ruta> rutasAerolinea=new ListaSimple<>();
    ListaSimple<Avion> listaAviones=new ListaSimple<>();
    ListaSimple<Equipaje> listaEquipaje=new ListaSimple<>();
    public Usuario usuario;

    public Aerolinea() {
    }

    public ListaSimple<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

    public void setListaEquipaje(ListaSimple<Equipaje> listaEquipaje) {
        this.listaEquipaje = listaEquipaje;
    }

    public ListaSimple<Equipaje> getListaEquipaje() {
        return listaEquipaje;
    }

    public ListaSimple<Tiquete> getListaTiquetes() {
        return listaTiquetes;
    }

    public void setListaTiquetes(ListaSimple<Tiquete> listaTiquetes) {
        this.listaTiquetes = listaTiquetes;
    }

    public ListaSimple<Ruta> getRutasAerolinea() {
        return rutasAerolinea;
    }

    public void setRutasAerolinea(ListaSimple<Ruta> rutasAerolinea) {
        this.rutasAerolinea = rutasAerolinea;
    }


    public void registrarUsuario(Usuario usuario) {
        listaUsuarios.agregar(usuario);
        Persistencia.guardaRegistroLog("Se ha registrado al usuario"+usuario.getNombre()+"con ID:"+usuario.getId(), 1, "Registro Usuario");
    }

    // metodo para validar el inicio de sesión
    public boolean validarInicioSesion(String id, String contrasenia) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado con ID: " + id);
            return false; // Usuario no encontrado
        }
        this.usuario = usuario;
        System.out.println("Usuario ingresado: " + usuario.getId());
        return usuario.getContrasenia().equals(contrasenia);
    }


    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario user : listaUsuarios) {
            if (user.getId().equalsIgnoreCase(id)) {
                return user;
            }
        }
        return null;
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

    public void registrarTripulante(Tripulante tripulante) {
        listaTripulantes.agregar(tripulante);
        Persistencia.guardaRegistroLog("Se ha registrado al tripulante"+tripulante.getNombre()+"con ID:"+tripulante.getId(), 1, "Registro Tripulante");
    }

    public boolean verificarTripuExistente(String id) throws Exception {
        if (this.tripulanteExiste(id)) {
            throw new Exception("El tripulante con cedula: " + id + " ya existe");
        } else {
            return false;
        }
    }

    public boolean eliminarTripulanteGlobal(String idTripulante) {
        Predicate<Tripulante> criterio = tripulante -> tripulante.getId().equals(idTripulante);

        boolean eliminado = listaTripulantes.eliminarElemento(criterio);


        for (Avion avion : listaAviones.toCollection()) {
            avion.getListaTripulantes().eliminarElemento(criterio);
        }

        return eliminado;
    }


    public boolean tripulanteExiste(String id) {
        boolean tripulanteEncontrado = false;
        Iterator var3 = this.getListaTripulantes().iterator();

        while(var3.hasNext()) {
            Tripulante user = (Tripulante) var3.next();
            if (user.getId().equalsIgnoreCase(id)) {
                tripulanteEncontrado = true;
                break;
            }
        }

        return tripulanteEncontrado;
    }

    public Tripulante obtenerTripulante(String id) {
        Tripulante tripulanteEncontrado = null;
        Iterator var3 = this.getListaTripulantes().iterator();

        while(var3.hasNext()) {
            Tripulante user = (Tripulante) var3.next();
            if (user.getId().equalsIgnoreCase(id)) {
                tripulanteEncontrado = user;
                break;
            }
        }

        return tripulanteEncontrado;
    }

    public boolean actualizarTripulante(String id, Tripulante empleado) throws Exception {
        Tripulante empleadoActual = this.obtenerTripulante(id);
        if (empleadoActual == null) {
            throw new Exception("El tripulante a actualizar no existe");
        } else {
            empleadoActual.setId(empleado.getId());
            empleadoActual.setNombre(empleado.getNombre());
            empleadoActual.setApellido(empleado.getApellido());
            empleadoActual.setDireccion(empleado.getDireccion());
            empleadoActual.setCorreo(empleado.getCorreo());
            empleadoActual.setEstudios(empleado.getEstudios());
            empleadoActual.setFechaNacimiento(empleado.getFechaNacimiento());
            empleadoActual.setRolTripulante(empleado.getRolTripulante());
            return true;
        }
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

    public void registrarTiquete(Tiquete tiquete) {
        listaTiquetes.agregar(tiquete);
        Persistencia.guardaRegistroLog("Se ha registrado un nuevo tiquete para el usuario " + tiquete.getUsuario().getId(), 1, "Registro Tiquete");
    }

    public Ruta buscarRutaPorDestino(Destino destino) {
        for (Ruta ruta : rutasAerolinea) {
            if (ruta.getDestino() == destino) {
                return ruta; // Retorna la ruta si se encuentra
            }
        }
        return null; // Retorna null si no se encuentra la ruta
    }


    public Collection<Avion> filtrarAvionesNacionales(ListaSimple<Avion> coleccionAviones,TipoAvion tipo) {
        ListaSimple<Avion> filtrarAvionesNacionales= new ListaSimple<>();
        for(int i=0;i<coleccionAviones.size();i++)
        {
            if(coleccionAviones.obtenerValorNodo(i).getTipoAvion().equals(tipo))
            {
                filtrarAvionesNacionales.agregar(coleccionAviones.obtenerValorNodo(i));
            }
        }

        return filtrarAvionesNacionales.toCollection();
    }


    public ListaSimple<Tripulante> verificarAsignacionYAgregar(String nombreAvion, ListaSimple<Tripulante> listaTripulantes, Tripulante tripulante) throws Exception {
        int copilotos = 0;
        int pilotos = 0;
        int auxiliares = 0;
        if(tripulante.getRolTripulante().equals(RolTripulante.AUXILIAR))
        {
            auxiliares++;
        }
        else if(tripulante.getRolTripulante().equals(RolTripulante.COPILOTO))
        {
            copilotos++;
        }
        else if(tripulante.getRolTripulante().equals(RolTripulante.PILOTO))
        {
            pilotos++;
        }
        for (int i = 0; i < listaTripulantes.size(); i++)
        {
            switch (listaTripulantes.obtenerValorNodo(i).getRolTripulante()) {
                case PILOTO:
                    pilotos++;
                    break;
                case COPILOTO:
                    copilotos++;
                    break;
                case AUXILIAR:
                    auxiliares++;
                    break;
            }
        }


        switch (nombreAvion) {
            case "Airbus A320":
                if (copilotos < 1&&pilotos < 1&&auxiliares < 3)
                {
                    listaTripulantes.agregar(tripulante);
                }
                else if(copilotos > 1)
                {
                    throw new Exception("Exceso de copilotos en el avion");
                }
                else if(pilotos > 1)
                {
                    throw new Exception("Exceso de pilotos en el avion");
                }
                else if(auxiliares > 3)
                {
                    throw new Exception("Exceso de auxiliares en el avion");
                }
                break;
            case "Airbus A330", "Boeing 787":
                if (copilotos < 1&&pilotos < 1&&auxiliares < 7)
                {
                    listaTripulantes.agregar(tripulante);
                }
                else if(copilotos > 1)
                {
                    throw new Exception("Exceso de copilotos en el avion");
                }
                else if(pilotos > 1)
                {
                    throw new Exception("Exceso de pilotos en el avion");
                }
                else if(auxiliares > 3)
                {
                    throw new Exception("Exceso de auxiliares en el avion");
                }
                break;
        }
        return listaTripulantes;


    }


    public ListaSimple buscarTiquetesRelacionados(Ruta ruta, LocalDate fechaViaje) {
        ListaSimple<Tiquete> listaTiquetesRelacionados=new ListaSimple<>();
        for(Tiquete tiquete: listaTiquetes){
            if(tiquete.getRuta().getDestino()==ruta.getDestino() && tiquete.getFechaViaje()==fechaViaje){
                listaTiquetesRelacionados.agregar(tiquete);
            }
        }
        return listaTiquetesRelacionados;
    }

    public void registrarEquipaje(Equipaje equipaje) {
        listaEquipaje.agregar(equipaje);
    }
}
