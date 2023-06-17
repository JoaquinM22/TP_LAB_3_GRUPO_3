package TPFinalLab3.Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Registro implements Serializable
{
    private static final long serialVersionUID = -6232542664770078482L;

    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Habitacion ocupada;
    private Cliente ocupante;
    private double precioPagado;

    public Registro()
    {

    }
    public Registro(LocalDate fechaEntrada, LocalDate fechaSalida, Habitacion ocupada, Cliente ocupante, double precioPagado) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.ocupada = ocupada;
        this.ocupante = ocupante;
        this.precioPagado = precioPagado;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Habitacion getOcupada() {
        return ocupada;
    }

    public void setOcupada(Habitacion ocupada) {
        this.ocupada = ocupada;
    }

    public Cliente getOcupante() {
        return ocupante;
    }

    public void setOcupante(Cliente ocupante) {
        this.ocupante = ocupante;
    }

    public double getPrecioPagado() {
        return precioPagado;
    }

    public void setPrecioPagado(double precioPagado) {
        this.precioPagado = precioPagado;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", ocupada=" + ocupada +
                ", ocupante=" + ocupante +
                ", precioPagado=" + precioPagado +
                '}';
    }
}