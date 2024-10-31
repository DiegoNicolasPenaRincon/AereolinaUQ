package co.edu.uniquindio.aerolineauq.utils;

import co.edu.uniquindio.aerolineauq.model.Aerolinea;
import co.edu.uniquindio.aerolineauq.model.Usuario;

import java.time.LocalDate;

public class AerolineaUtils {

    public static Aerolinea inicializarDatos(){
        Aerolinea aerolinea=new Aerolinea();

        Usuario usuario=new Usuario();
        usuario.setId("1234");
        usuario.setNombre("Santiago");
        usuario.setApellido("Betancourt");
        usuario.setContrasenia("1234");
        usuario.setCorreo("santiagobv687@gmail.com");
        usuario.setDireccion("Armenia");
        usuario.setFechaNacimiento(LocalDate.ofEpochDay(01-02-2005));
        aerolinea.getListaUsuarios().agregar(usuario);

        return aerolinea;
    }
}
