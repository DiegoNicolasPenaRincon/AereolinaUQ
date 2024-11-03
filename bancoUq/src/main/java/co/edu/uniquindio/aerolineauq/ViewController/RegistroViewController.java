package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class RegistroViewController {

    @FXML
    private Button btnRegistrarse;

    @FXML
    private TextField txtApellido;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private DatePicker txtFecha;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtNombre;

    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    private AerolineaApplication aplicacion;

    public void setAplicacion(AerolineaApplication aplicacion){
        this.aplicacion=aplicacion;
    }

    @FXML
    void RegistrarUsuario(ActionEvent event) {
        String id = txtID.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String direccion = txtDireccion.getText();
        String contrasenia = txtContrasenia.getText();
        LocalDate fechaNacimiento = txtFecha.getValue();

        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || direccion.isEmpty() || contrasenia.isEmpty() || fechaNacimiento == null) {
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        modelFactoryController.registrarUsuario(id, nombre, apellido, correo, direccion, contrasenia, fechaNacimiento);
        registrarAccionesSistema("Registro usuario", 1, "Se registro el usuario "+ nombre);

        mostrarAlerta("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);

        limpiarCampos();
    }

    @FXML
    void irRegreso(ActionEvent event){
        aplicacion.mostrarVentanaPrincipal();
        registrarAccionesSistema("Volver atras", 1, "Se volvio al login ");
    }

    private void limpiarCampos() {
        txtID.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
        txtDireccion.clear();
        txtContrasenia.clear();
        txtFecha.setValue(null);
        registrarAccionesSistema("Limpiar campos", 1, "Se limpiaron los campos ");
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

}