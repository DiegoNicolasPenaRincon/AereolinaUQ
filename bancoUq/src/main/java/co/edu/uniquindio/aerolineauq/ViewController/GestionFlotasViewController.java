package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.Avion;
import co.edu.uniquindio.aerolineauq.model.RolTripulante;
import co.edu.uniquindio.aerolineauq.model.Ruta;
import co.edu.uniquindio.aerolineauq.model.Tripulante;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Collection;
import java.util.List;

public class GestionFlotasViewController {


    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnAgregar;

    @FXML
    private ComboBox<Ruta> rutaComboBox;

    @FXML

    private Label avionMostrarlbl;

    @FXML

    private Label avionLbl;

    @FXML

    private TableView<Tripulante> tripulantesTable;

    @FXML

    private TableColumn<Tripulante, String> idTripulanteColumn;

    @FXML
    private TableColumn<Tripulante, String> nombreTripulanteColumn;

    @FXML
    private TableColumn<Tripulante, String> rolTripulanteColumn;

    @FXML
    private TableColumn<Tripulante, String> correoTripulanteColumn;

    public void initialize() {
        Collection<Ruta> coleccionRuta=modelFactoryController.getAerolinea().getRutasAerolinea().toCollection();
        Collection<Tripulante> coleccionTripulantes=modelFactoryController.getAerolinea().getListaTripulantes().toCollection();
        //Collection<Avion> coleccionAviones=modelFactoryController.getAerolinea().getAvionesDisponibles().toCollection();
        rutaComboBox.getItems().addAll(coleccionRuta);
        //ListaSimple<Avion> aviones = modelFactoryController.getAerolinea().filtrarNacionalesInternacionales();
        //avionComboBox.getItems().addAll(modelFactoryController.getAerolinea());

        nombreTripulanteColumn.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getNombre()) );
        idTripulanteColumn.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getId() ));
        rolTripulanteColumn.setCellValueFactory( cellData -> new SimpleStringProperty( String.valueOf(cellData.getValue().getRolTripulante()) ) );
        correoTripulanteColumn.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getCorreo()) );

        this.tripulantesTable.setItems(FXCollections.observableList((List<Tripulante>) coleccionTripulantes));
    }

}
