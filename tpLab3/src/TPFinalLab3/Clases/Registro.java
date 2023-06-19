package TPFinalLab3.Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Registro implements Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = 128438501752471011L;



    /** ATRIBUTOS **/
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Habitacion ocupada;
    private Cliente ocupante;
    private double precioPagado;
    private int cantDias;



    /** CONSTRUCTOR **/
    public Registro()
    {

    }
    public Registro(LocalDate fechaEntrada, LocalDate fechaSalida, Habitacion ocupada, Cliente ocupante, double precioPagado, int cantDias)
    {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.ocupada = ocupada;
        this.ocupante = ocupante;
        this.precioPagado = precioPagado;
        this.cantDias = cantDias;
    }



    /** SETTERS **/
    public void setFechaEntrada(LocalDate fechaEntrada)
    {
        this.fechaEntrada = fechaEntrada;
    }
    public void setFechaSalida(LocalDate fechaSalida)
    {
        this.fechaSalida = fechaSalida;
    }
    public void setOcupada(Habitacion ocupada)
    {
        this.ocupada = ocupada;
    }
    public void setOcupante(Cliente ocupante)
    {
        this.ocupante = ocupante;
    }
    public void setPrecioPagado(double precioPagado)
    {
        this.precioPagado = precioPagado;
    }
    public void setCantDias(int cantDias)
    {
        this.cantDias = cantDias;
    }



    /** GETTERS **/
    public LocalDate getFechaEntrada()
    {
        return fechaEntrada;
    }
    public LocalDate getFechaSalida()
    {
        return fechaSalida;
    }
    public Habitacion getOcupada()
    {
        return ocupada;
    }
    public Cliente getOcupante()
    {
        return ocupante;
    }
    public double getPrecioPagado()
    {
        return precioPagado;
    }
    public int getCantDias()
    {
        return cantDias;
    }



    @Override
    public String toString() {
        return  "\n-------------------------" +
                "\nDATOS REGISTRO" +
                "\n-------------------------" +
                "\nFecha Entrada: " + fechaEntrada +
                "\nFecha Salida: " + fechaSalida +
                ocupada.toString() +
                ocupante.toString() +
                "\nPrecio Pagado: $" + precioPagado +
                "\nSe quedo: " + cantDias + " dias" +
                "\n-------------------------";
    }
}
