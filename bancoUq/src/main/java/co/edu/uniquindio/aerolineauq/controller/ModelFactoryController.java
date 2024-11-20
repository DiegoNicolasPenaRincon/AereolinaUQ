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
    public double costoEquipaje=0;

    public ModelFactoryController() throws IOException {
        System.out.println("Datos inicializados");// Inicializa la clase de lógica de negocio

        //1. carga los datos del utils
        //cargarDatosBase();
        //salvarDatosPrueba();

        //2. cargar desde los archivos
        //cargarDatosDesdeArchivos();

        // Guardar y cargar desde el binario
        cargarResourceBinario();
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

    public Tripulante registrarTripulante(String id, String nombre, String apellido, String direccion, LocalDate fechaNacimiento, String correo , String estudios, RolTripulante rolTripulante) throws Exception {
        Tripulante tripulante = new Tripulante(id, nombre,apellido, direccion, fechaNacimiento, correo,estudios,rolTripulante );
        try {
            if (!aerolinea.verificarTripuExistente(tripulante.getId())) {
                throw new Exception("Ya existe un tripulante con la cédula (ID) ingresada: " + tripulante.getId());
            }
                aerolinea.registrarTripulante(tripulante);
                guardarResourceBinario();
                guardarResourceXML();

        } catch (Exception e) {
        System.err.println(e.getMessage());
        throw e;
        }
        return tripulante;
    }


    public void actualizarTripulante(Tripulante tripulanteActualizado) throws Exception {
        boolean actualizado = aerolinea.getListaTripulantes().modificarElemento(
                tripulante -> tripulante.getId().equals(tripulanteActualizado.getId()),
                tripulanteActualizado
        );

        if (actualizado) {
            System.out.println("Tripulante actualizado correctamente.");
            guardarResourceBinario();
            guardarResourceXML();
        } else {
            throw new Exception("No se encontró un tripulante con el ID especificado.");
        }
    }

    public boolean actualizarTripulante(String idActual, Tripulante tripulanteNuevo) {
        try {
            aerolinea.actualizarTripulante(idActual, tripulanteNuevo);
            guardarResourceBinario();
            guardarResourceXML();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public void eliminarTripulante(String idTripulante) throws Exception {
        boolean eliminado = aerolinea.eliminarTripulanteGlobal(idTripulante);

        if (eliminado) {
            System.out.println("Tripulante eliminado correctamente.");
            guardarResourceBinario();
            guardarResourceXML();
        } else {
            throw new Exception("No se encontró un tripulante con el ID especificado.");
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


    public void registrarCompraTiquete(Usuario usuario, String numeroVuelo, Ruta ruta, ClaseVuelo claseVuelo,
                                       List<Silla> sillas, TipoViaje tipoViaje, LocalDate fechaViaje, LocalDate fechaRegreso,
                                       double pesoEquipaje, boolean esMascota, double pesoMascota, int cantidadPersonas) {
        ListaSimple<Silla>sillasSeleccionadas=new ListaSimple<>();
        sillasSeleccionadas.addAll((ArrayList<Silla>) sillas);

        // 2. Calcular el precio (esto dependerá de tu lógica de precios)
        double precio = calcularPrecio(ruta, claseVuelo, tipoViaje, pesoEquipaje, esMascota, pesoMascota);

        // 3. Crear el equipaje
        Equipaje equipaje = new Equipaje();
        equipaje.setPesoEquipaje(pesoEquipaje);
        equipaje.setEsMascota(esMascota);
        equipaje.setPesoMascota(pesoMascota);
        equipaje.setCategoriaViaje(esInternacional(ruta) ? "Internacional" : "Nacional");
        equipaje.setClaseVuelo(claseVuelo);
        equipaje.setCostoAdicional(precio-costoEquipaje);

        aerolinea.getListaEquipaje().agregar(equipaje);
        for (int i = 0; i < cantidadPersonas; i++) {
            // Asegurarse de que haya suficientes sillas
            if (i >= sillas.size()) {
                throw new IllegalArgumentException("No hay suficientes sillas para el número de personas");
            }

            // Crear el tiquete para esta persona
            Tiquete tiquete = new Tiquete();
            tiquete.setNumeroVuelo(numeroVuelo);
            tiquete.setUsuario(usuario);
            tiquete.setRuta(ruta);
            tiquete.setPrecio(precio);  // El precio base podría ser ajustado dependiendo del equipaje y otros factores
            tiquete.setClaseVuelo(claseVuelo);
            tiquete.setSilla(sillasSeleccionadas.obtenerValorNodo(i));  // Asigna una silla única por persona
            tiquete.setTipoViaje(tipoViaje);
            tiquete.setFechaViaje(fechaViaje);
            tiquete.setFechaRegreso(fechaRegreso);
            tiquete.setEquipaje(equipaje);  // Asocia el mismo equipaje a todos los tiquetes

            // Registrar el tiquete en la aerolínea
            aerolinea.registrarTiquete(tiquete);
        }
        // 5. Registrar el tiquete en la aerolínea


        // 6. Guardar los cambios (en binario y XML, si es necesario)
        guardarResourceBinario();
        guardarResourceXML();
    }


    public Tiquete buscarTiquetePorNumero(String numeroVuelo) {
        for (Tiquete tiquete : aerolinea.getListaTiquetes()) {
            if (tiquete.getNumeroVuelo().equals(numeroVuelo)) {
                return tiquete;
            }
        }
        return null;
    }

    public double calcularPrecio(Ruta ruta, ClaseVuelo claseVuelo, TipoViaje tipoViaje, double pesoEquipaje, boolean esMascota, double pesoMascota) {
        double precioBase = ruta.getPrecio();

// Ajustar el precio base según la clase de vuelo
        precioBase += claseVuelo == ClaseVuelo.EJECUTIVA ? 300 : 0;
        if (esInternacional(ruta)) {
            precioBase += precioBase * 0.0097; // 0.97% adicional si es internacional
        } else {
            precioBase += precioBase * 0.008; // 0.8% adicional si es nacional
        }

        precioBase *= (tipoViaje == TipoViaje.idaYvuelta ? 2 : 1);
        int piezasPermitidas;
        double pesoMaximoPorPieza;
        if (esInternacional(ruta)) {
            if (claseVuelo == ClaseVuelo.EJECUTIVA) {
                piezasPermitidas = 2;
                pesoMaximoPorPieza = 34.0;
            } else { // Clase económica
                piezasPermitidas = 2;
                pesoMaximoPorPieza = 24.0;
            }
        } else { // Nacional
            if (claseVuelo == ClaseVuelo.EJECUTIVA) {
                piezasPermitidas = 2;
                pesoMaximoPorPieza = 34.0;
            } else { // Clase económica
                piezasPermitidas = 1;
                pesoMaximoPorPieza = 24.0;
            }
        }

// Calcular el peso adicional y su costo
        double pesoPermitidoTotal = piezasPermitidas * pesoMaximoPorPieza;
        double sobrepeso = Math.max(0, pesoEquipaje - pesoPermitidoTotal); // Peso que excede el límite permitido

        if (sobrepeso > 0) {
            double costoSobrepeso = sobrepeso * 8.0; // Cada kilo extra cuesta 8 dólares
            costoSobrepeso += costoSobrepeso * 0.0675; // Aplicar impuesto del 6.75% sobre el costo de sobrepeso
            precioBase += costoSobrepeso;
            costoEquipaje+=costoSobrepeso;
        }

        if(esMascota){
            if(pesoMascota>=3 && pesoMascota<=9){
                precioBase+=48;
                costoEquipaje+=48;
            }
            else if(pesoMascota>9){
                precioBase+=48+(2*pesoMascota);
                costoEquipaje+=48+(2*pesoMascota);
            }
        }
        return precioBase;
    }

    private boolean esInternacional(Ruta ruta) {
        if(ruta.getDestino()==Destino.Monterrey || ruta.getDestino()==Destino.Cancún){
            return false;
        }
        else{
            return true;
        }
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