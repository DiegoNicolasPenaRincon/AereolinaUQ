package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.*;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class MenuViewController {

    @FXML
    private AnchorPane AnchorCompras;

    @FXML
    private AnchorPane AnchorNavegacion;

    @FXML
    private TableColumn<?, ?> HistorialAsientos;

    @FXML
    private TableColumn<?, ?> HistorialPersonas;

    @FXML
    private TableColumn<?, ?> HistorialPrecio;

    @FXML
    private TableColumn<?, ?> HistorialRegreso;

    @FXML
    private TableColumn<?, ?> HistorialSalida;

    @FXML
    private Spinner<Integer> SpinPersonas;

    @FXML
    private AnchorPane anchorHistorial;

    @FXML
    private AnchorPane anchorPerfil;

    @FXML
    private Button btnActualizarPerfil;

    @FXML
    private Button btnAsientos;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnCompra;

    @FXML
    private Button btnComprarTiquetes;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnVerHistorial;

    @FXML
    private TableColumn<?, ?> columnDestino;

    @FXML
    private TableColumn<?, ?> columnHora;

    @FXML
    private TableColumn<?, ?> columnOrigen;

    @FXML
    private TableColumn<?, ?> columnPrecio;

    @FXML
    private ComboBox<?> comboCiudadOrigen;

    @FXML
    private ComboBox<String> comboClase;

    @FXML
    private ComboBox<String> comboDestinos;

    @FXML
    private DatePicker dateNacimiento;

    @FXML
    private DatePicker dateRegresoViaje;

    @FXML
    private DatePicker dateSalidaViaje;

    @FXML
    private TableColumn<?, ?> historialClase;

    @FXML
    private TableColumn<?, ?> historialDestino;

    @FXML
    private TableColumn<?, ?> historialOrigen;

    @FXML
    private Label labelRegreso;

    @FXML
    private RadioButton radioIda;

    @FXML
    private RadioButton radioIdaVuelta;

    @FXML
    private TableView<?> tableHistorial;

    @FXML
    private TableView<?> tableInformacion;

    @FXML
    private TextField txtApellido;

    @FXML
    private PasswordField txtContraseniaNueva;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtIDNueva;

    @FXML
    private TextField txtNombre;

    private ToggleGroup toggleGroupViaje;

    private AerolineaApplication aplicacion;

    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    public MenuViewController() throws IOException {
    }

    public void setAplicacion(AerolineaApplication aplicacion) {
        this.aplicacion = aplicacion;
    }

    @FXML
    public void cerrarSesion(){
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
    public void initialize() {
        toggleGroupViaje = new ToggleGroup();
        radioIda.setToggleGroup(toggleGroupViaje);
        radioIdaVuelta.setToggleGroup(toggleGroupViaje);
        toggleGroupViaje.selectedToggleProperty().addListener((observable, oldValue, newValue) -> actualizarVisibilidadDateRegreso());
        actualizarVisibilidadDateRegreso();
        comboDestinos.getItems().addAll("Monterrey", "Cancún", "Buenos Aires", "Los Angeles", "Bogotá", "Panamá");
        comboDestinos.getSelectionModel().selectFirst();
        for (ClaseVuelo clase : ClaseVuelo.values()) {
            comboClase.getItems().add(clase.toString());
        }
        radioIda.setSelected(true);
        comboClase.getSelectionModel().selectFirst();
        SpinPersonas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
        AnchorCompras.setVisible(true);
        anchorPerfil.setVisible(false);
        anchorHistorial.setVisible(false);
    }

    private void actualizarVisibilidadDateRegreso() {
        dateRegresoViaje.setVisible(radioIdaVuelta.isSelected());
        labelRegreso.setVisible(radioIdaVuelta.isSelected());
    }
    @FXML
    public void realizarCompra() {
        if (validarCampos()) {
            // Recopila información de la interfaz
            Destino destino = Destino.valueOf(comboDestinos.getValue());
            ClaseVuelo claseVuelo = ClaseVuelo.valueOf(comboClase.getValue());
            TipoViaje tipoViaje = radioIdaVuelta.isSelected() ? TipoViaje.idaYvuelta: TipoViaje.ida;
            int cantidadPersonas = SpinPersonas.getValue();
            LocalDate fechaSalida = dateSalidaViaje.getValue();
            LocalDate fechaRegreso = tipoViaje == TipoViaje.idaYvuelta ? dateRegresoViaje.getValue() : null;

            try {
                // Llamada al método de ModelFactoryController para registrar el tiquete
                Tiquete tiquete = modelFactoryController.registrarCompra("V123", modelFactoryController.getUsuarioActual(), new Ruta(),0, claseVuelo, new Silla(), tipoViaje, fechaSalida, fechaRegreso, new Equipaje());
                mostrarConfirmacion("Tiquete registrado exitosamente", "Número de vuelo: " + tiquete.getNumeroVuelo());
            } catch (Exception e) {
                mostrarError("Error al registrar el tiquete", e.getMessage());
            }
        } else {
            mostrarError("Datos incompletos", "Por favor completa todos los campos necesarios.");
        }
    }

    private boolean validarCampos() {
        return comboDestinos.getValue() != null &&
                comboClase.getValue() != null &&
                dateSalidaViaje.getValue() != null &&
                SpinPersonas.getValue() > 0 &&
                (!radioIdaVuelta.isSelected() || dateRegresoViaje.getValue() != null);
    }

    private void mostrarConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

    @FXML
    public void mostrarCompras() {
        AnchorCompras.setVisible(true);
        anchorPerfil.setVisible(false);
        anchorHistorial.setVisible(false);
    }

    @FXML
    public void mostrarPerfil() {
        AnchorCompras.setVisible(false);
        anchorPerfil.setVisible(true);
        anchorHistorial.setVisible(false);
    }

    @FXML
    public void mostrarHistorial() {
        AnchorCompras.setVisible(false);
        anchorPerfil.setVisible(false);
        anchorHistorial.setVisible(true);
    }

}
