package co.edu.uniquindio.aerolineauq.controller;

public class TransaccionControler {
    ModelFactoryController modelFactoryController;

    public TransaccionControler(){
        System.out.println("Llamando al singleton desde EmpleadoServiceController");
        modelFactoryController = ModelFactoryController.getInstance();
    }
}
