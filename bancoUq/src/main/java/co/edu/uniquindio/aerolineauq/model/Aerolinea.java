package co.edu.uniquindio.aerolineauq.model;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Iterator;

@Getter
@Setter
public class Aerolinea implements Serializable {
    private static final long serialVersionUID = 1L;

    ListaSimple<Usuario> listaUsuarios = new ListaSimple<>();
    ListaSimple<Tripulante> listaTripulantes = new ListaSimple<>();
    ListaSimple<Tiquete> listaTiquetes=new ListaSimple<>();
    ListaSimple<Ruta> rutasAerolinea=new ListaSimple<>();
    ListaSimple<Avion> avionesDisponibles=new ListaSimple<>();

    public Usuario usuario;


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

    public ListaSimple<Avion> filtrarNacionalesInternacionales(int estado) {
        ListaSimple<Avion> listaFiltrada = new ListaSimple<>();
        for(int i=0;i<avionesDisponibles.getTamanio();i++)
        {
            if(estado==1)
            {
                if(avionesDisponibles.obtenerValorNodo(i).getTipoAvion().equals(TipoAvion.INTERNACIONAL))
                {
                    listaFiltrada.agregar(avionesDisponibles.obtenerValorNodo(i));
                }
            }
            else
            {
                if(avionesDisponibles.obtenerValorNodo(i).getTipoAvion().equals(TipoAvion.NACIONAL))
                {
                    listaFiltrada.agregar(avionesDisponibles.obtenerValorNodo(i));
                }
            }
        }
        return listaFiltrada;
    }





}
