package TPFinalLab3.Clases;
import java.io.Serializable;

public class Habitacion implements Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = -2367655082727167829L;

    /** ATRIBUTOS **/
    private int id;
    //enum estado;
    private Cliente ocupante;
    private double precio;

    /** CONSTRUCTOR **/
    public Habitacion(int id, Cliente ocupante, double precio)
    {
        this.id = id;
        this.ocupante = ocupante;
        this.precio = precio;
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
    public void setEstado(Estado estado) {this.estado = estado;}


    /** GETTERS **/
    public Estado getEstado() {return estado;}
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
