package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.Avion;
import co.edu.uniquindio.aerolineauq.model.Ruta;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.Collection;

public class GestionFlotasViewController {


    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnAgregar;

    @FXML
    private ComboBox<Avion> avionComboBox;

    @FXML
    private ComboBox<Ruta> rutaComboBox;

    @FXML

    public void initialize() {
        Collection<Ruta> coleccionRuta=modelFactoryController.getAerolinea().getRutasAerolinea().toCollection();
        //Collection<Avion> coleccionAviones=modelFactoryController.getAerolinea().getAvionesDisponibles().toCollection();
        rutaComboBox.getItems().addAll(coleccionRuta);
        //ListaSimple<Avion> aviones = modelFactoryController.getAerolinea().filtrarNacionalesInternacionales();
        //avionComboBox.getItems().addAll(modelFactoryController.getAerolinea());
        avionComboBox.setDisable(false);


    }

}
