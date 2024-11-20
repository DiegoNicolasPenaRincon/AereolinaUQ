package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.exceptions.TripulanteAsignadoException;
import co.edu.uniquindio.aerolineauq.model.*;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AdminViewController {

    @FXML
    Button cambiarAvionButton;
    @FXML
    private AnchorPane AnchorNavegacion;

    @FXML
    private AnchorPane anchorAeronaves;

    @FXML
    private AnchorPane anchorTripulantes;

    @FXML
    private ComboBox<Avion> avionComboBox;

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
    @FXML
    private ComboBox<RolTripulante> cbRol;


    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    private AerolineaApplication aplicacion;

    public AdminViewController() throws IOException {
    }

    public void setAplicacion(AerolineaApplication aplicacion) {
        this.aplicacion = aplicacion;
    }

    @FXML
    public void initialize() {
        cbRol.setItems(FXCollections.observableArrayList(RolTripulante.values()));

        columnID.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getId()));
        columnNombre.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombre()));
        columnApellido.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getApellido()));
        columnCorreo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCorreo()));
        columnDireccion.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDireccion()));
        columnEstudios.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEstudios()));
        columnFecha.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFechaNacimiento().toString()));
        columnRol.setCellValueFactory(cellData -> {
            RolTripulante rol = cellData.getValue().getRolTripulante();
            return new SimpleStringProperty(rol != null ? rol.toString() : "Sin asignar");
        });
        // Configurar las columnas de la tabla de tripulantes asignados
        columnIDAsignado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getId()));
        columnNombreAsignado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombre()));
        columnCorreoAsignado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCorreo()));
        columnRolAsignado.setCellValueFactory(cellData -> {
            RolTripulante rol = cellData.getValue().getRolTripulante();
            return new SimpleStringProperty(rol != null ? rol.toString() : "Sin asignar");
        });

        //Llenar ComboBox
        Collection<Ruta> coleccionRuta=modelFactoryController.getAerolinea().getRutasAerolinea().toCollection();
        //Collection<Avion> coleccionAviones=modelFactoryController.getAerolinea().getListaAviones().toCollection();

        this.rutaComboBox.setItems(FXCollections.observableArrayList(coleccionRuta));
        avionComboBox.setDisable(true);
        tableTripulantesAsignados.setDisable(true);



        rutaComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la opción seleccionada
                avionComboBox.getItems().clear();
                Ruta selectedOption = rutaComboBox.getValue();
                avionLbl.setText(selectedOption.getAvionAsignado().getNombre());
                Collection<Avion> coleccionAviones=modelFactoryController.getAerolinea().filtrarAvionesNacionales(modelFactoryController.getAerolinea().getListaAviones(), selectedOption.getAvionAsignado().getTipoAvion());
                avionComboBox.getItems().addAll(coleccionAviones);
                //avionComboBox.setDisable(false);
                tableTripulantesAsignados.setDisable(false);
            }
        });


        tableTripulantesAsignados.setRowFactory(tv -> {
            TableRow<Tripulante> row = new TableRow<>();

            row.itemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.getAvionAsignado().equals(rutaComboBox.getSelectionModel().getSelectedItem().getAvionAsignado()))
                {
                    row.setStyle("-fx-background-color: yellow;");
                }
                else
                {
                    row.setStyle("");
                }
            });
            return row;
        });
        tableTripulantes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarDatosTripulante(newValue);
            }
        });

        // Cargar los datos en la tabla
        cargarDatosTabla();
    }
    private void cargarDatosTripulante(Tripulante tripulante) {
        if (tripulante == null) return;

        txtID.setText(tripulante.getId());
        txtID.setText(tripulante.getId());
        txtNombre.setText(tripulante.getNombre());
        txtApellido.setText(tripulante.getApellido());
        txtCorreo.setText(tripulante.getCorreo());
        txtDireccion.setText(tripulante.getDireccion());
        txtEstudios.setText(tripulante.getEstudios());
        dateNacimiento.setValue(tripulante.getFechaNacimiento());
        cbRol.setValue(tripulante.getRolTripulante());
    }



    @FXML
    void agregarTripulanteEvent(ActionEvent event) {
        String id = txtID.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String direccion = txtDireccion.getText();
        LocalDate fechaNacimiento = dateNacimiento.getValue();
        String estudios = txtEstudios.getText();
        RolTripulante rolTripulante = cbRol.getValue();


        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || direccion.isEmpty() || estudios.isEmpty() || fechaNacimiento == null ) {
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        modelFactoryController.registrarTripulante(id, nombre , apellido, direccion, fechaNacimiento, correo, estudios,rolTripulante);
        registrarAccionesSistema("Registro Tripulante", 1, "Se registro el tripulante "+ nombre);

        mostrarAlerta("Éxito", "Tripulante registrado correctamente", Alert.AlertType.INFORMATION);

        limpiarCampos();
    }
    


    private void limpiarCampos() {
        txtID.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
        txtDireccion.clear();
        txtEstudios.clear();
        dateNacimiento.setValue(null);
        registrarAccionesSistema("Limpiar campos", 1, "Se limpiaron los campos ");
    }
    private void limpiarCampos1() {
        txtID.clear();
        txtID.setDisable(false); // Habilitar nuevamente el campo ID
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
        txtDireccion.clear();
        txtEstudios.clear();
        dateNacimiento.setValue(null);
        cbRol.setValue(null);
    }


    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

    @FXML
    void eliminarTripulanteEvent(ActionEvent event) {
        Tripulante tripulanteSeleccionado = tableTripulantes.getSelectionModel().getSelectedItem();
        if (tripulanteSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un tripulante para eliminar.", Alert.AlertType.ERROR);
            return;
        }

        try {
            String id = tripulanteSeleccionado.getId();
            modelFactoryController.eliminarTripulante(id);
            cargarDatosTabla();

            mostrarAlerta("Éxito", "El tripulante " + tripulanteSeleccionado.getNombre() + " ha sido eliminado correctamente.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void actualizarTripulanteEvent(ActionEvent event) {
        Tripulante tripulanteSeleccionado = tableTripulantes.getSelectionModel().getSelectedItem();
        if (tripulanteSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un tripulante para modificar.", Alert.AlertType.ERROR);
            return;
        }

        try {
            String id = txtID.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String direccion = txtDireccion.getText();
            String correo = txtCorreo.getText();
            LocalDate fechaNacimiento = dateNacimiento.getValue();
            String estudios = txtEstudios.getText();
            RolTripulante rolTripulante = cbRol.getValue();

            if (id.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || correo.isEmpty() || estudios.isEmpty() || fechaNacimiento == null || rolTripulante == null) {
                mostrarAlerta("Error", "Todos los campos son obligatorios para modificar un tripulante.", Alert.AlertType.ERROR);
                return;
            }

            Tripulante tripulanteActualizado = new Tripulante(id, nombre,apellido, direccion, fechaNacimiento, correo, estudios, rolTripulante);

            modelFactoryController.actualizarTripulante(tripulanteActualizado);
            cargarDatosTabla();

            mostrarAlerta("Éxito", "El tripulante " + tripulanteSeleccionado.getNombre() + " ha sido actualizado correctamente.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
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

    private void cargarDatosTabla() {
        ListaSimple<Tripulante> listaTripulantes = modelFactoryController.getListaTripulantes();

        tableTripulantes.getItems().clear();
        for (Tripulante tripulante : listaTripulantes) {
            tableTripulantes.getItems().add(tripulante);
        }

        tableTripulantesAsignados.getItems().clear();
        for (Tripulante tripulante : listaTripulantes) {
            tableTripulantesAsignados.getItems().add(tripulante);
        }
        tableTripulantes.setItems(FXCollections.observableArrayList(
                modelFactoryController.getAerolinea().getListaTripulantes().toCollection()
        ));
        tableTripulantes.refresh();
    }


    public void agregarTripulanteOnAction(ActionEvent actionEvent) throws TripulanteAsignadoException {
        if(tableTripulantesAsignados.getSelectionModel().getSelectedItem()!=null)
        {
            try
            {
                if(tableTripulantesAsignados.getSelectionModel().getSelectedItem().getAvionAsignado().equals(null))
                {
                    throw new NullPointerException();
                }
                else
                {
                    throw new TripulanteAsignadoException();
                }
            }
            catch (NullPointerException e)
            {
                if(modelFactoryController.getAerolinea().verificarAsignacion(rutaComboBox.getSelectionModel().getSelectedItem().getAvionAsignado().getNombre(),rutaComboBox.getSelectionModel().getSelectedItem().getAvionAsignado().getListaTripulantes()))
                {
                    tableTripulantesAsignados.getSelectionModel().getSelectedItem().setAvionAsignado(rutaComboBox.getSelectionModel().getSelectedItem().getAvionAsignado());
                    //avionComboBox.getSelectionModel().getSelectedItem().getListaTripulantes().agregar(tableTripulantesAsignados.getSelectionModel().getSelectedItem());
                    rutaComboBox.getSelectionModel().getSelectedItem().getAvionAsignado().getListaTripulantes().agregar(tableTripulantesAsignados.getSelectionModel().getSelectedItem());
                    mostrarAlerta("Informacion","Tripulante agregado correctamente", Alert.AlertType.INFORMATION);
                }
                else
                {
                    mostrarAlerta("Informacion","el avion supero el limite de tripulantes permitidos", Alert.AlertType.ERROR);
                }
            }
            catch (TripulanteAsignadoException e)
            {
                mostrarAlerta("Error",e.getMessage(),Alert.AlertType.ERROR);
            }
        }
        else
        {
            mostrarAlerta("Error","Debe seleccionar un tripulante",Alert.AlertType.ERROR);
        }
    }

    public void cambiarAvionOnAction(ActionEvent actionEvent) {

    }

    public void eliminarOnAction(ActionEvent actionEvent) {
        if(tableTripulantesAsignados.getSelectionModel().getSelectedItem()!=null)
        {
            if(tableTripulantesAsignados.getSelectionModel().getSelectedItem().getAvionAsignado().equals(rutaComboBox.getSelectionModel().getSelectedItem().getAvionAsignado()))
            {
                tableTripulantesAsignados.getSelectionModel().getSelectedItem().setAvionAsignado(null);
                mostrarAlerta("Informacion","El tripulante ya no se encuentra asignado a ese avion",Alert.AlertType.INFORMATION);
            }
            else
            {
                mostrarAlerta("Error","El tripulante no pertenece al avion seleccionado",Alert.AlertType.ERROR);
            }
        }
        else
        {
            mostrarAlerta("Error","Debe seleccionar un tripulante",Alert.AlertType.ERROR);
        }
    }
}
