package co.edu.uniquindio.aerolineauq.controller;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.model.Aerolinea;
import co.edu.uniquindio.aerolineauq.model.Usuario;
import co.edu.uniquindio.aerolineauq.utils.AerolineaUtils;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelFactoryController {
    private static ModelFactoryController instance;
    private Aerolinea aerolinea;

    public ModelFactoryController() {
        System.out.println("Datos inicializados");// Inicializa la clase de lógica de negocio

        //1. carga los datos del utils
        //cargarDatosBase();
        //salvarDatosPrueba();

        //2. cargar desde los archivos
        cargarDatosDesdeArchivos();

        // Guardar y cargar desde el binario
        //cargarResourceBinario();
        //guardarResourceBinario();

        if (aerolinea == null) {
            cargarDatosBase();
        }

    }
    public Aerolinea getAerolinea() {
        return aerolinea;
    }


    public static ModelFactoryController getInstance() {
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
                aerolinea.registrarUsuario(usuario); // Delegar el registro a Aerolinea
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
        aerolinea = Persistencia.cargarRecursoBancoXML();
    }

    private void guardarResourceXML() {
        Thread thread = new Thread(() -> {
            Persistencia.guardarRecursoBancoXML(aerolinea);
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
    private void salvarDatosPrueba() {
        try {

            Persistencia.guardarUsuarios(getAerolinea().getListaUsuarios());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

}