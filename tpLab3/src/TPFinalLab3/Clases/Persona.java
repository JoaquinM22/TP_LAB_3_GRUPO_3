package TPFinalLab3.Clases;
import java.io.Serializable;

public abstract class Persona implements Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = -6445231651267360642L;

    /** ATRIBUTOS **/
    private String nombre;
    private String direccion;
    private int dni;

    /** CONSTRUCTOR **/
    public Persona(String nombre, String direccion, int dni)
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.dni = dni;
    }

    /** SETTERS **/
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }
    public void setDni(int dni)
    {
        this.dni = dni;
    }

    /** GETTERS **/
    public String getNombre()
    {
        return nombre;
    }
    public String getDireccion()
    {
        return direccion;
    }
    public int getDni()
    {
        return dni;
    }

    /** METODOS **/
    @Override
    public abstract String toString();
}
