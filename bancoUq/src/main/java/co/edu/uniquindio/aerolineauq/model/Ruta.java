package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;


public class Ruta implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Destino origen=Destino.CDMX;
    private Destino destino;
    private LocalTime duracion;
    private LocalTime horaSalida;
    private LocalTime horaRegreso;

    public Ruta(Destino destino, LocalTime duracion, LocalTime horaSalida, LocalTime horaRegreso) {
        this.destino = destino;
        this.duracion = duracion;
        this.horaSalida = horaSalida;
        this.horaRegreso = horaRegreso;
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
}
