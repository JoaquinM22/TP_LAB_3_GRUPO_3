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
        System.out.println("A continuacion se muestran todos los recepcionistas");
        unHotel.mostrarRecepcionistas();
        System.out.print("\nIngrese el dni del Recepcionista al que desea concederle los permisos de administrador: ");
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
                System.out.println("Concediendo permisos...");
                pausa(1000);
                System.out.println("...");
                pausa(1000);
                System.out.println("...");
                pausa(1000);
                System.out.println("... Ahora el Recepecionista posee permisos de Administrador ...");
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

    /** Lee el archivo de registros **/
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



    /** Metodos de validacion **/
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



    /** MODIFICAR Y AGREGAR RECEPCIONISTA Y HABITACION **/
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
        Recepcionista auxRecepcionista = existeRecepcionista(dni, datos);

        if(auxRecepcionista == null)
        {
            auxRecepcionista = new Recepcionista();
            auxRecepcionista.setDni(dni);

            teclado.nextLine();
            System.out.print("\nIngrese nombre del Recepcionista: ");
            auxRecepcionista.setNombre(teclado.nextLine());

            System.out.print("\nIngrese direccion del Recepcionista: ");
            auxRecepcionista.setDireccion(teclado.nextLine());

            System.out.print("\nIngrese Contrasenia Recepcionista: ");
            auxRecepcionista.setPassword(teclado.nextLine());

            auxRecepcionista.setSueldo(validarImporte());

            System.out.println("\nAgregando datos al sistema...");
            pausa(1000);
            System.out.println("...");
            pausa(1000);
            System.out.println("...");
            pausa(1000);
            System.out.println("...");
            pausa(1000);
            datos.listaEmpleados.agregar(auxRecepcionista);
            System.out.println("... LOS DATOS SE GUARDARON CORRECTAMENTE ...");
            System.out.println("Datos Recepcionista Creado:");
            System.out.println(auxRecepcionista);
            pausa(3000);
        }else
        {
            System.out.println("El Recepcionista que quiso agregar, ya existe.");
        }
    }
    public void modificarRecepcionista(Hotel datos)
    {
        int dni = validarDNI();
        Recepcionista auxRecepcionista = existeRecepcionista(dni, datos);

        if(auxRecepcionista != null)
        {
            teclado.nextLine();
            System.out.print("\nIngrese nuevo nombre del Recepcionista: ");
            auxRecepcionista.setNombre(teclado.nextLine());

            System.out.print("\nIngrese nueva direccion del Recepcionista: ");
            auxRecepcionista.setDireccion(teclado.nextLine());

            auxRecepcionista.setSueldo(validarImporte());

            System.out.println("Modificando datos ...");
            pausa(1000);
            System.out.println("...");
            pausa(1000);
            System.out.println("...");
            pausa(1000);
            System.out.println("...");
            pausa(1000);
            System.out.println("... LOS DATOS SE ACTUALIZARON CORRECTAMENTE ...");
            System.out.println("Datos actualizados Recepcionista:");
            System.out.println(auxRecepcionista);
            pausa(3000);
        }else
        {
            System.out.println("El Recepcionista no existe.");
        }
    }
//    public void eliminarRecepcionista(Hotel datos)
//    {
//        int dni = validarDNI();
//        Recepcionista aux = existeRecepcionista(dni, datos);
//
//        if(aux != null)
//        {
//            System.out.println("A continuacion se muestran los datos del Recepcionista: \n");
//            System.out.println(aux);
//            //Falta que elimine el recepcionista
//            System.out.println("Borrando Recepcionista ...");
//            pausa(1000);
//            System.out.println("....");
//            pausa(1000);
//            System.out.println("....");
//            pausa(1000);
//            System.out.println("....");
//            pausa(1000);
//            System.out.println("... El Recepcionista a sido borrado correctamente ...");
//        }else
//        {
//            System.out.println("El Recepcionista no existe.");
//        }
//    }
    public void modificarHabitacion(Hotel datos)
    {
        Recepcionista auxRecepcionista = datos.retornarRecepcionista();
        System.out.println("A continuacion se muestran todas las habitaciones: ");
        datos.mostrarHabitacionesOrdenado();
        int idIngresado;
        Habitacion existeHab;
        do
        {
            System.out.print("\nIngrese el ID de la habitacion que desea modificar: ");
            idIngresado = teclado.nextInt();
            existeHab = auxRecepcionista.buscarHabitacion(idIngresado, datos.listaHabitaciones);
            if(existeHab == null)
            {
                System.out.println("ID invalido. Intente de nuevo");
            }
        }while(existeHab == null);

        System.out.println("A continuacion ingrese los nuevos datos de la Habitacion: ");

        System.out.print("\nIngrese nuevo precio de la habitacion: ");
        existeHab.setPrecio(teclado.nextDouble());
        teclado.nextLine();

        System.out.print("\nIngrese nueva descripcion de la habitacion: ");
        existeHab.setDescripcion(teclado.nextLine());

        System.out.println("\nIngrese nuevo Estado de la habitacion: ");
        System.out.println("\nDisponible: 1  -  Ocupada: 2  - Mantenimiento: 3");
        int num;
        do
        {
            System.out.print("\nIngrese numero: ");
            num = teclado.nextInt();
            if(num > 3 || num <1)
            {
                System.out.println("Numero invalido. Intente de nuevo");
            }
        }while(num > 3 || num <1);

        if(num == 1)
        {
            existeHab.setEstado(Habitacion.Estado.DISPONIBLE);
        }else if(num == 2)
        {
            existeHab.setEstado(Habitacion.Estado.OCUPADO);
        }else
        {
            existeHab.setEstado(Habitacion.Estado.MANTENIMIENTO);
        }

        System.out.println("Modificando datos ...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("... LOS DATOS SE ACTUALIZARON CORRECTAMENTE ...");
        System.out.println("Datos actualizados Habitacion: ");
        System.out.println(existeHab);
        pausa(2000);
    }
    public void agregarHabitacion(Hotel datos)
    {
        System.out.println("A continuacion se muestran todas las habitaciones: ");
        datos.mostrarHabitacionesOrdenado();

        System.out.println("\nIngrese los datos de la nueva Habitacion: ");
        Recepcionista auxRecepcionista = datos.retornarRecepcionista();

        int idIngresado;
        Habitacion nuevaHabitacion;
        do
        {
            System.out.print("\nIngrese el ID de la nueva habitacion: ");
            idIngresado = teclado.nextInt();
            nuevaHabitacion = auxRecepcionista.buscarHabitacion(idIngresado, datos.listaHabitaciones);
            if(nuevaHabitacion != null)
            {
                System.out.println("El ID de la Habitacion ya existe. Intente de nuevo");
            }
        }while(nuevaHabitacion != null);

        nuevaHabitacion = new Habitacion();
        nuevaHabitacion.setId(idIngresado);

        System.out.print("\nIngrese precio nueva Habitacion: ");
        nuevaHabitacion.setPrecio(teclado.nextDouble());
        teclado.nextLine();

        nuevaHabitacion.setEstado(Habitacion.Estado.DISPONIBLE);

        System.out.print("\nIngrese descripcion nueva Habitacion: ");
        nuevaHabitacion.setDescripcion(teclado.nextLine());

        nuevaHabitacion.setOcupante(null);
        nuevaHabitacion.setFechaInicioReserva(null);
        nuevaHabitacion.setFechaFinReserva(null);
        System.out.println("\nAgregando datos al sistema...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        datos.listaHabitaciones.agregar(nuevaHabitacion);
        System.out.println("... LOS DATOS SE GUARDARON CORRECTAMENTE ...");
        System.out.println("Datos Habitacion Creada:");
        System.out.println(nuevaHabitacion);
        pausa(3000);
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
    /** METODO LIMPIAR PANTALLA **/
    public static void limpiarPantalla()
    {
        for(int i=0; i<100; i++)
        {
            System.out.println("\n\n\n\n\n\n\n");
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
                case 1 -> /** AGREGAR NUEVO RECEPCIONISTA **/
                {
                    agregarRecepcionista(datos);
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 2 -> /** MODIFICAR DATOS RECEPCIONISTA **/
                {
                    modificarRecepcionista(datos);
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 3 -> /** MUESTAR TODOS LOS RECEPCIONISTAS DEL SISTEMA **/
                {
                    System.out.println("A continuacion se muestran todos los Recepcionsitas: ");
                    datos.mostrarRecepcionistas();
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 4 -> /** MUESTRA TODOS LOS CLIENTES DEL SISTEMA **/
                {
                    System.out.println("A continuacion se muestran todos los clientes: ");
                    datos.mostrarClientes();
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 5 -> /** MUESTRA TODAS LAS HABITACIONES **/
                {
                    System.out.println("A continuacion se muestran todas las habitaciones: ");
                    datos.mostrarHabitacionesOrdenado();
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 6 -> /** MODIFICAR HABITACION **/
                {
                    modificarHabitacion(datos);
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 7 -> /** AGREGAR HABITACION **/
                {
                    agregarHabitacion(datos);
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 8 -> /** CONSULTA EL SUELDO DEL ADMINISTRADOR **/
                {
                    consultarSueldo();
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 9 -> /** VER LISTA DE REGISTROS **/
                {
                    System.out.println("A continuacion se muestra la lista de Registros");
                    datos.mostrarRegistro();
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 10 -> /** HACER BACKUP DE LA LISTA DE REGISTROS **/
                {
                    hacerBackUp(datos);
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 11 -> /** LEER ARCHIVO REGISTROS **/
                {
                    ColeccionGenerica<Registro> listaRetornadaRegistros = leerArchivoRegistros();
                    listaRetornadaRegistros.listar();
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 12 -> /** CONCEDER PERMISOS A RECEPCIONISTA **/
                {
                    concederPermisos(datos);
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 13 -> /** MOSTRAR DATOS HOTEL **/
                {
                    datos.datosHotel();
                    do
                    {
                        System.out.print("\nOprima 0 para volver: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 0 -> System.out.println("\nVolviendo al inicio...");
                default -> System.out.println("Opcion invalida. Intente de nuevo");
            }
        }catch(ErrorEnArchivoException e)
        {
            throw new RuntimeException();
        }
    }
}
