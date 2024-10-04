package co.edu.uniquindio.aerolineauq.viewController;

import co.edu.uniquindio.aerolineauq.controller.service.IBancoControllerService;
import co.edu.uniquindio.aerolineauq.controller.BancoController;
import javafx.fxml.FXML;

public class BancoViewController {
    IBancoControllerService bancoControllerService;

    @FXML
    void initialize() {
        bancoControllerService = new BancoController();
    }
}
