package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.Ruta;
import co.edu.uniquindio.aerolineauq.model.Tripulante;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AdminViewController {

    @FXML
    private AnchorPane AnchorNavegacion;

    @FXML
    private AnchorPane anchorAeronaves;

    @FXML
    private AnchorPane anchorTripulantes;

    @FXML
    private ComboBox<?> avionComboBox;

    @FXML
    private Label avionLbl;

    @FXML
    private Label avionMostrarlbl;

    @FXML
    private Label avionlbl;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnAgregarAsignacion;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminarAsignacion;

    @FXML
    private Button btnGestionAeronaves;

    @FXML
    private Button btnGestionEmbarque;

    @FXML
    private Button btnGestionTripulantes;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<Tripulante, String> columnApellido;

    @FXML
    private TableColumn<Tripulante, String> columnCorreo;

    @FXML
    private TableColumn<Tripulante, String> columnCorreoAsignado;

    @FXML
    private TableColumn<Tripulante, String> columnDireccion;

    @FXML
    private TableColumn<Tripulante, String> columnEstudios;

    @FXML
    private TableColumn<Tripulante, String> columnFecha;

    @FXML
    private TableColumn<Tripulante, String> columnID;

    @FXML
    private TableColumn<Tripulante, String> columnIDAsignado;

    @FXML
    private TableColumn<Tripulante, String> columnNombre;

    @FXML
    private TableColumn<Tripulante, String> columnNombreAsignado;

    @FXML
    private TableColumn<Tripulante, String> columnRol;

    @FXML
    private TableColumn<Tripulante, String> columnRolAsignado;

    @FXML
    private DatePicker dateNacimiento;

    @FXML
    private ComboBox<Ruta> rutaComboBox;

    @FXML
    private TableView<Tripulante> tableTripulantes;

    @FXML
    private TableView<Tripulante> tableTripulantesAsignados;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEstudios;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtNombre;

    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    private AerolineaApplication aplicacion;

    public AdminViewController() throws IOException {
    }

    public void setAplicacion(AerolineaApplication aplicacion) {
        this.aplicacion = aplicacion;
    }

    public void initialize() {
        Collection<Ruta> coleccionRuta=modelFactoryController.getAerolinea().getRutasAerolinea().toCollection();
        Collection<Tripulante> coleccionTripulantes=modelFactoryController.getAerolinea().getListaTripulantes().toCollection();
        //Collection<Avion> coleccionAviones=modelFactoryController.getAerolinea().getAvionesDisponibles().toCollection();
        rutaComboBox.getItems().addAll(coleccionRuta);
        //ListaSimple<Avion> aviones = modelFactoryController.getAerolinea().filtrarNacionalesInternacionales();
        //avionComboBox.getItems().addAll(modelFactoryController.getAerolinea());

        columnNombreAsignado.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getNombre()) );
        columnIDAsignado.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getId() ));
        columnRolAsignado.setCellValueFactory( cellData -> new SimpleStringProperty( String.valueOf(cellData.getValue().getRolTripulante()) ) );
        columnCorreoAsignado.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getCorreo()) );

        tableTripulantesAsignados.setItems(FXCollections.observableList((List<Tripulante>) coleccionTripulantes));
        anchorAeronaves.setVisible(true);
        anchorTripulantes.setVisible(false);
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        try{
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(("Confirmación"));
            alert.setHeaderText(null);
            alert.setHeaderText("¿Esta seguro de que desea cerrar sesión?");
            Optional<ButtonType> option=alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){
                aplicacion.mostrarVentanaPrincipal();
            }
        }catch(Exception e){
            System.out.println("Error al abrir la ventana."+e);
        }
    }

    @FXML
    public void mostrarAeronaves() {
        anchorAeronaves.setVisible(true);
        anchorTripulantes.setVisible(false);
    }

    @FXML
    public void mostrarTripulantes() {
        anchorAeronaves.setVisible(false);
        anchorTripulantes.setVisible(true);
    }

}
