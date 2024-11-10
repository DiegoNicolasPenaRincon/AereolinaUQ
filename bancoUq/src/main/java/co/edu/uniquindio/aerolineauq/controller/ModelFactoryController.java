package co.edu.uniquindio.aerolineauq.controller;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.model.*;
import co.edu.uniquindio.aerolineauq.utils.AerolineaUtils;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelFactoryController {
    private static ModelFactoryController instance;
    private Aerolinea aerolinea;

    public ModelFactoryController() throws IOException {
        System.out.println("Datos inicializados");// Inicializa la clase de lógica de negocio

        //1. carga los datos del utils
        //cargarDatosBase();
        //salvarDatosPrueba();

        //2. cargar desde los archivos
        cargarDatosDesdeArchivos();

        // Guardar y cargar desde el binario
        cargarResourceBinario();
        guardarResourceBinario();

        //cargarResourceXML();
        //guardarResourceXML();

        if (aerolinea == null) {
            cargarDatosBase();
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
                guardarListaUsuario(getAerolinea().getListaUsuarios());
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
        Thread thread = new Thread(() -> {
            Persistencia.guardarRecursoXML(aerolinea);
        });
        thread.start();
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

    private void guardarListaUsuario(ListaSimple<Usuario> listaUsuario) {
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




}