package TPFinalLab3.Clases;

import java.io.Serializable;

public class Habitacion implements Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = -2367655082727167829L;

    /** ATRIBUTOS **/
    private int id;
    private Cliente ocupante;
    private double precio;
    private Estado estado;

    /** ENUM **/
    public enum Estado
    {
        OCUPADO("O"),
        RESERVADO("R"),
        DISPONIBLE("D"),
        MANTENIMIENTO("M");

        private String abreviatura;

        Estado(String abreviatura)
        {
            this.abreviatura = abreviatura;
        }

        private String getAbreviatura()
        {
            return this.abreviatura;
        }
    }

    /** CONSTRUCTOR **/
    public Habitacion(int id, Cliente ocupante, double precio)
    {
        this.id = id;
        this.ocupante = ocupante;
        this.precio = precio;
        this.estado = Estado.DISPONIBLE;
    }

    /** SETTERS **/
    public void setId(int id)
    {
        this.id = id;
    }
    public void setOcupante(Cliente ocupante)
    {
        this.ocupante = ocupante;
    }
    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    /** GETTERS **/
    public int getId()
    {
        return id;
    }
    public Cliente getOcupante()
    {
        return ocupante;
    }
    public double getPrecio()
    {
        return precio;
    }

    /** METODOS **/
    @Override
    public String toString()
    {
        return  "\n------------------------" +
                "\nDATOS HABITACION" +
                "\n--------------------" +
                "\nNro Habitacion: " + id +
                "\nPrecio: $" + precio +
                ocupante.toString() +
                "\n------------------------";
    }

}
