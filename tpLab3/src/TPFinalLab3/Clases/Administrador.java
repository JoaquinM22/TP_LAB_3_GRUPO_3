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
        System.out.println("Hola Administrador " + this.getNombre() + ", su sueldo es de $" + this.getSueldo() + " pesos por mes");
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



    /** REALIZA UN BACKUP DE LA LISTA DE REGISTROS, QUE CONTIENE
     * LOS DATOS DE TODAS LAS PERSONAS QUE SE HOSPEDARON **/
    public void hacerBackUp(Hotel dato) throws ErrorEnArchivoException
    {
        try
        {
            FileOutputStream fOutput = new FileOutputStream("registro_hotel.txt");
            ObjectOutputStream objOutput = new ObjectOutputStream(fOutput);
            System.out.println("Realizando backUp...");
            objOutput.writeObject(dato.listaRegistro);
            objOutput.close();
            pausa(1000);
            System.out.println("....");
            pausa(1000);
            System.out.println("....");
            pausa(1000);
            System.out.println("....");
            pausa(1000);
            System.out.println("... backUp realizado correctamente! ...");
        }catch(IOException e)
        {
            throw new ErrorEnArchivoException(1);
        }
    }
    private static ColeccionGenerica<Registro> leerArchivoRegistros() throws ErrorEnArchivoException
    {
        ColeccionGenerica<Registro> listaRegistros = null;
        try
        {
            FileInputStream fInput = new FileInputStream("registro_hotel.txt");
            ObjectInputStream objInput = new ObjectInputStream(fInput);
            System.out.println("Leyendo el Archivo...");
            listaRegistros = (ColeccionGenerica<Registro>) objInput.readObject();
            System.out.println(".... El Archivo se leyo correctamente ....");
        }catch(ClassNotFoundException e)
        {
            System.out.println("Error al buscar la clase");
            e.printStackTrace();
        }catch(IOException e)
        {
            throw new ErrorEnArchivoException(2);
        }

        return listaRegistros;
    }




    @Override
    public double validarImporte()
    {
        double nuevoSueldo;
        do
        {
            System.out.print("\nIngrese el nuevo sueldo: ");
            nuevoSueldo = teclado.nextDouble();
            if(nuevoSueldo <= 0)
            {
                System.out.println("El sueldo no puede ser 0 ni un numero negativo. Intente de nuevo");
            }
        }while(nuevoSueldo <= 0);
        return nuevoSueldo;
    }
    @Override
    public int validarDNI()
    {
        int dni;
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
    public Recepcionista existeRecepcionista(int dato, Hotel datos)
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
    public void modificarRecepcionista(Hotel datos)
    {
        int dni = validarDNI();
        Recepcionista aux = existeRecepcionista(dni, datos);

        if(aux != null)
        {
            System.out.print("\nIngrese nuevo nombre del Recepcionista: ");
            aux.setNombre(teclado.nextLine());
            teclado.nextLine();

            System.out.println("\nIngrese nueva direccion del Recepcionista: ");
            aux.setDireccion(teclado.nextLine());

            aux.setSueldo(validarImporte());
            teclado.nextLine();

            System.out.println("... Los datos se han actualizado correctamente ...");
        }else
        {
            System.out.println("El Recepcionista no existe.");
        }
    }
    public void eliminarRecepcionista(Hotel datos)
    {
        int dni = validarDNI();
        Recepcionista aux = existeRecepcionista(dni, datos);

        if(aux != null)
        {
            System.out.println("A continuacion se muestran los datos del Recepcionista: \n");
            System.out.println(aux);
            System.out.println("Borrando Recepcionista ...");
            pausa(1000);
            System.out.println("....");
            pausa(1000);
            System.out.println("....");
            pausa(1000);
            System.out.println("....");
            pausa(1000);
            //Falta borrar el recepcionista de la lista
            System.out.println("... El Recepcionista a sido borrado correctamente ...");
        }else
        {
            System.out.println("El Recepcionista no existe.");
        }
    }



    /** METODO PAUSA **/
    public static void pausa(int miliseg)
    {
        try
        {
            Thread.sleep(miliseg);
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }



    /** MENU ADMINISTRADOR **/
    public void accionesAdmin(Scanner teclado, int ref, Hotel datos)
    {
        try
        {
            int seleccion = ref;

            switch(seleccion)
            {
                case 1 ->
                {
                    System.out.println("Realizando backUp...");
                    hacerBackUp(datos);
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida.");
                        }
                    }while(seleccion != 0);
                }
                case 2 ->
                {
                    agregarRecepcionista(datos);
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida.");
                        }
                    }while(seleccion != 0);
                }
                case 3 ->
                {
                    modificarRecepcionista(datos);
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida.");
                        }
                    }while(seleccion != 0);
                }
                case 4 ->
                {
                    System.out.println("A continuacion se muestran todas las habitaciones: ");
                    datos.mostrarHabitacionesOrdenado();
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida.");
                        }
                    }while(seleccion != 0);
                }
                case 5 ->
                {
                    System.out.println("A continuacion se muestran todos los clientes: ");
                    datos.mostrarClientes();
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. ");
                        }
                    }while(seleccion != 0);
                }
                case 6 ->
                {
                    System.out.println("A continuacion se muestran todos los Recepcionsitas: ");
                    datos.mostrarRecepcionistas();
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida.");
                        }
                    }while(seleccion != 0);
                }
                case 7 ->
                {
                    consultarSueldo();
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida.");
                        }
                    }while(seleccion != 0);
                }
                case 0 -> System.out.println("\nVolviendo al inicio...");
                default -> System.out.println("Opcion invalida.");
            }
        }catch(ErrorEnArchivoException e)
        {
            throw new RuntimeException();
        }
    }
}
