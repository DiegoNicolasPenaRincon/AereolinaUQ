package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


public class Silla implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fila;
    private String posicion;

    public Silla(String fila, String posicion) {
        this.fila = fila;
        this.posicion = posicion;
    }

    public Silla(){

    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
