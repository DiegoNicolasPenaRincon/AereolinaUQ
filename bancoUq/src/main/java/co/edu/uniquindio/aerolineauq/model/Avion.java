package co.edu.uniquindio.aerolineauq.model;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class Avion implements Serializable {
    private static final long serialVersionUID = 1L;
    private TipoAvion tipoAvion;
    private int cantidadPasajerosEconomica;
    private int cantidadPasajerosEjecutiva;
    private int cantidadPasajerosActualEconomica;
    private int cantidadPasajerosActualEjecutiva;
    private double capacidadCarga;
    private double cargaActual;
    private ListaSimple<Usuario> listaPasajeros=new ListaSimple<>();
    private ListaSimple<Tripulante> listaTripulantes=new ListaSimple<>();
    private String nombre;
    private Ruta rutaAsignada;

    public TipoAvion getTipoAvion() {
        return tipoAvion;
    }

    public void setTipoAvion(TipoAvion tipoAvion) {
        this.tipoAvion = tipoAvion;
    }

    public int getCantidadPasajerosEconomica() {
        return cantidadPasajerosEconomica;
    }

    public void setCantidadPasajerosEconomica(int cantidadPasajerosEconomica) {
        this.cantidadPasajerosEconomica = cantidadPasajerosEconomica;
    }

    public int getCantidadPasajerosEjecutiva() {
        return cantidadPasajerosEjecutiva;
    }

    public void setCantidadPasajerosEjecutiva(int cantidadPasajerosEjecutiva) {
        this.cantidadPasajerosEjecutiva = cantidadPasajerosEjecutiva;
    }

    public int getCantidadPasajerosActualEconomica() {
        return cantidadPasajerosActualEconomica;
    }

    public void setCantidadPasajerosActualEconomica(int cantidadPasajerosActualEconomica) {
        this.cantidadPasajerosActualEconomica = cantidadPasajerosActualEconomica;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public int getCantidadPasajerosActualEjecutiva() {
        return cantidadPasajerosActualEjecutiva;
    }

    public void setCantidadPasajerosActualEjecutiva(int cantidadPasajerosActualEjecutiva) {
        this.cantidadPasajerosActualEjecutiva = cantidadPasajerosActualEjecutiva;
    }

    public double getCargaActual() {
        return cargaActual;
    }

    public void setCargaActual(double cargaActual) {
        this.cargaActual = cargaActual;
    }

    public ListaSimple<Usuario> getListaPasajeros() {
        return listaPasajeros;
    }

    public void setListaPasajeros(ListaSimple<Usuario> listaPasajeros) {
        this.listaPasajeros = listaPasajeros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ruta getRutaAsignada() {
        return rutaAsignada;
    }

    public void setRutaAsignada(Ruta rutaAsignada) {
        this.rutaAsignada = rutaAsignada;
    }

    public ListaSimple<Tripulante> getListaTripulantes() {
        return listaTripulantes;
    }

    public void setListaTripulantes(ListaSimple<Tripulante> listaTripulantes) {
        this.listaTripulantes = listaTripulantes;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
