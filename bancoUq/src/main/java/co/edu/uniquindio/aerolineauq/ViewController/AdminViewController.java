package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.exceptions.ExcesoDeTripulantesException;
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

        this.rutaComboBox.setItems(FXCollections.observableArrayList(coleccionRuta));
        tableTripulantesAsignados.setDisable(true);



        rutaComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la opción seleccionada
                Ruta selectedOption = rutaComboBox.getValue();
                avionLbl.setText(selectedOption.getAvionAsignado().getNombre());
                Collection<Avion> coleccionAviones=modelFactoryController.getAerolinea().filtrarAvionesNacionales(modelFactoryController.getAerolinea().getListaAviones(), selectedOption.getAvionAsignado().getTipoAvion());
                tableTripulantesAsignados.setDisable(false);
            }
        });

        tableTripulantes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarDatosTripulante(newValue);
            }
        });


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
    void agregarTripulanteEvent(ActionEvent event) throws Exception {
        String id = txtID.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String direccion = txtDireccion.getText();
        LocalDate fechaNacimiento = dateNacimiento.getValue();
        String estudios = txtEstudios.getText();
        RolTripulante rolTripulante = cbRol.getValue();

        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || direccion.isEmpty() || estudios.isEmpty() || fechaNacimiento == null) {
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }
        if (!id.matches("\\d+")) {
            mostrarAlerta("Error", "El ID debe contener solo números", Alert.AlertType.ERROR);
            return;
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            mostrarAlerta("Error", "El nombre solo puede contener letras", Alert.AlertType.ERROR);
            return;
        }
        if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            mostrarAlerta("Error", "El apellido solo puede contener letras", Alert.AlertType.ERROR);
            return;
        }
        if (!correo.matches("^[\\w.-]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,}$")) {
            mostrarAlerta("Error", "El correo no tiene un formato válido", Alert.AlertType.ERROR);
            return;
        }
       
        Tripulante nuevoTripulante = modelFactoryController.registrarTripulante(
                id, nombre, apellido, direccion, fechaNacimiento, correo, estudios, rolTripulante
        );

        if (nuevoTripulante == null) {
            mostrarAlerta("Error", "No se pudo registrar el tripulante. Verifique los datos.", Alert.AlertType.ERROR);
            return;
        }
        tableTripulantes.getItems().add(nuevoTripulante);
        tableTripulantes.refresh();


        mostrarAlerta("Éxito", "Tripulante registrado y guardado correctamente.", Alert.AlertType.INFORMATION);

        // Registrar acción en el sistema y limpiar campos
        registrarAccionesSistema("Registro Tripulante", 1, "Se registró el tripulante " + nombre);
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
            // Recoger los datos del formulario
            String id = txtID.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String direccion = txtDireccion.getText();
            String correo = txtCorreo.getText();
            LocalDate fechaNacimiento = dateNacimiento.getValue();
            String estudios = txtEstudios.getText();
            RolTripulante rolTripulante = cbRol.getValue();

            // Validar campos obligatorios
            if (id.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || correo.isEmpty() || estudios.isEmpty() || fechaNacimiento == null || rolTripulante == null) {
                mostrarAlerta("Error", "Todos los campos son obligatorios para modificar un tripulante.", Alert.AlertType.ERROR);
                return;
            }
            if (!id.matches("\\d+")) {
                mostrarAlerta("Error", "El ID debe contener solo números", Alert.AlertType.ERROR);
                return;
            }
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                mostrarAlerta("Error", "El nombre solo puede contener letras", Alert.AlertType.ERROR);
                return;
            }
            if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                mostrarAlerta("Error", "El apellido solo puede contener letras", Alert.AlertType.ERROR);
                return;
            }
            if (!correo.matches("^[\\w.-]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,}$")) {
                mostrarAlerta("Error", "El correo no tiene un formato válido", Alert.AlertType.ERROR);
                return;
            }
            if (modelFactoryController.getListaTripulantes().toCollection().stream().anyMatch(t -> t.getId().equals(id))) {
                mostrarAlerta("Error", "El ID ya está registrado para otro tripulante", Alert.AlertType.ERROR);
                return;
            }

            // Crear un nuevo tripulante con los datos
            Tripulante tripulanteNuevo = new Tripulante(id, nombre, apellido, direccion, fechaNacimiento, correo, estudios, rolTripulante);

            // Llamar al método del ModelFactoryController
            boolean actualizado = modelFactoryController.actualizarTripulante(tripulanteSeleccionado.getId(), tripulanteNuevo);

            if (actualizado) {
                cargarDatosTabla(); // Refrescar la tabla
                mostrarAlerta("Éxito", "El tripulante ha sido actualizado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo actualizar el tripulante.", Alert.AlertType.ERROR);
            }

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
        tableTripulantesAsignados.getItems().clear();


        tableTripulantes.getItems().addAll(listaTripulantes.toCollection());
        tableTripulantesAsignados.getItems().addAll(listaTripulantes.toCollection());


        tableTripulantes.refresh();
        tableTripulantesAsignados.refresh();
    }



    public void agregarTripulanteOnAction(ActionEvent actionEvent)  {
        if(tableTripulantesAsignados.getSelectionModel().getSelectedItem()!=null)
        {
            try
            {
                if(tableTripulantesAsignados.getSelectionModel().getSelectedItem().getAvionAsignado()==null)
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
                ListaSimple<Tripulante> listaApoyo=rutaComboBox.getSelectionModel().getSelectedItem().getAvionAsignado().getListaTripulantes();
                listaApoyo.agregar(tableTripulantesAsignados.getSelectionModel().getSelectedItem());
                try
                {
                    modelFactoryController.getAerolinea().verificarAsignacion(rutaComboBox.getSelectionModel().getSelectedItem().getAvionAsignado().getNombre(),listaApoyo);
                    tableTripulantesAsignados.getSelectionModel().getSelectedItem().setAvionAsignado(rutaComboBox.getSelectionModel().getSelectedItem().getAvionAsignado());
                    rutaComboBox.getSelectionModel().getSelectedItem().getAvionAsignado().getListaTripulantes().agregar(tableTripulantesAsignados.getSelectionModel().getSelectedItem());
                    mostrarAlerta("Informacion","Tripulante agregado correctamente", Alert.AlertType.INFORMATION);
                }
                catch (ExcesoDeTripulantesException ex)
                {
                    mostrarAlerta("Error",e.getMessage(),Alert.AlertType.ERROR);
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
