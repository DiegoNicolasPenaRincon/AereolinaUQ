package co.edu.uniquindio.aerolineauq.controller;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.exceptions.UsuarioException;
import co.edu.uniquindio.aerolineauq.model.*;
import co.edu.uniquindio.aerolineauq.utils.AerolineaUtils;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController {
    private static ModelFactoryController instance;
    private Aerolinea aerolinea;

    public ModelFactoryController() throws IOException {
        System.out.println("Datos inicializados");// Inicializa la clase de lógica de negocio

        //1. carga los datos del utils
        cargarDatosBase();
        //salvarDatosPrueba();

        //2. cargar desde los archivos
        //cargarDatosDesdeArchivos();

        // Guardar y cargar desde el binario
        //cargarResourceBinario();
        guardarResourceBinario();

        //cargarResourceXML();
        //guardarResourceXML();

        if (aerolinea == null) {
            cargarDatosBase();
            guardarResourceBinario();
        }

    }
    public Aerolinea getAerolinea() {
        return aerolinea;
    }


    public static ModelFactoryController getInstance() throws IOException {
        if (instance == null) {
            instance = new ModelFactoryController();
        }
        return instance;
    }

    // Inicializa los datos del arxhivo utils
    private void cargarDatosBase() {
        aerolinea = AerolineaUtils.inicializarDatos();
    }

    //metodo que registra a un usuario
    public void registrarUsuario(String id, String nombre, String apellido, String correo, String direccion, String contrasenia, LocalDate fechaNacimiento) {
        Usuario usuario = new Usuario(id, nombre, apellido, direccion, fechaNacimiento, correo, contrasenia);
        try {
            if (!aerolinea.verificarUsuarioExistente(usuario.getId())) {
                aerolinea.registrarUsuario(usuario);
                guardarResourceBinario();
                guardarResourceXML();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarUsuario(Usuario usuarioActualizado) throws UsuarioException {
        boolean actualizado = aerolinea.getListaUsuarios().modificarElemento(
                usuario -> usuario.getId().equals(getUsuarioActual().getId()), // Criterio: buscar por ID
                usuarioActualizado);

        if (actualizado) {
            System.out.println("Usuario actualizado correctamente.");
            guardarListaUsuario(aerolinea.getListaUsuarios());
            guardarResourceXML();
            guardarResourceBinario();
        } else {
            System.out.println("No se encontró el usuario con el ID especificado.");
            throw new UsuarioException("No se encontró el usuario.");
        }
    }

    public void registrarTripulante(String id, String nombre, String direccion, LocalDate fechaNacimiento,String correo , String estudios, RolTripulante rolTripulante) {
        Tripulante tripulante = new Tripulante(id, nombre, direccion, fechaNacimiento, correo,estudios,rolTripulante );
        try {
            if (!aerolinea.verificarTripuExistente(tripulante.getId())) {
                aerolinea.registrarTripulante(tripulante);
                guardarResourceBinario();
                guardarResourceXML();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validarInicioSesion(String id, String contrasenia) {
        return aerolinea.validarInicioSesion(id, contrasenia);
    }

    // Cargar y guardar los xml
    private void cargarResourceXML() {
        aerolinea = Persistencia.cargarRecursoXML();
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoXML(aerolinea);
    }
    // Carga y guarda los binarios
    private void cargarResourceBinario() {
        aerolinea = Persistencia.cargarRecursoBinario();
    }

    private void guardarResourceBinario() {
        Thread thread = new Thread(() -> {
            Persistencia.guardarRecursoBinario(aerolinea);
        });
        thread.start();
    }

    // Guarda los datos diferentes datos que esten en los utils
    private void salvarDatosPrueba() throws IOException {

         Persistencia.guardarUsuarios(getAerolinea().getListaUsuarios());

        Persistencia.guardarRecursoBinario(getAerolinea());

      //  Persistencia.guardarEquipaje(getAerolinea().getListaEquipaje());

        Persistencia.guardarRecursoXML(getAerolinea());
    }
    // Carga los datos desde los archivos (txt dat xml)
    private void cargarDatosDesdeArchivos() {
        aerolinea = new Aerolinea();
        try {
            Persistencia.cargarDatosArchivos(aerolinea);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario getUsuarioActual(){
        return aerolinea.getUsuario();
    }

    public ListaSimple getListaRutas(){
        return aerolinea.getRutasAerolinea();
    }

    public void guardarListaUsuario(ListaSimple<Usuario> listaUsuario) {
        Thread thread = new Thread(() -> {
            try {
                Persistencia.guardarUsuarios(listaUsuario);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }


   public void registrarEquipaje(String numeroVuelo, double pesoEquipaje, boolean esMascota, double pesoMascota, String categoriaViaje, ClaseVuelo claseVuelo) {
        Tiquete tiquete = buscarTiquetePorNumero(numeroVuelo);

        if (tiquete != null) {
            Equipaje equipaje = new Equipaje(pesoEquipaje, esMascota, pesoMascota, categoriaViaje, claseVuelo);
            tiquete.agregarEquipaje(equipaje);
            guardarResourceBinario();
            guardarResourceXML();
        } else {
            System.out.println("Tiquete no encontrado.");
        }
    }


    public Tiquete buscarTiquetePorNumero(String numeroVuelo) {
        for (Tiquete tiquete : aerolinea.getListaTiquetes()) {
            if (tiquete.getNumeroVuelo().equals(numeroVuelo)) {
                return tiquete;
            }
        }
        return null;
    }

    public Tiquete registrarCompra(String numeroVuelo, Usuario usuario, Ruta ruta, double precio, ClaseVuelo claseVuelo, Silla silla, TipoViaje tipoViaje, LocalDate fechaViaje, LocalDate fechaRegreso, Equipaje equipaje) {
        if (!validarDatosCompra(usuario, ruta, claseVuelo)) {
            throw new IllegalArgumentException("Datos de compra inválidos.");
        }

        // Calcula el precio
       // double precio = calcularPrecio(ruta, claseVuelo, tipoViaje, pesoEquipaje, esMascota);

        // Crea el tiquete y equipaje
        //Equipaje equipaje = new Equipaje(pesoEquipaje, esMascota, pesoMascota, tipoViaje.toString(), claseVuelo);
        // Silla silla = asignarSilla(claseVuelo);
        Tiquete tiquete = new Tiquete(numeroVuelo, usuario, ruta, precio,claseVuelo,silla,tipoViaje, fechaViaje,fechaRegreso,equipaje);

        // Asigna el equipaje al tiquete
        tiquete.setEquipaje(equipaje);
        aerolinea.registrarTiquete(tiquete); // Añade el tiquete a la aerolínea

        // Registra la acción en el log
        registrarAccionesSistema("Compra realizada para el usuario: " + usuario.getId(), 1, "Compra Tiquete");

        return tiquete;
    }

    /**
     * Método para validar datos de la compra.
     */
    private boolean validarDatosCompra(Usuario usuario, Ruta ruta, ClaseVuelo claseVuelo) {
        return usuario != null && ruta != null && claseVuelo != null;
    }

    /**
     * Método para calcular el precio del tiquete según la ruta, clase, tipo de viaje y equipaje.
     */
    private double calcularPrecio(Ruta ruta, ClaseVuelo claseVuelo, TipoViaje tipoViaje, double pesoEquipaje, boolean esMascota) {
        //double precioBase = ruta.getDistancia() * 0.1; // Supón que el precio base depende de la distancia
        double precioBase=0;
        precioBase += claseVuelo == ClaseVuelo.EJECUTIVA ? 50 : claseVuelo == ClaseVuelo.ECONOMICA ? 20 : 30;
        precioBase += (tipoViaje == TipoViaje.idaYvuelta ? 0.8 * precioBase : 0);
        precioBase += pesoEquipaje * 2.0; // Añade un costo por peso del equipaje
        precioBase += esMascota ? 30 : 0; // Añade un costo si hay una mascota
        return precioBase;
    }

    /**
     * Método para asignar una silla disponible según la clase de vuelo.
     */
    /*
    private Silla asignarSilla(ClaseVuelo claseVuelo) {
        // Método para buscar y retornar una silla disponible según la clase de vuelo.
        // Para este ejemplo, devuelve una silla nueva.
        return new Silla(claseVuelo.toString(), true); // Asume que el asiento está disponible
    }


     */

    /**
     * Método para registrar acciones en el sistema.
     */
    private void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

    public Ruta buscarRutaPorDestino(Destino destino){
        return aerolinea.buscarRutaPorDestino(destino);
    }

    public String buscarAvionPorDestino(Destino destino){
        Ruta ruta=buscarRutaPorDestino(destino);
        return ruta.getAvionAsignado().getNombre();
    }

    public ListaSimple buscarTiquetesRelacionados(Destino destino, LocalDate fechaViaje){
        Ruta ruta=buscarRutaPorDestino(destino);
        return aerolinea.buscarTiquetesRelacionados(ruta, fechaViaje);
    }


    public ListaSimple<Tripulante> getListaTripulantes(){
        return aerolinea.getListaTripulantes();

    }

}