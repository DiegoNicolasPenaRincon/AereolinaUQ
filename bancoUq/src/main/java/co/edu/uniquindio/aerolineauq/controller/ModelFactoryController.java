package co.edu.uniquindio.aerolineauq.controller;

import co.edu.uniquindio.aerolineauq.model.Aerolinea;
import co.edu.uniquindio.aerolineauq.model.Usuario;
import co.edu.uniquindio.aerolineauq.utils.AerolineaUtils;

import java.time.LocalDate;

public class ModelFactoryController {
    private static ModelFactoryController instance;
    private Aerolinea aerolinea;

    private ModelFactoryController() {
        aerolinea = AerolineaUtils.inicializarDatos();
        System.out.println("Datos inicializados");// Inicializa la clase de lógica de negocio
    }

    public static ModelFactoryController getInstance() {
        if (instance == null) {
            instance = new ModelFactoryController();
        }
        return instance;
    }

    public void registrarUsuario(String id, String nombre, String apellido, String correo, String direccion, String contrasenia, LocalDate fechaNacimiento) {
        Usuario usuario = new Usuario(id, nombre, apellido, direccion, fechaNacimiento, correo, contrasenia);
        aerolinea.registrarUsuario(usuario); // Delegar el registro a Aerolinea
    }

    public boolean validarInicioSesion(String id, String contrasenia) {
        return aerolinea.validarInicioSesion(id, contrasenia);
    }

}