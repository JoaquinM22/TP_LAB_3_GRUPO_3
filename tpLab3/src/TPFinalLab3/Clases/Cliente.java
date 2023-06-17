package TPFinalLab3.Clases;
import java.io.Serializable;

public class Cliente extends Persona implements Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = -1121434585563208736L;

    /** ATRIBUTOS **/
    private double saldo;

    /** CONSTRUCTOR **/
    public Cliente()
    {
        super();
    }
    public Cliente(String nombre, String direccion, int dni, double saldo)
    {
        super(nombre, direccion, dni);
        this.saldo = saldo;
    }

    /** SETTERS **/
    public void setSaldo(double saldo)
    {
        this.saldo = saldo;
    }

    /** GETTERS **/
    public double getSaldo()
    {
        return saldo;
    }

    /** METODOS **/
    @Override
    public String toString()
    {
        return  "\n--------------------" +
                "\nDATOS CLIENTE" +
                "\n--------------------" +
                "\nNombre: " + super.getNombre() +
                "\nDomicilio: " + super.getDireccion() +
                "\nDNI: " + super.getDni() +
                "\nSaldo: " + saldo +
                "\n--------------------";
    }
    public void realizarConsumo()
    {

    }
}
