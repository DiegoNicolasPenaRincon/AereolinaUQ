package co.edu.uniquindio.aerolineauq.controller;

import co.edu.uniquindio.aerolineauq.controller.service.IModelFactoryService;
import co.edu.uniquindio.aerolineauq.exceptions.EmpleadoException;
import co.edu.uniquindio.aerolineauq.mapping.mappers.BancoMapper;
import co.edu.uniquindio.aerolineauq.model.Aerolinea;
import co.edu.uniquindio.aerolineauq.utils.BancoUtils;
//import co.edu.uniquindio.aerolineauq.utils.Persistencia;

import java.io.IOException;
import java.util.List;
/*
public class ModelFactoryController implements IModelFactoryService, Runnable {
    /*
    Aerolinea aerolinea;
    BancoMapper mapper = BancoMapper.INSTANCE;

    Thread hilo1GuardarXml;
    Thread hilo2SalvarLog;

    String mensaje = "";
    int nivel = 0;
    String accion = "";
    BoundedSemaphore semaphore = new BoundedSemaphore(1);


    //------------------------------  Singleton ------------------------------------------------
    // Clase estatica oculta. Tan solo se instanciara el singleton una vez
    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        //1. inicializar datos y luego guardarlo en archivos
        System.out.println("invocación clase singleton");
//        cargarDatosBase();
//        salvarDatosPrueba();

        //2. Cargar los datos de los archivos
//		cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
//		cargarResourceBinario();
//		guardarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
//		guardarResourceXML();
        cargarResourceXML();

        //Siempre se debe verificar si la raiz del recurso es null

        if(aerolinea == null){
            cargarDatosBase();
            guardarResourceXML();
        }
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");
    }

    private void cargarDatosDesdeArchivos() {
        aerolinea = new Aerolinea();
        try {
            Persistencia.cargarDatosArchivos(aerolinea);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void salvarDatosPrueba() {
        try {
            Persistencia.guardarEmpleados(getBanco().getListaEmpleados());
            Persistencia.guardarClientes(getBanco().getListaClientes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarDatosBase() {
        aerolinea = BancoUtils.inicializarDatos();
    }

    public Aerolinea getBanco() {
        return aerolinea;
    }

    public void setBanco(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }


    @Override
    public List<EmpleadoDto> obtenerEmpleados() {
       return  mapper.getEmpleadosDto(aerolinea.getListaEmpleados());
    }

    @Override
    public boolean agregarEmpleado(EmpleadoDto empleadoDto) {
        try{
            if(!aerolinea.verificarEmpleadoExistente(empleadoDto.cedula())) {
                Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
                getBanco().agregarEmpleado(empleado);
                registrarAccionesSistema("Empleado agregado: "+ empleado.getNombre(),1,"agregarEmpleado");
                guardarResourceXML();
            }
            return true;
        }catch (EmpleadoException e){
            e.getMessage();
            registrarAccionesSistema(e.getMessage(),3,"agregarEmpleado");
            return false;
        }
    }

    @Override
    public boolean eliminarEmpleado(String cedula) {
        boolean flagExiste = false;
        try {
            flagExiste = getBanco().eliminarEmpleado(cedula);
        } catch (EmpleadoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flagExiste;
    }

    @Override
    public boolean actualizarEmpleado(String cedulaActual, EmpleadoDto empleadoDto) {
        try {
            Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
            getBanco().actualizarEmpleado(cedulaActual, empleado);
            return true;
        } catch (EmpleadoException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void cargarResourceXML() {
        aerolinea = Persistencia.cargarRecursoBancoXML();
    }

    private void guardarResourceXML() {
        hilo1GuardarXml = new Thread(this);
        hilo1GuardarXml.start();
    }

    private void cargarResourceBinario() {
        aerolinea = Persistencia.cargarRecursoBancoBinario();
    }

    private void guardarResourceBinario() {
        Persistencia.guardarRecursoBancoBinario(aerolinea);
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        this.mensaje = mensaje;
        this.nivel = nivel;
        this.accion = accion;
        hilo2SalvarLog =  new Thread(this);
        hilo2SalvarLog.start();
    }

    @Override
    public void run() {
        Thread hiloActual = Thread.currentThread();
        ocupar();
        if(hiloActual == hilo1GuardarXml){
            Persistencia.guardarRecursoBancoXML(aerolinea);
            liberar();
        }
        if(hiloActual == hilo2SalvarLog){
            Persistencia.guardaRegistroLog(mensaje, nivel, accion);
            liberar();
        }
    }

    private void liberar() {
        try {
            semaphore.liberar();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void ocupar() {
        try {
            semaphore.ocupar();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

     */

