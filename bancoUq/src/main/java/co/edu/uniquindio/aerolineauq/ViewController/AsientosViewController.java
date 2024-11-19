package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.Tiquete;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AsientosViewController {

    @FXML
    private Pane paneAsientos;

    private AerolineaApplication aplicacion;

    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    private String avion;

    private ListaSimple<Tiquete> listaTiquetes;
    public AsientosViewController() throws IOException {
    }

    public void setAplicacion(AerolineaApplication aplicacion, String avion, ListaSimple listaTiquetes) {
        this.aplicacion = aplicacion;
        this.avion=avion;
        this.listaTiquetes=listaTiquetes;
    }

    public void initialize() {
        crearAsientosA320();
    }

    private void crearAsientosA320() {
        int filasEjecutiva = 4; // 3 filas para clase ejecutiva (6 asientos por fila)
        int filasEconomica = 6; // 20 filas para clase económica (8 asientos por fila)
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

        // Creación de los asientos de clase ejecutiva (6 asientos por fila)
        for (int fila = 1; fila <= filasEjecutiva; fila++) {
            for (int columna = 0; columna < 3; columna++) { // Cambiado a 3 asientos por lado
                String letraIzquierda = String.valueOf((char) ('A' + columna));
                Button asientoIzquierda = new Button(fila + letraIzquierda);
                asientoIzquierda.setPrefWidth(anchoAsiento);
                asientoIzquierda.setPrefHeight(altoAsiento);
                asientoIzquierda.setStyle("-fx-font-size: 10px;");
                asientoIzquierda.setLayoutX(xInicial + (fila - 1) * espacioHorizontal);
                asientoIzquierda.setLayoutY(yInicialIzquierda + columna * espacioVertical);
                paneAsientos.getChildren().add(asientoIzquierda);

                String letraDerecha = String.valueOf((char) ('D' + columna)); // Cambiado a 'D' para 3 asientos en el otro lado
                Button asientoDerecha = new Button(fila + letraDerecha);
                asientoDerecha.setPrefWidth(anchoAsiento);
                asientoDerecha.setPrefHeight(altoAsiento);
                asientoDerecha.setStyle("-fx-font-size: 10px;");
                asientoDerecha.setLayoutX(xInicial + (fila - 1) * espacioHorizontal);
                asientoDerecha.setLayoutY(yInicialDerecha + columna * espacioVertical);
                paneAsientos.getChildren().add(asientoDerecha);
            }
        }

        // Ajustar coordenada X para los asientos de clase económica
        xInicial += filasEjecutiva * espacioHorizontal + espacioHorizontal;

        // Creación de los asientos de clase económica (8 asientos por fila)
        for (int fila = filasEjecutiva + 1; fila <= totalFilas; fila++) {
            for (int columna = 0; columna < 4; columna++) { // Cambiado a 4 asientos por lado
                String letraIzquierda = String.valueOf((char) ('A' + columna));
                Button asientoIzquierda = new Button(fila + letraIzquierda);
                asientoIzquierda.setPrefWidth(anchoAsiento);
                asientoIzquierda.setPrefHeight(altoAsiento);
                asientoIzquierda.setStyle("-fx-font-size: 10px;");
                asientoIzquierda.setLayoutX(xInicial + (fila - filasEjecutiva - 1) * espacioHorizontal);
                asientoIzquierda.setLayoutY(yInicialIzquierda + columna * espacioVertical);
                paneAsientos.getChildren().add(asientoIzquierda);

                String letraDerecha = String.valueOf((char) ('E' + columna)); // Cambiado a 'E' para 4 asientos en el otro lado
                Button asientoDerecha = new Button(fila + letraDerecha);
                asientoDerecha.setPrefWidth(anchoAsiento);
                asientoDerecha.setPrefHeight(altoAsiento);
                asientoDerecha.setStyle("-fx-font-size: 10px;");
                asientoDerecha.setLayoutX(xInicial + (fila - filasEjecutiva - 1) * espacioHorizontal);
                asientoDerecha.setLayoutY(yInicialDerecha + columna * espacioVertical);
                paneAsientos.getChildren().add(asientoDerecha);
            }
        }
    }
}