package co.edu.uniquindio.aerolineauq.utils;

import co.edu.uniquindio.aerolineauq.model.Aerolinea;
import co.edu.uniquindio.aerolineauq.model.Avion;
import co.edu.uniquindio.aerolineauq.model.TipoAvion;
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

        Avion avion1=new Avion();
        Avion avion2=new Avion();
        Avion avion3=new Avion();
        Avion avion4=new Avion();
        Avion avion5=new Avion();
        Avion avion6=new Avion();

        avion1.setNombre("airbus A320");
        avion1.setCantidadPasajerosEconomica(138);
        avion1.setCantidadPasajerosEjecutiva(12);
        avion1.setCantidadPasajerosActualEconomica(0);
        avion1.setCantidadPasajerosActualEjecutiva(0);
        avion1.setCapacidadCarga(190000);
        avion1.setCargaActual(0);
        avion1.setTipoAvion(TipoAvion.NACIONAL);

        avion2.setNombre("airbus A320");
        avion2.setCantidadPasajerosEconomica(138);
        avion2.setCantidadPasajerosEjecutiva(12);
        avion2.setCantidadPasajerosActualEconomica(0);
        avion2.setCantidadPasajerosActualEjecutiva(0);
        avion2.setCapacidadCarga(190000);
        avion2.setCargaActual(0);
        avion2.setTipoAvion(TipoAvion.NACIONAL);

        avion3.setNombre("airbus A330");
        avion3.setCantidadPasajerosEconomica(222);
        avion3.setCantidadPasajerosEjecutiva(30);
        avion3.setCantidadPasajerosActualEconomica(0);
        avion3.setCantidadPasajerosActualEjecutiva(0);
        avion3.setCapacidadCarga(520000);
        avion3.setCargaActual(0);
        avion3.setTipoAvion(TipoAvion.INTERNACIONAL);

        avion4.setNombre("airbus A330");
        avion4.setCantidadPasajerosEconomica(222);
        avion4.setCantidadPasajerosEjecutiva(30);
        avion4.setCantidadPasajerosActualEconomica(0);
        avion4.setCantidadPasajerosActualEjecutiva(0);
        avion4.setCapacidadCarga(520000);
        avion4.setCargaActual(0);
        avion4.setTipoAvion(TipoAvion.INTERNACIONAL);

        avion5.setNombre("Boeing 787");
        avion5.setCantidadPasajerosEconomica(222);
        avion5.setCantidadPasajerosEjecutiva(28);
        avion5.setCantidadPasajerosActualEconomica(0);
        avion5.setCantidadPasajerosActualEjecutiva(0);
        avion5.setCapacidadCarga(500000);
        avion5.setCargaActual(0);
        avion5.setTipoAvion(TipoAvion.INTERNACIONAL);

        avion6.setNombre("Boeing 787");
        avion6.setCantidadPasajerosEconomica(222);
        avion6.setCantidadPasajerosEjecutiva(28);
        avion6.setCantidadPasajerosActualEconomica(0);
        avion6.setCantidadPasajerosActualEjecutiva(0);
        avion6.setCapacidadCarga(500000);
        avion6.setCargaActual(0);
        avion6.setTipoAvion(TipoAvion.INTERNACIONAL);

        aerolinea.getAvionesDisponibles().agregar(avion1);
        aerolinea.getAvionesDisponibles().agregar(avion2);
        aerolinea.getAvionesDisponibles().agregar(avion3);
        aerolinea.getAvionesDisponibles().agregar(avion4);
        aerolinea.getAvionesDisponibles().agregar(avion5);
        aerolinea.getAvionesDisponibles().agregar(avion6);

        return aerolinea;
    }
}
