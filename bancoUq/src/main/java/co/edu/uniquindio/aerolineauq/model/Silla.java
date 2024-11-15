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
    private boolean esDisponible;

    public Silla(String fila, String posicion, boolean esDisponible) {
        this.fila = fila;
        this.posicion = posicion;
        this.esDisponible = esDisponible;
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

    public boolean isEsDisponible() {
        return esDisponible;
    }

    public void setEsDisponible(boolean esDisponible) {
        this.esDisponible = esDisponible;
    }
}
