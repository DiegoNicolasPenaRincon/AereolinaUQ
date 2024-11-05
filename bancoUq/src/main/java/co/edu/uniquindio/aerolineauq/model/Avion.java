package co.edu.uniquindio.aerolineauq.model;

import co.edu.uniquindio.aerolineauq.Listas.ListaSimple;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
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
    private String nombre;
    //private claseVuelo claseVuelo;
}
