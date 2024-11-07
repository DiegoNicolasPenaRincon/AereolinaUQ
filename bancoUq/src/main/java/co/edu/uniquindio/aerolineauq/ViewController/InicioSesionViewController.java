package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.time.LocalDate;

public class InicioSesionViewController {

    private AerolineaApplication aplicacion;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Button btnRegistro;

    @FXML
    private DatePicker dateNacimiento;

    @FXML
    private TextField txtApellido;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private PasswordField txtContraseniaNueva;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtIDNueva;

    @FXML
    private TextField txtNombre;

    @FXML
    private AnchorPane sideForm;

    @FXML
    private Button btnExisteCuenta;

    @FXML
    private AnchorPane SideInicio;

    @FXML
    private AnchorPane SideSign;

    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();


    public void setAplicacion(AerolineaApplication aplicacion) {
        this.aplicacion = aplicacion;
    }


    @FXML
    void Ingresar(ActionEvent event) {
        String id = txtID.getText();
        String contrasenia = txtContrasenia.getText();

        // Validar que los campos no estén vacíos
        if (id.isEmpty() || contrasenia.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        // Verificar las credenciales
        boolean inicioExitoso = modelFactoryController.validarInicioSesion(id, contrasenia);

        if (inicioExitoso) {
            mostrarAlerta("Éxito", "Inicio de sesión exitoso", Alert.AlertType.INFORMATION);
            // Aquí puedes redirigir al usuario a la siguiente vista, si es necesario
        } else {
            mostrarAlerta("Error", "ID o contraseña incorrecta", Alert.AlertType.ERROR);
        }
        aplicacion.mostrarVentanaCompras();
        registrarAccionesSistema("Ingresar", 1, "Se ingreso ");
    }

    @FXML
    void irRegistro(ActionEvent event) {
        TranslateTransition slider =new TranslateTransition();

        if(event.getSource() == btnRegistrarse){
            slider.setNode(sideForm);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));
            SideInicio.setVisible(false);

            slider.setOnFinished((ActionEvent e) ->{

                btnExisteCuenta.setVisible(true);
                btnRegistrarse.setVisible(false);
            });

            slider.play();
        } else if(event.getSource() == btnExisteCuenta){
            slider.setNode(sideForm);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));
            SideInicio.setVisible(true);

            slider.setOnFinished((ActionEvent e) ->{

                btnExisteCuenta.setVisible(false);
                btnRegistrarse.setVisible(true);
            });

            slider.play();
        }
    }

    @FXML
    void RegistrarUsuario(ActionEvent event) {
        String id = txtIDNueva.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String direccion = txtDireccion.getText();
        String contrasenia = txtContraseniaNueva.getText();
        LocalDate fechaNacimiento = dateNacimiento.getValue();

        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || direccion.isEmpty() || contrasenia.isEmpty() || fechaNacimiento == null) {
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        modelFactoryController.registrarUsuario(id, nombre, apellido, correo, direccion, contrasenia, fechaNacimiento);
        registrarAccionesSistema("Registro usuario", 1, "Se registro el usuario "+ nombre);

        mostrarAlerta("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);

        limpiarCampos();
    }

    private void limpiarCampos() {
        txtIDNueva.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
        txtDireccion.clear();
        txtContraseniaNueva.clear();
        dateNacimiento.setValue(null);
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
