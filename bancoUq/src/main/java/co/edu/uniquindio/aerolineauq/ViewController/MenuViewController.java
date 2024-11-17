package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.*;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

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
    private TableColumn<Ruta, Destino> columnDestino;

    @FXML
    private TableColumn<Ruta, String> columnHora;

    @FXML
    private TableColumn<Ruta, String> columnOrigen;

    @FXML
    private TableColumn<Ruta, String> columnPrecio;

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
    private TableView<Ruta> tableInformacion;

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
        configureDatePicker(dateSalidaViaje);
        configureDatePicker(dateRegresoViaje);
        radioIda.setSelected(true);
        comboClase.getSelectionModel().selectFirst();
        SpinPersonas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
        AnchorCompras.setVisible(true);
        anchorPerfil.setVisible(false);
        anchorHistorial.setVisible(false);
        inicializarTabla();
        inicializarPerfil();
    }

    private void inicializarTabla(){
        columnOrigen.setCellValueFactory(new PropertyValueFactory<>("origen")); // Nombre del atributo en la clase Ruta
        columnDestino.setCellValueFactory(new PropertyValueFactory<>("destino")); // Nombre del atributo en la clase Ruta
        columnHora.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getHoraSalida().toString())); // Transformar LocalTime a String
        columnPrecio.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.format("$%.2f", cellData.getValue().getPrecio()))); // Formato de precio

        ObservableList<Ruta> rutas = FXCollections.observableArrayList();
        for (Object ruta : modelFactoryController.getListaRutas()) {
            rutas.add((Ruta) ruta);
        }

// Ahora puedes usar 'observableRutas' para tu TableView
        tableInformacion.setItems(rutas);

    }

    private void inicializarPerfil(){
        txtNombre.setText(modelFactoryController.getUsuarioActual().getNombre());
        txtApellido.setText(modelFactoryController.getUsuarioActual().getApellido());
        txtCorreo.setText(modelFactoryController.getUsuarioActual().getCorreo());
        txtDireccion.setText(modelFactoryController.getUsuarioActual().getDireccion());
        txtIDNueva.setText(modelFactoryController.getUsuarioActual().getId());
        txtContraseniaNueva.setText(modelFactoryController.getUsuarioActual().getContrasenia());
        dateNacimiento.setValue(modelFactoryController.getUsuarioActual().getFechaNacimiento());
    }

    private void actualizarVisibilidadDateRegreso() {
        dateRegresoViaje.setVisible(radioIdaVuelta.isSelected());
        labelRegreso.setVisible(radioIdaVuelta.isSelected());
    }
    @FXML
    public void realizarCompra() {
        if (validarCampos()) {
            // Recopila información de la interfaz
            Destino destino = Destino.valueOf(comboDestinos.getValue().replace(" ", "_"));
            ClaseVuelo claseVuelo = ClaseVuelo.valueOf(comboClase.getValue());
            TipoViaje tipoViaje = radioIdaVuelta.isSelected() ? TipoViaje.idaYvuelta: TipoViaje.ida;
            int cantidadPersonas = SpinPersonas.getValue();
            LocalDate fechaSalida = dateSalidaViaje.getValue();
            LocalDate fechaRegreso = tipoViaje == TipoViaje.idaYvuelta ? dateRegresoViaje.getValue() : null;
            System.out.println(modelFactoryController.getUsuarioActual().getId());

            Ruta rutaSeleccionada = modelFactoryController.buscarRutaPorDestino(destino);
            if (rutaSeleccionada == null) {
                mostrarError("Error al registrar el tiquete", "No se encontró una ruta para el destino seleccionado.");
                return;
            }

            Usuario usuarioActual = modelFactoryController.getUsuarioActual();
            if (usuarioActual == null) {
                mostrarError("Error", "Usuario no autenticado.");
                return;
            }

            try {
                // Llamada al metodo de ModelFactoryController para registrar el tiquete
                modelFactoryController.registrarCompra("V123", usuarioActual, rutaSeleccionada, (rutaSeleccionada.getPrecio()*cantidadPersonas), claseVuelo, new Silla(), tipoViaje, fechaSalida, fechaRegreso, new Equipaje());
                mostrarConfirmacion("Tiquete registrado exitosamente", usuarioActual.getNombre()+", ID:"+usuarioActual.getId());
                aplicacion.mostrarVentanaEquipaje();
            } catch (Exception e) {
                mostrarError("Error al registrar el tiquete", e.getMessage());
                System.out.println(e.getMessage());
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
    private void configureDatePicker(DatePicker datePicker) {
        datePicker.setDayCellFactory(getDateCellFactory());
    }

    private Callback<DatePicker, DateCell> getDateCellFactory() {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #d3d3d3;");
                }
            }
        };
    }

    @FXML
    private void actualizarPerfil(){
        try {
            if (validarCamposPerfil()) {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String correo = txtCorreo.getText();
                String direccion = txtDireccion.getText();
                String id = txtIDNueva.getText();
                String contrasenia = txtContraseniaNueva.getText();
                LocalDate fechaNacimiento = dateNacimiento.getValue();

                // Crear el usuario actualizado
                Usuario usuarioActualizado = new Usuario(id, nombre, apellido, direccion, fechaNacimiento, correo, contrasenia);
                modelFactoryController.actualizarUsuario(usuarioActualizado);
                // Mostrar mensaje de confirmación
                mostrarConfirmacion("Perfil actualizado", "Los datos del perfil han sido actualizados exitosamente.");
            } else {
                mostrarError("Datos incompletos", "Por favor completa todos los campos antes de actualizar el perfil.");
            }
        } catch (Exception e) {
            mostrarError("Error al actualizar", "Ocurrió un error al intentar actualizar el perfil: " + e.getMessage());
        }

    }

    private boolean validarCamposPerfil() {
        return !(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() ||
                txtCorreo.getText().isEmpty() || txtDireccion.getText().isEmpty() ||
                txtIDNueva.getText().isEmpty() || txtContraseniaNueva.getText().isEmpty() ||
                dateNacimiento.getValue() == null);
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