package co.edu.uniquindio.aerolineauq.utils;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.model.Aerolinea;
import co.edu.uniquindio.aerolineauq.model.Usuario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Persistencia {

    public static final String RUTA_ARCHIVO_MODELO_BANCO_XML = "/persistencia/model.xml";
    public static final String RUTA_ARCHIVO_LOG = "/persistencia/log/AeroLog.txt";
    public static final String RUTA_ARCHIVO_MODELO_BINARIO = "/persistencia/model.dat";
    public static final String RUTA_ARCHIVO_USUARIOS = "/persistencia/usuarios.txt";




    //Cargar y guardar recurso binario del model
    public static Aerolinea cargarRecursoBinario() {

        Aerolinea aeroUq = null;

        try {
            aeroUq = (Aerolinea) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_BINARIO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aeroUq;
    }

    public static void guardarRecursoBinario(Aerolinea aeroUq) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_BINARIO, aeroUq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // //Cargar y guardar recurso xml del model
    public static void guardarRecursoBancoXML(Aerolinea aeroUq) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_BANCO_XML, aeroUq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Aerolinea cargarRecursoBancoXML() {

        Aerolinea aeroUq = null;

        try {
            aeroUq = (Aerolinea) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_BANCO_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aeroUq;

    }

    // Guardar registro del log

    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    // Cargar y guardar a los usuarios

    public static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException {
        ArrayList<Usuario> empleados =new ArrayList<Usuario>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);
            Usuario empleado = new Usuario();
            String[] datos = linea.split("@@");
            empleado.setId(linea.split("@@")[0]);
            empleado.setNombre(linea.split("@@")[1]);
            empleado.setApellido(linea.split("@@")[2]);
            empleado.setDireccion(linea.split("@@")[3]);
            empleado.setFechaNacimiento(LocalDate.parse(datos[4]));
            empleado.setCorreo(linea.split("@@")[5]);
            empleado.setContrasenia(linea.split("@@")[6]);
            empleados.add(empleado);
        }
        return empleados;
    }

    public static void guardarUsuarios(ListaSimple<Usuario> listaUsuarios) throws IOException {
        String contenido = "";
        for(Usuario usuario:listaUsuarios)
        {
            contenido+= usuario.getId()+
                    "@@"+usuario.getNombre()+
                    "@@"+usuario.getApellido()+
                    "@@"+usuario.getDireccion()+
                    "@@"+usuario.getFechaNacimiento()+
                    "@@"+usuario.getCorreo()+
                    "@@"+usuario.getContrasenia()+
                     "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
    }


    // Cargar los datos de los archivos guardados en las respectivas rutas

    public static void cargarDatosArchivos(Aerolinea aerolinea) throws FileNotFoundException, IOException {

        ArrayList<Usuario> usuariosCargados = cargarUsuarios();
        if (usuariosCargados.size() > 0)
            aerolinea.getListaUsuarios().addAll(usuariosCargados);


    }
}
