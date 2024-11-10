package co.edu.uniquindio.aerolineauq.utils;

import co.edu.uniquindio.aerolineauq.model.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class AerolineaUtils {

    public static Aerolinea inicializarDatos(){
        Aerolinea aerolinea=new Aerolinea();

        //Aviones
        Avion avion1=new Avion();
        Avion avion2=new Avion();
        Avion avion3=new Avion();
        Avion avion4=new Avion();
        Avion avion5=new Avion();
        Avion avion6=new Avion();

        avion1.setNombre("Airbus A320");
        avion1.setTipoAvion(TipoAvion.NACIONAL);
        avion1.setCantidadPasajerosEconomica(138);
        avion1.setCantidadPasajerosEjecutiva(12);
        avion1.setCantidadPasajerosActualEconomica(0);
        avion1.setCantidadPasajerosActualEjecutiva(0);
        avion1.setCapacidadCarga(190000);
        avion1.setCargaActual(0);
        aerolinea.getListaAviones().agregar(avion1);

       avion2.setNombre("Airbus A320");
        avion2.setTipoAvion(TipoAvion.NACIONAL);
        avion2.setCantidadPasajerosEconomica(138);
        avion2.setCantidadPasajerosEjecutiva(12);
        avion2.setCantidadPasajerosActualEconomica(0);
        avion2.setCantidadPasajerosActualEjecutiva(0);
        avion2.setCapacidadCarga(190000);
        avion2.setCargaActual(0);
        aerolinea.getListaAviones().agregar(avion2);

        avion3.setNombre("Airbus A330");
        avion3.setTipoAvion(TipoAvion.INTERNACIONAL);
        avion3.setCantidadPasajerosEconomica(222);
        avion3.setCantidadPasajerosEjecutiva(30);
        avion3.setCantidadPasajerosActualEconomica(0);
        avion3.setCantidadPasajerosActualEjecutiva(0);
        avion3.setCapacidadCarga(52000);
        avion3.setCargaActual(0);
        aerolinea.getListaAviones().agregar(avion3);

        avion4.setNombre("Airbus A330");
        avion4.setTipoAvion(TipoAvion.INTERNACIONAL);
        avion4.setCantidadPasajerosEconomica(222);
        avion4.setCantidadPasajerosEjecutiva(30);
        avion4.setCantidadPasajerosActualEconomica(0);
        avion4.setCantidadPasajerosActualEjecutiva(0);
        avion4.setCapacidadCarga(52000);
        avion4.setCargaActual(0);
        aerolinea.getListaAviones().agregar(avion4);

        avion5.setNombre("Boeing 787");
        avion5.setTipoAvion(TipoAvion.INTERNACIONAL);
        avion5.setCantidadPasajerosEconomica(222);
        avion5.setCantidadPasajerosEjecutiva(28);
        avion5.setCantidadPasajerosActualEconomica(0);
        avion5.setCantidadPasajerosActualEjecutiva(0);
        avion5.setCapacidadCarga(50000);
        avion5.setCargaActual(0);
        aerolinea.getListaAviones().agregar(avion5);

        avion6.setNombre("Boeing 787");
        avion6.setTipoAvion(TipoAvion.INTERNACIONAL);
        avion6.setCantidadPasajerosEconomica(222);
        avion6.setCantidadPasajerosEjecutiva(28);
        avion6.setCantidadPasajerosActualEconomica(0);
        avion6.setCantidadPasajerosActualEjecutiva(0);
        avion6.setCapacidadCarga(50000);
        avion6.setCargaActual(0);
        aerolinea.getListaAviones().agregar(avion6);




        //Usuarios
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
        piloto1.setRolTripulante(RolTripulante.PILOTO);
        aerolinea.getListaTripulantes().agregar(piloto1);


        Tripulante piloto2 = new Tripulante();
        piloto2.setId("002");
        piloto2.setNombre("Ana");
        piloto2.setDireccion("Calle 13");
        piloto2.setFechaNacimiento( LocalDate.of(1983, 3, 23));
        piloto2.setCorreo("ana1@correo.com");
        piloto2.setEstudios("Aviación");
        piloto2.setRolTripulante(RolTripulante.PILOTO);
        aerolinea.getListaTripulantes().agregar(piloto2);

        //Copilotos
        Tripulante copiloto1 = new Tripulante();
        copiloto1.setId("003");
        copiloto1.setNombre("Camilo");
        copiloto1.setDireccion("Calle 56");
        copiloto1.setFechaNacimiento( LocalDate.of(1985, 8, 14));
        copiloto1.setCorreo("camila@correo.com");
        copiloto1.setEstudios("Aviación");
        copiloto1.setRolTripulante(RolTripulante.COPILOTO);
        aerolinea.getListaTripulantes().agregar(copiloto1);

        Tripulante copiloto2 = new Tripulante();
        copiloto2.setId("004");
        copiloto2.setNombre("Camila");
        copiloto2.setDireccion("Calle 72");
        copiloto2.setFechaNacimiento( LocalDate.of(1986, 8, 14));
        copiloto2.setCorreo("camil1a@correo.com");
        copiloto2.setEstudios("Aviación");
        copiloto2.setRolTripulante(RolTripulante.COPILOTO);
        aerolinea.getListaTripulantes().agregar(copiloto2);

        // Auxiliares
        Tripulante aux1 = new Tripulante();
        aux1.setId("005");
        aux1.setNombre("Luis");
        aux1.setDireccion("Calle 12");
        aux1.setFechaNacimiento( LocalDate.of(1985, 8, 14));
        aux1.setCorreo("luis@correo.com");
        aux1.setEstudios("Tripulante de Cabina");
        aux1.setRolTripulante(RolTripulante.AUXILIAR);
        aerolinea.getListaTripulantes().agregar(aux1);

        Tripulante aux2 = new Tripulante();
        aux2.setId("006");
        aux2.setNombre("Maria");
        aux2.setDireccion("Calle 56");
        aux2.setFechaNacimiento( LocalDate.of(1985, 8, 14));
        aux2.setCorreo("maria@correo.com");
        aux2.setEstudios("Tripulante de Cabina");
        aux2.setRolTripulante(RolTripulante.AUXILIAR);
        aerolinea.getListaTripulantes().agregar(aux2);

        Tripulante aux3 = new Tripulante();
        aux3.setId("007");
        aux3.setNombre("Ana");
        aux3.setDireccion("Calle 78");
        aux3.setFechaNacimiento( LocalDate.of(1985, 8, 14));
        aux3.setCorreo("ana@correo.com");
        aux3.setEstudios("Tripulante de Cabina");
        aux3.setRolTripulante(RolTripulante.COPILOTO);
        aerolinea.getListaTripulantes().agregar(aux3);

     Ruta ruta1 = new Ruta(Destino.Monterrey, LocalTime.of(3, 30), LocalTime.of(10, 0), LocalTime.of(15, 0));
     aerolinea.getRutasAerolinea().agregar(ruta1);

     Silla silla1 = new Silla("12", "A", true);

     Tiquete tiquete1 = new Tiquete("123",usuario1, ruta1, 350.0, ClaseVuelo.ECONOMICA, silla1, TipoViaje.ida, null);
     aerolinea.getListaTiquetes().agregar(tiquete1);



        /*Rutas

        Ruta ruta1=new Ruta();
        Ruta ruta2=new Ruta();
        Ruta ruta3=new Ruta();
        Ruta ruta4=new Ruta();
        Ruta ruta5=new Ruta();
        Ruta ruta6=new Ruta();

        ruta1.setDestino(Destino.Monterrey);
        ruta1.setDuracion(LocalTime.of(2,45));
        ruta1.setHoraSalida(LocalTime.of(6,0));
        ruta1.setHoraRegreso(ruta1.getHoraSalida());

         */


        return aerolinea;
    }
}
