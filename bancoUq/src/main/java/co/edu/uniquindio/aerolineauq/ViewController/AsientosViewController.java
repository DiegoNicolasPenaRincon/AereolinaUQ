package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.Silla;
import co.edu.uniquindio.aerolineauq.model.Tiquete;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AsientosViewController {

    @FXML
    private AnchorPane panelPrincipal;

    @FXML
    private Pane paneAsientos;

    private AerolineaApplication aplicacion;

    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    private String avion;

    private int numAsientos;

    private ListaSimple<Tiquete> listaTiquetes=new ListaSimple<>();

    private List<Silla> sillasSeleccionadas = new ArrayList<>();
    private int contadorSeleccionados = 0; // Contador para rastrear asientos seleccionados

    public AsientosViewController() throws IOException {
    }

    public void setAplicacion(AerolineaApplication aplicacion, String avion, ListaSimple listaTiquetes, int cantidadPersonas) {
        this.aplicacion = aplicacion;
        this.avion = avion;
        this.listaTiquetes = listaTiquetes;
        this.numAsientos = cantidadPersonas;
    }

    @FXML
    public void initialize() {
        paneAsientos.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                // Ahora el paneAsientos está asociado a una escena
                crearAsientosA320();
                ajustarTamañoPane(2500, 400);

                // Establecer el manejador de cierre de ventana
                newScene.getWindow().setOnCloseRequest(event -> {
                    if (contadorSeleccionados < numAsientos) {
                        event.consume(); // Bloquear el cierre de la ventana
                        mostrarAlerta("Por favor, selecciona todos los asientos requeridos antes de cerrar.");
                    } else {
                        enviarSillasSeleccionadas();
                    }
                });
            }
        });
    }


    private void crearAsientosA320() {
        int filasEjecutiva = 3; // 3 filas para clase ejecutiva
        int filasEconomica = 23; // 23 filas para clase económica
        int totalFilas = filasEjecutiva + filasEconomica;

        // Configuración inicial de coordenadas y espacio entre filas
        double xInicial = 20;
        double yInicialIzquierda = 20;
        double yInicialDerecha = 200;
        double espacioVertical = 35; // Espacio entre asientos en vertical (para las columnas)
        double espacioHorizontal = 50; // Espacio entre filas (en horizontal)

        // Tamaño de los botones de asiento
        double anchoAsiento = 35;
        double altoAsiento = 30;
        String identificador;
        // Creación de los asientos de clase ejecutiva (4 sillas por fila: AC y DF)
        for (int fila = 1; fila <= filasEjecutiva; fila++) {
            for (int columna = 0; columna < 2; columna++) { // 2 sillas por lado
                // Lado izquierdo: A y C
                String letraIzquierda = String.valueOf((char) ('A' + columna * 2)); // A y C
                Button asientoIzquierda = new Button(fila + letraIzquierda);
                asientoIzquierda.setPrefWidth(anchoAsiento);
                asientoIzquierda.setPrefHeight(altoAsiento);
                asientoIzquierda.setStyle("-fx-font-size: 10px; -fx-background-color: green;");
                asientoIzquierda.setLayoutX(xInicial + (fila - 1) * espacioHorizontal);
                asientoIzquierda.setLayoutY(yInicialIzquierda + columna * espacioVertical);
                identificador=fila+letraIzquierda;
                if (esSillaOcupada(identificador)) {
                    asientoIzquierda.setStyle("-fx-font-size: 10px; -fx-background-color: red;");
                    asientoIzquierda.setDisable(true); // Deshabilitar el asiento si está ocupado
                } else {
                    String finalIdentificador = identificador;
                    asientoIzquierda.setOnAction(event -> seleccionarAsiento(asientoIzquierda, finalIdentificador));
                }
                paneAsientos.getChildren().add(asientoIzquierda);

                // Lado derecho: D y F
                String letraDerecha = String.valueOf((char) ('D' + columna * 2)); // D y F
                Button asientoDerecha = new Button(fila + letraDerecha);
                asientoDerecha.setPrefWidth(anchoAsiento);
                asientoDerecha.setPrefHeight(altoAsiento);
                asientoDerecha.setStyle("-fx-font-size: 10px; -fx-background-color: green;");
                asientoDerecha.setLayoutX(xInicial + (fila - 1) * espacioHorizontal);
                asientoDerecha.setLayoutY(yInicialDerecha + columna * espacioVertical);
                identificador=fila+letraIzquierda;
                if (esSillaOcupada(identificador)) {
                    asientoDerecha.setStyle("-fx-font-size: 10px; -fx-background-color: red;");
                    asientoDerecha.setDisable(true); // Deshabilitar el asiento si está ocupado
                } else {
                    String finalIdentificador = identificador;
                    asientoDerecha.setOnAction(event -> seleccionarAsiento(asientoDerecha, finalIdentificador));
                }
                paneAsientos.getChildren().add(asientoDerecha);
            }
        }

        // Ajustar coordenada X para los asientos de clase económica
        xInicial += filasEjecutiva * espacioHorizontal + espacioHorizontal;

        // Creación de los asientos de clase económica (6 sillas por fila: ABC y DEF)
        for (int fila = filasEjecutiva + 1; fila <= totalFilas; fila++) {
            for (int columna = 0; columna < 3; columna++) { // 3 sillas por lado
                // Lado izquierdo: A, B, C
                String letraIzquierda = String.valueOf((char) ('A' + columna));
                Button asientoIzquierda = new Button(fila + letraIzquierda);
                asientoIzquierda.setPrefWidth(anchoAsiento);
                asientoIzquierda.setPrefHeight(altoAsiento);
                asientoIzquierda.setStyle("-fx-font-size: 10px; -fx-background-color: green;");
                asientoIzquierda.setLayoutX(xInicial + (fila - filasEjecutiva - 1) * espacioHorizontal);
                asientoIzquierda.setLayoutY(yInicialIzquierda + columna * espacioVertical);
                identificador=fila+letraIzquierda;
                if (esSillaOcupada(identificador)) {
                    asientoIzquierda.setStyle("-fx-font-size: 10px; -fx-background-color: red;");
                    asientoIzquierda.setDisable(true); // Deshabilitar el asiento si está ocupado
                } else {
                    String finalIdentificador = identificador;
                    asientoIzquierda.setOnAction(event -> seleccionarAsiento(asientoIzquierda, finalIdentificador));
                }
                paneAsientos.getChildren().add(asientoIzquierda);

                // Lado derecho: D, E, F
                String letraDerecha = String.valueOf((char) ('D' + columna));
                Button asientoDerecha = new Button(fila + letraDerecha);
                asientoDerecha.setPrefWidth(anchoAsiento);
                asientoDerecha.setPrefHeight(altoAsiento);
                asientoDerecha.setStyle("-fx-font-size: 10px; -fx-background-color: green;");
                asientoDerecha.setLayoutX(xInicial + (fila - filasEjecutiva - 1) * espacioHorizontal);
                asientoDerecha.setLayoutY(yInicialDerecha + columna * espacioVertical);
                identificador=fila+letraIzquierda;
                if (esSillaOcupada(identificador)) {
                    asientoDerecha.setStyle("-fx-font-size: 10px; -fx-background-color: red;");
                    asientoDerecha.setDisable(true); // Deshabilitar el asiento si está ocupado
                } else {
                    String finalIdentificador = identificador;
                    asientoDerecha.setOnAction(event -> seleccionarAsiento(asientoDerecha, finalIdentificador));
                }
                paneAsientos.getChildren().add(asientoDerecha);
            }
        }
    }



    private boolean esSillaOcupada(String identificador) {
        for (Tiquete tiquete : listaTiquetes) {
            String silla = tiquete.getSilla().getFila() + tiquete.getSilla().getPosicion();
            if (silla.equals(identificador)) {
                return true;
            }
        }
        return false;
    }

    private void seleccionarAsiento(Button asiento, String identificador) {
        if (asiento.getStyle().contains("blue")) {
            // Deseleccionar asiento
            asiento.setStyle("-fx-font-size: 10px; -fx-background-color: green;");
            contadorSeleccionados--;
            sillasSeleccionadas.removeIf(silla -> (silla.getFila() + silla.getPosicion()).equals(identificador));
        } else if (contadorSeleccionados < numAsientos) {
            // Seleccionar asiento
            asiento.setStyle("-fx-font-size: 10px; -fx-background-color: blue;");
            contadorSeleccionados++;
            sillasSeleccionadas.add(new Silla(identificador.substring(0, 1), identificador.substring(1)));
        } else {
            mostrarAlerta("Ya has seleccionado el número máximo de asientos permitidos.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void enviarSillasSeleccionadas() {
        // Lógica para enviar las sillas seleccionadas a la ventana anterior
      //  aplicacion.recibirSillasSeleccionadas(sillasSeleccionadas);
    }

    private void ajustarTamañoPane(int anchoRequerido, int altoRequerido) {
        paneAsientos.setPrefWidth(anchoRequerido);
        paneAsientos.setPrefHeight(altoRequerido);
    }
}