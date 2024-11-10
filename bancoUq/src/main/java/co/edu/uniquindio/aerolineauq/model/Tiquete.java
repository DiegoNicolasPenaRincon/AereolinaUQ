package co.edu.uniquindio.aerolineauq.model;

import java.io.Serializable;

public class Tiquete implements Serializable {
    private static final long serialVersionUID = 1L;
    private String numeroVuelo;
    private Usuario usuario;
    private Ruta ruta;
    private double precio;
    private ClaseVuelo claseVuelo;
    private Silla silla;
    private TipoViaje tipoViaje;
    private Equipaje equipaje;  // Nuevo atributo para asociar equipaje

    // Constructor, getters y setters
    public Tiquete(String numeroVuelo,Usuario usuario, Ruta ruta, double precio, ClaseVuelo claseVuelo, Silla silla, TipoViaje tipoViaje, Equipaje equipaje) {
        this.numeroVuelo = numeroVuelo;
        this.usuario = usuario;
        this.ruta = ruta;
        this.precio = precio;
        this.claseVuelo = claseVuelo;
        this.silla = silla;
        this.tipoViaje = tipoViaje;
        this.equipaje = equipaje;

    }

    public Tiquete() {
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ClaseVuelo getClaseVuelo() {
        return claseVuelo;
    }

    public void setClaseVuelo(ClaseVuelo claseVuelo) {
        this.claseVuelo = claseVuelo;
    }

    public Silla getSilla() {
        return silla;
    }

    public void setSilla(Silla silla) {
        this.silla = silla;
    }

    public TipoViaje getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(TipoViaje tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public Equipaje getEquipaje() {
        return equipaje;
    }

    public void setEquipaje(Equipaje equipaje) {
        this.equipaje = equipaje;
    }

    public void agregarEquipaje(Equipaje equipaje) {
        this.equipaje = equipaje;
    }
}
