package co.edu.uniquindio.aerolineauq.utils;

import co.edu.uniquindio.aerolineauq.model.Aerolinea;
import co.edu.uniquindio.aerolineauq.model.Tripulante;
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

        Usuario usuario1=new Usuario();
        usuario1.setId("111");
        usuario1.setNombre("Camila");
        usuario1.setApellido("Ramirez");
        usuario1.setContrasenia("1234");
        usuario1.setCorreo("camila@gmail.com");
        usuario1.setDireccion("Armenia");
        usuario1.setFechaNacimiento(LocalDate.ofEpochDay(25-07-2003));
        aerolinea.getListaUsuarios().agregar(usuario1);

       // Tripulacion

        //Pilotos
        Tripulante piloto1 = new Tripulante();
        piloto1.setId("001");
        piloto1.setNombre("Juan");
        piloto1.setDireccion("Calle 123");
        piloto1.setFechaNacimiento( LocalDate.of(1980, 5, 21));
        piloto1.setCorreo("juan.perez@correo.com");
        piloto1.setEstudios("Aviación");
        piloto1.setRolTripulante("Piloto");
        aerolinea.getListaTripulantes().agregar(piloto1);


        Tripulante piloto2 = new Tripulante();
        piloto2.setId("002");
        piloto2.setNombre("Ana");
        piloto2.setDireccion("Calle 13");
        piloto2.setFechaNacimiento( LocalDate.of(1983, 3, 23));
        piloto2.setCorreo("ana1@correo.com");
        piloto2.setEstudios("Aviación");
        piloto2.setRolTripulante("Piloto");
        aerolinea.getListaTripulantes().agregar(piloto2);

        //Copilotos
        Tripulante copiloto1 = new Tripulante();
        copiloto1.setId("003");
        copiloto1.setNombre("Camilo");
        copiloto1.setDireccion("Calle 56");
        copiloto1.setFechaNacimiento( LocalDate.of(1985, 8, 14));
        copiloto1.setCorreo("camila@correo.com");
        copiloto1.setEstudios("Aviación");
        copiloto1.setRolTripulante("Copiloto");
        aerolinea.getListaTripulantes().agregar(copiloto1);

        Tripulante copiloto2 = new Tripulante();
        copiloto2.setId("004");
        copiloto2.setNombre("Camila");
        copiloto2.setDireccion("Calle 72");
        copiloto2.setFechaNacimiento( LocalDate.of(1986, 8, 14));
        copiloto2.setCorreo("camil1a@correo.com");
        copiloto2.setEstudios("Aviación");
        copiloto2.setRolTripulante("Copiloto");
        aerolinea.getListaTripulantes().agregar(copiloto2);

        // Auxiliares
        Tripulante aux1 = new Tripulante();
        aux1.setId("005");
        aux1.setNombre("Luis");
        aux1.setDireccion("Calle 12");
        aux1.setFechaNacimiento( LocalDate.of(1985, 8, 14));
        aux1.setCorreo("luis@correo.com");
        aux1.setEstudios("Tripulante de Cabina");
        aux1.setRolTripulante("Auxiliar de vuelo");
        aerolinea.getListaTripulantes().agregar(aux1);

        Tripulante aux2 = new Tripulante();
        aux2.setId("006");
        aux2.setNombre("Maria");
        aux2.setDireccion("Calle 56");
        aux2.setFechaNacimiento( LocalDate.of(1985, 8, 14));
        aux2.setCorreo("maria@correo.com");
        aux2.setEstudios("Tripulante de Cabina");
        aux2.setRolTripulante("Auxiliar de vuelo");
        aerolinea.getListaTripulantes().agregar(aux2);

        Tripulante aux3 = new Tripulante();
        aux3.setId("007");
        aux3.setNombre("Ana");
        aux3.setDireccion("Calle 78");
        aux3.setFechaNacimiento( LocalDate.of(1985, 8, 14));
        aux3.setCorreo("ana@correo.com");
        aux3.setEstudios("Tripulante de Cabina");
        aux3.setRolTripulante("Copiloto");
        aerolinea.getListaTripulantes().agregar(aux3);

        return aerolinea;
    }
}
