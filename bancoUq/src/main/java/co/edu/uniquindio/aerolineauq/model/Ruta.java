package co.edu.uniquindio.aerolineauq.model;

import java.io.Serializable;
import java.time.LocalTime;


public class Ruta implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Destino origen=Destino.CDMX;
    private Destino destino;
    private LocalTime duracion;
    private LocalTime horaSalida;
    private LocalTime horaRegreso;
    private double precio;
    private Avion avionAsignado;

    public Ruta(Destino destino, LocalTime duracion, LocalTime horaSalida, LocalTime horaRegreso, double precio,Avion avionAsignado) {
        this.destino = destino;
        this.duracion = duracion;
        this.horaSalida = horaSalida;
        this.horaRegreso = horaRegreso;
        this.precio=precio;
        this.avionAsignado=avionAsignado;
    }

    public Ruta() {
    }

    public Destino getOrigen() {
        return origen;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalTime getHoraRegreso() {
        return horaRegreso;
    }

    public void setHoraRegreso(LocalTime horaRegreso) {
        this.horaRegreso = horaRegreso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Avion getAvionAsignado() {
        return avionAsignado;
    }

    public void setAvionAsignado(Avion avionAsignado) {
        this.avionAsignado = avionAsignado;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "destino=" + destino +
                '}';
    }
}
