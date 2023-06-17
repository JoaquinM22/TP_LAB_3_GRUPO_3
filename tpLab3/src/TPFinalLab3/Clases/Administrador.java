package TPFinalLab3.Clases;
import TPFinalLab3.Excepciones.ErrorEnArchivoException;
import TPFinalLab3.Interfaces.FuncionesEmpleados;
import TPFinalLab3.Interfaces.MetodosValidaciones;

import java.io.*;
import java.util.Scanner;

public class Administrador extends Persona implements MetodosValidaciones, FuncionesEmpleados, Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = 352942585576717560L;



    /** ATRIBUTOS **/
    private String password;
    private double sueldo;
    private final static Scanner teclado = new Scanner(System.in);



    /** CONSTRUCTOR **/
    public Administrador()
    {
        super();
    }
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
    @Override
    public void consultarSueldo()
    {
        System.out.println("Hola Administrador" + this.getNombre() + ", tu sueldo es de $" + this.getSueldo() + " pesos por mes");
    }
    public void concederPermisos(Hotel unHotel)
    {
        System.out.println("Ingrese el dni del Recepcionista al que desea concederle los permisos de administrador:");
        unHotel.mostrarRecepcionistas();
        int dniIngresado = validarDNI();
        Recepcionista encontrado = existeRecepcionista(dniIngresado, unHotel);

        if(encontrado == null)
        {
            System.out.println("El Recepcionsita ingresado no existe");
        }else
        {
            if(!encontrado.isTienePermiso())
            {
                encontrado.setTienePermiso(true);
                System.out.println("Ahora el Recpecionista tiene permisos de Administrador");
            }else
            {
                System.out.println("El Recepcionista ya posee permisos de Administrador");
            }
        }

    }



    public void hacerBackUp(Hotel dato) throws ErrorEnArchivoException /** DESPUES HAY QUE CAMBIAR "STRING" POR LA CLASE "HOTEL" **/
    {
        try
        {
            FileOutputStream fOutput = new FileOutputStream(new File("archivo_hotel.txt"));
            ObjectOutputStream objOutput = new ObjectOutputStream(fOutput);
            System.out.println("Guardando Archivo...");
            objOutput.writeObject(dato);
            objOutput.close();
            System.out.println(".... El Archivo se guardo correctamente ....");
        }catch(IOException e)
        {
            throw new ErrorEnArchivoException(1);
        }
    }



    @Override
    public double validarImporte()
    {
        double nuevoSueldo = 0;
        do
        {
            System.out.print("\nIngrese el nuevo sueldo: ");
            nuevoSueldo = teclado.nextDouble();
            if(nuevoSueldo <= 0)
            {
                System.out.println("El sueldo no puede ser un numero negativo. Intente de nuevo");
            }
        }while(nuevoSueldo <= 0);
        return nuevoSueldo;
    }
    @Override
    public int validarDNI()
    {
        int dni = 0;
        do
        {
            System.out.print("\nPor favor ingrese el DNI del Recepcionista: ");
            dni = teclado.nextInt();
            if(dni < 1000000)
            {
                System.out.println("Los DNI tienen que tener como minimo 7 digitos. Intente de nuevo");
            }
        }while(dni < 1000000);
        return dni;
    }
    public Recepcionista existeRecepcionista(int dato, Hotel datos) /** Si no existe lanzar excepcion **/
    {
        Recepcionista encontrado = null;
        for(Persona aux : datos.listaEmpleados)
        {
            if(aux instanceof Recepcionista)
            {
                if(dato == aux.getDni())
                {
                    encontrado = (Recepcionista) aux;
                }
            }
        }
        return encontrado;
    }
    public void agregarRecepcionista(Hotel datos)
    {
        System.out.println("A continuacion ingrese los datos del nuevo empleado: ");
        int dni = validarDNI();
        Recepcionista aux = existeRecepcionista(dni, datos);

        if(aux == null)
        {
            aux = new Recepcionista();
            aux.setDni(dni);

            System.out.println("\nIngrese nombre del Recepcionista: ");
            aux.setNombre(teclado.nextLine());
            teclado.nextLine();

            System.out.println("\nIngrese direccion del Recepcionista: ");
            aux.setDireccion(teclado.nextLine());

            aux.setSueldo(validarImporte());
            teclado.nextLine();

            datos.listaEmpleados.agregar(aux);
        }else
        {
            System.out.println("El Recepcionista que quiso agregar, ya existe.");
        }

    }
}
