package TPFinalLab3.Clases;
import TPFinalLab3.Interfaces.CargarDinero;

import java.io.Serializable;
import java.util.Scanner;

public class Cliente extends Persona implements CargarDinero, Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = -1121434585563208736L;



    /** ATRIBUTOS **/
    private double saldo;
    private final static Scanner teclado = new Scanner(System.in);
    private boolean consumi = false;



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
    public void setConsumi(boolean consumi)
    {
        this.consumi = consumi;
    }

    /** GETTERS **/
    public double getSaldo()
    {
        return saldo;
    }
    public boolean isConsumi()
    {
        return consumi;
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
                "\n--------------------";
    }
    @Override
    public void cargarSaldo(Cliente aux, double precio)
    {
        if(aux.getSaldo() < precio)
        {
            do
            {
                System.out.println("Su saldo es insuficiente");
                System.out.println("Precio producto: $" + precio);
                System.out.println("Saldo: $" + aux.getSaldo());
                double faltan = precio - aux.getSaldo();
                System.out.println("Faltan $" + faltan + " para poder comprar el producto");
                System.out.print("\nIngrese la cantidad a cargar: ");
                aux.setSaldo(aux.getSaldo() + teclado.nextInt());
                teclado.nextLine();
            }while(aux.getSaldo() < precio);
        }
    }
    public void realizarConsumo()
    {
        System.out.println("Que consumo desea realizar?");
        System.out.println("1- Comprar bebida: $800");
        System.out.println("2- Comprar comida: $1200");
        System.out.println("3- Comprar comida y Bebida: $1800");
        System.out.println("\n0- Cancelar");
        int seleccion = -1;
        do
        {
            System.out.print("\nIngrese el numero referente a la accion:");
            seleccion = teclado.nextInt();
            if(seleccion < 1 || seleccion > 3)
            {
                System.out.println("Accion invalida. Intente de nuevo");
            }

        }while(seleccion < 1 || seleccion > 3);

        if(seleccion == 1)
        {
            cargarSaldo(this, 800);
            this.setSaldo(this.getSaldo() - 800);
        }else if(seleccion == 2)
        {
            cargarSaldo(this, 1200);
            this.setSaldo(this.getSaldo() - 1200);
        }else if(seleccion == 3)
        {
            cargarSaldo(this, 1800);
            this.setSaldo(this.getSaldo() - 1800);
        }

        if(seleccion >= 1 || seleccion <= 3)
        {
            this.setConsumi(true);
        }

    }
}
