package TPFinalLab3.Clases;
import java.io.*;

public class Administrador extends Persona implements Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = 352942585576717560L;

    /** ATRIBUTOS **/
    private String password;
    private double sueldo;

    /** CONSTRUCTOR **/
    public Administrador(String nombre, String direccion, int dni, String password, double sueldo)
    {
        super(nombre, direccion, dni);
        this.password = password;
        this.sueldo = sueldo;
    }

    /** SETTERS **/
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setSueldo(double sueldo)
    {
        this.sueldo = sueldo;
    }

    /** GETTERS **/
    public String getPassword()
    {
        return password;
    }
    public double getSueldo()
    {
        return sueldo;
    }

    /** METODOS **/
    @Override
    public String toString()
    {
        return  "\n--------------------" +
                "\nDATOS ADMINISTRADOR" +
                "\n--------------------" +
                "\nNombre: " + super.getNombre() +
                "\nDomicilio: " + super.getDireccion() +
                "\nDNI: " + super.getDni() +
                "\nSueldo: $" + sueldo +
                "\n--------------------";
    }
    public void hacerBackUp(String dato) /** DESPUES HAY QUE CAMBIAR "STRING" POR LA CLASE "HOTEL" **/
    {
        try
        {
            FileOutputStream fOutput = new FileOutputStream(new File("mi_archivo.txt"));
            ObjectOutputStream objOutput = new ObjectOutputStream(fOutput);
            System.out.println("Guardando Archivo...");
            objOutput.writeObject(dato);
            objOutput.close();
            System.out.println(".... El Archivo se guardo correctamente ....");
        }catch(IOException e)
        {
            System.out.println("Error al escribir en el Archivo");
            e.getMessage();
            e.printStackTrace();
        }
    }
}
