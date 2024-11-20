package co.edu.uniquindio.aerolineauq;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.ViewController.*;
import co.edu.uniquindio.aerolineauq.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AerolineaApplication extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Aerolinea UQ");
        mostrarVentanaPrincipal();
    }

    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AerolineaApplication.class.getResource("/co/edu/uniquindio/aerolineauq/InicioSesion.fxml"));
            Parent root= loader.load();
            InicioSesionViewController inicioSesionViewController=loader.getController();
            inicioSesionViewController.setAplicacion(this);
            Scene scene = new Scene(root);
            primaryStage.setTitle("Aerolinea UQ");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setWidth(600);
            primaryStage.setHeight(405);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/aerolineauq/MenuView.fxml"));
            Parent root = loader.load();

            MenuViewController menuViewController = loader.getController();
            menuViewController.setAplicacion(this);

            Scene scene = new Scene(root);
            primaryStage.setTitle("Menú de Usuario");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setWidth(1100);
            primaryStage.setHeight(600);
            primaryStage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaEquipaje(Usuario usuarioActual, Ruta rutaSeleccionada, double precio, ClaseVuelo claseVuelo, List<Silla> asientosSeleccionados, TipoViaje tipoViaje, LocalDate fechaSalida, LocalDate fechaRegreso, int cantidadPersonas){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/aerolineauq/Equipaje.fxml"));
            Parent root = loader.load();

            EquipajeViewController equipajeViewController = loader.getController();
            equipajeViewController.setAplicacion(this, usuarioActual, rutaSeleccionada, precio, claseVuelo, asientosSeleccionados, tipoViaje, fechaSalida, fechaRegreso, cantidadPersonas);

            Scene scene = new Scene(root);
            primaryStage.setTitle("equipaje");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setWidth(1100);
            primaryStage.setHeight(600);
            primaryStage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaAdministrador() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/aerolineauq/AdminView.fxml"));
        Parent root = loader.load();

        AdminViewController adminViewController =loader.getController();
        adminViewController.setAplicacion(this);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Administrador");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setWidth(1100);
        primaryStage.setHeight(600);
        primaryStage.centerOnScreen();
    }

    public void mostrarAsientos(String avion, ListaSimple<Tiquete> tiquetesRelacionados, int cantidadPersonas, ClaseVuelo claseVuelo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/aerolineauq/AsientosView.fxml"));
            Parent root = loader.load();

            AsientosViewController asientosViewController = loader.getController();
            //asientosViewController.setAplicacion(this, avion, tiquetesRelacionados, cantidadPersonas, claseVuelo);

            // Crear un nuevo Stage
            Stage nuevoStage = new Stage();
            Scene scene = new Scene(root);

            // Configurar la nueva ventana
            nuevoStage.setTitle("Asientos");
            nuevoStage.setScene(scene);
            nuevoStage.centerOnScreen();
            nuevoStage.initModality(Modality.APPLICATION_MODAL); // Bloquear interacción con otras ventanas (opcional)
            nuevoStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}