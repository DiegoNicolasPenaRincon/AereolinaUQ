package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ruta implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Destino origen=Destino.CDMX;
    private Destino destino;
    private LocalTime duracion;
    private LocalTime horaSalida;
    private LocalTime horaRegreso;
}
