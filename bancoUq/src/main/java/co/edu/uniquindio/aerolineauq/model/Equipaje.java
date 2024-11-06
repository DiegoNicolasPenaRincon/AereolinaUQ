package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


public class Equipaje implements Serializable {
    private static final long serialVersionUID = 1L;

    private double peso;
    private double costoAdicional;
    private boolean esMascota;
    private double pesoMascota;
    private int alto;
    private int ancho;
    private int largo;
    private String categoriaViaje;
    private claseVuelo claseVuelo;

    public Equipaje(double peso, boolean esMascota, double pesoMascota, String categoriaViaje, claseVuelo claseVuelo) {
        this.peso = peso;
        this.esMascota = esMascota;
        this.pesoMascota = pesoMascota;
        this.categoriaViaje = categoriaViaje;
        this.claseVuelo = claseVuelo;
        this.costoAdicional = calcularCostoAdicional();
    }

    public Equipaje() {
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getCostoAdicional() {
        return costoAdicional;
    }

    public void setCostoAdicional(double costoAdicional) {
        this.costoAdicional = costoAdicional;
    }

    public boolean isEsMascota() {
        return esMascota;
    }

    public void setEsMascota(boolean esMascota) {
        this.esMascota = esMascota;
    }

    public double getPesoMascota() {
        return pesoMascota;
    }

    public void setPesoMascota(double pesoMascota) {
        this.pesoMascota = pesoMascota;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public String getCategoriaViaje() {
        return categoriaViaje;
    }

    public void setCategoriaViaje(String categoriaViaje) {
        this.categoriaViaje = categoriaViaje;
    }

    public co.edu.uniquindio.aerolineauq.model.claseVuelo getClaseVuelo() {
        return claseVuelo;
    }

    public void setClaseVuelo(co.edu.uniquindio.aerolineauq.model.claseVuelo claseVuelo) {
        this.claseVuelo = claseVuelo;
    }

    public double calcularCostoAdicional() {
        double costo = 0;
        double pesoMaximo = claseVuelo == claseVuelo.EJECUTIVA ? 34 : 24;
        int cantidadPiezas = categoriaViaje.equalsIgnoreCase("internacional") ? 2 : 1;
        double pesoTotalPermitido = pesoMaximo * cantidadPiezas;

        if (peso > pesoTotalPermitido) {
            costo += (peso - pesoTotalPermitido) * 8 * 1.0675; // Costo por sobrepeso
        }
        if (esMascota) {
            costo += (pesoMascota > 9) ? (pesoMascota - 9) * 2 + 48 : 48;
        }
        return costo;
    }



    // Getters y Setters
}

