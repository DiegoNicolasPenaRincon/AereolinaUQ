package co.edu.uniquindio.aerolineauq.controller;

import co.edu.uniquindio.aerolineauq.controller.service.IBancoControllerService;

public class AerolineaController implements IBancoControllerService {

    ModelFactoryController modelFactoryController;

    public AerolineaController(){
        System.out.println("Llamando al singleton desde BancoServiceController");

       // modelFactoryController = ModelFactoryController.getInstance();
    }
}
