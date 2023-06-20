package TPFinalLab3.Clases;
import TPFinalLab3.Excepciones.ErrorEnArchivoException;
import TPFinalLab3.Interfaces.CargarDinero;
import TPFinalLab3.Interfaces.FuncionesEmpleados;
import TPFinalLab3.Interfaces.MetodosValidaciones;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Recepcionista extends Persona implements CargarDinero, MetodosValidaciones, FuncionesEmpleados, Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = -739450841937426159L;



    /** ATRIBUTOS **/
    private String password;
    private double sueldo;
    private boolean tienePermiso = false;
    private final static Scanner teclado = new Scanner(System.in);



    /** CONSTRUCTOR **/
    public Recepcionista()
    {
        super();
    }
    public Recepcionista(String nombre, String direccion, int dni, String password, double sueldo)
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
    public void setTienePermiso(boolean tienePermiso)
    {
        this.tienePermiso = tienePermiso;
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
    public boolean isTienePermiso()
    {
        return tienePermiso;
    }



    /** METODOS **/
    @Override
    public String toString()
    {
        return  "\n--------------------" +
                "\nDATOS RECEPCIONISTA" +
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
        System.out.println("Hola Recepcionista " + this.getNombre() + ", tu sueldo es de $" + this.getSueldo() + " pesos por mes");
    }
    public void hacerBackUp(Hotel unHotel) throws ErrorEnArchivoException
    {
        if(this.tienePermiso)
        {
            Administrador admin = unHotel.retornarAdministrador();
            admin.hacerBackUp(unHotel);
        }
    }


    /** METODO BUSQUEDA Y CARGA DE NUEVO CLIENTE **/
    public Cliente existeCliente(int dato, Hotel datos)
    {
        Cliente encontrado = null;
        for(Cliente aux : datos.listaClientes)
        {
            if(dato == aux.getDni())
            {
                encontrado = aux;
            }
        }
        return encontrado;
    }
    public Cliente agregarCliente(Hotel datos)
    {

        int dni = validarDNI();
        Cliente aux = existeCliente(dni, datos);

        if(aux == null)
        {
            aux = new Cliente();
            aux.setDni(dni);

            teclado.nextLine();
            System.out.print("\nPor favor ingrese su nombre: ");
            aux.setNombre(teclado.nextLine());


            System.out.print("\nIngrese su direccion: ");
            aux.setDireccion(teclado.nextLine());

            aux.setSaldo(validarImporte());

            datos.listaClientes.agregar(aux);
        }

        return aux;
    }
    @Override
    public void cargarSaldo(Cliente aux, double precio)
    {
        if(aux.getSaldo() < precio)
        {
            do
            {
                System.out.println("Usted no tiene el saldo suficiente para pagar la habitacion. Cargue saldo a su cuenta a continuacion: ");
                System.out.println("Precio habitacion: $" + precio);
                System.out.println("Su saldo: $" + aux.getSaldo());
                System.out.print("\nIngrese la cantidad a cargar: ");
                aux.setSaldo(aux.getSaldo() + teclado.nextDouble());
            }while(aux.getSaldo() < precio);
        }
    }



    /** CHECK IN Y CHECK OUT **/
    public void checkIn(Hotel datos)
    {
        //checkearReservas(datos);
        /** RETORNA UN CLIENTE SI EXISTE SINO CREA UNO NUEVO **/
        Cliente auxCliente = agregarCliente(datos);

        /** MUESTRA LAS HABITACIONES DISPONIBLES **/
        System.out.println("A continuacion le mostramos las habitaciones disponibles:");
        mostrarHabitacionesDisponibles(datos.listaHabitaciones);

        /** SE QUEDA EN EL BUCLE HASTA QUE RETORNA UNA HABITACION VALIDA **/
        Habitacion existeHab;
        do
        {
            System.out.print("\nIngrese el id de la habitacion que desea: ");
            existeHab = buscarHabitacion(teclado.nextInt(), datos.listaHabitaciones);
            if(existeHab == null)
            {
                System.out.println("El id ingresado no le corresponde a ninguna habitacion. Intente de nuevo");
            }
        }while(existeHab == null);

        System.out.println("Realizando CheckIn ...");

        /** LE CARGA EL CLIENTE A LA HABITACION **/
        existeHab.setOcupante(auxCliente);

        /** LE CARGA LA HABITACION A LA LISTA DEL CLIENTE **/
        auxCliente.agregarOcupada(existeHab);

        /** LE CAMBIA EL ESTADO A "OCUPADO" **/
        existeHab.setEstado(Habitacion.Estado.OCUPADO);

        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println(".... El CheckIn se realizo exitosamene! ....");

        /** MUESTRO LOS DATOS ACTUALIZADOS DE LA HABITACION **/
        System.out.println("A continuacion se muestra los datos de la habitacion:");
        existeHab.datosHabitacion();
        System.out.println("\n\n");
        pausa(3000);
    }
    public void checkOut(Hotel datos)
    {
        /** VALIDA QUE SE INGRESE UN DNI **/
        int dniIngresado = validarDNI();

        /** BUSCA AL CLIENTE POR EL DNI EN EL SISTEMA **/
        Cliente auxCliente = existeCliente(dniIngresado, datos);
        if(auxCliente != null)
        {
            /** VERIFICA QUE EL CLIENTE TENGA HABITACIONES OCUPADAS Y NO RESERVADAS **/
            int dimension = auxCliente.listaOcupadas.tamanio();
            if(dimension > 0)
            {
                /** ELIGE LA HABITACION A LA QUE QUIERE HACER UN CHECKOUT **/
                Habitacion existeHab = validarID(auxCliente, datos);

                /** CON UN RANDOM INGRESA CUANTOS DIAS SE HOSPEDO **/
                int cantDias = (int)(Math.random() * 20 + 1);
                cargarSaldo(auxCliente,existeHab.getPrecio()*cantDias);

                System.out.println("Haciendo ChekOut ...");

                auxCliente.setSaldo(auxCliente.getSaldo() - (existeHab.getPrecio()*cantDias));
                LocalDate fechaSalida = LocalDate.now();
                LocalDate fechaEntrada = fechaSalida.minusDays(cantDias);

                /** CREA UN NUEVO REGISTRO **/
                Registro nuevoReg = new Registro(fechaEntrada,fechaSalida, existeHab, auxCliente, existeHab.getPrecio()*cantDias, cantDias);
                datos.agregarRegistro(nuevoReg);

                /** ELIMINA EL CLIENTE DE LA HABITACION Y VICEVERSA **/
                auxCliente.eliminar(existeHab);
                existeHab.setOcupante(null);

                /** CAMBIA EL ESTADO A DISPONIBLE **/
                existeHab.setEstado(Habitacion.Estado.DISPONIBLE);

                pausa(1000);
                System.out.println("...");
                pausa(1000);
                System.out.println("...");
                pausa(1000);
                System.out.println("...");
                pausa(1000);
                System.out.println("... El CheckOut se realizo con exito! ...");
            }else
            {
                System.out.println("Usted no esta ocupando ninguna habitacion en este hotel");
            }
        }else
        {
            System.out.println("El dni ingresado no corresponde a ningun cliente en nuestro sistema");
        }
    }




    /** METODOS DE VALIDACION **/
    private Habitacion validarID(Cliente aux, Hotel datos)
    {
        Habitacion existeHab;
        int id = 0;
        do
        {
            System.out.println("A continuacion se muestran las Habitaciones que usted actualmente ocupa:");
            aux.mostrarMisHabitaciones();
            System.out.print("\nIngrese el ID de la habitacion que se retira: ");
            int idRetirar = teclado.nextInt();
            existeHab = buscarHabitacionOcupada(datos, aux, idRetirar);
            if(existeHab == null)
            {
                System.out.println("\nLa habitacion buscada no existe. Intente de nuevo");
            }
        }while(existeHab == null);

        return existeHab;
    }
    private Habitacion mostrarHabitacionesReservadasYValidar(Cliente auxCliente, Hotel datos)
    {
        Habitacion existeHab;
        int id = 0;
        do
        {
            System.out.println("A continuacion se muestran las Habitaciones que usted tiene Reservadas:");
            auxCliente.mostrarMisHabitacionesReservadas();
            System.out.print("\nIngrese el ID de la habitacion que desea cancelar la Reserva: ");
            int idCancelar = teclado.nextInt();
            existeHab = buscarHabitacionesReservadas(datos, auxCliente, idCancelar);

            if(existeHab == null)
            {
                System.out.println("\nLa habitacion buscada no existe. Intente de nuevo");
            }
        }while(existeHab == null);

        return existeHab;
    }
    @Override
    public double validarImporte()
    {
        double saldo;
        do{
            System.out.print("\nIngrese el saldo de su cuenta: ");
            saldo = teclado.nextDouble();
            if(saldo < 150000){
                System.out.println("El saldo minimo a cargar es de $150000");
            }
        }while(saldo < 150000);
        return saldo;
    }
    @Override
    public int validarDNI()
    {
        int dni;
        do {
            System.out.print("\nPor favor ingrese su DNI: ");
            dni = teclado.nextInt();


            if(dni < 1000000)
            {
                System.out.println("Los DNI tienen que tener como minimo 7 digitos");
            }

        }while(dni < 1000000);
        return dni;
    }




    /** METODOS HACER RESERVA Y CANCELAR RESERVA **/
    public void hacerReserva(Hotel unHotel)
    {
        Cliente auxCliente = agregarCliente(unHotel);

        System.out.println("Que habitacion desea reservar?");
        System.out.println("Para la reserva debe abonar el 20% del total del precio, y el resto al momento del CheckOut");
        System.out.println("Si cancela la reserva se le devolvera el dinero de la senia");
        mostrarHabitacionesDisponibles(unHotel.listaHabitaciones);
        Habitacion existeHab;
        do
        {
            System.out.print("\nIngrese el id de la habitacion que desea reservar: ");
            existeHab = buscarHabitacion(teclado.nextInt(), unHotel.listaHabitaciones);
            if(existeHab == null)
            {
                System.out.println("El id ingresado no le corresponde a ninguna habitacion. Intente de nuevo");
            }

        }while(existeHab == null);

        existeHab.setOcupante(auxCliente);

        System.out.print("\nCuantos dias va a hospedarse? ");
        int cantDias = teclado.nextInt();

        System.out.print("\nIngrese fecha de ingreso: ");
        System.out.print("\nDia: ");
        int diaIngreso = teclado.nextInt();
        System.out.print("\nMes: ");
        int mesIngreso = teclado.nextInt();
        System.out.print("\nAnio: ");
        int anioIngreso = teclado.nextInt();

        LocalDate inicioReserva = LocalDate.of(anioIngreso, mesIngreso, diaIngreso);
        LocalDate finReserva = inicioReserva.plusDays(cantDias);
        double nuevoPrecio = existeHab.getPrecio()*cantDias;

        seniarHabitacion(auxCliente, nuevoPrecio);

        System.out.println("Cargando Reserva en el sistema ...");

        /** LE CARGA LA HABITACION A LA LISTA DE RESERVAS DEL CLIENTE **/
        auxCliente.agregarReserva(existeHab);

        double senia = nuevoPrecio*0.20;
        auxCliente.setSaldo(auxCliente.getSaldo() - senia);
        existeHab.setEstado(Habitacion.Estado.RESERVADO);
        existeHab.setFechaInicioReserva(inicioReserva);
        existeHab.setFechaFinReserva(finReserva);

        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("... La Reserva se realizo con exito! ...");
        System.out.println("Pago de senia:$" + senia);
        System.out.println("A continuacion se muestran los datos de la Reserva:");
        existeHab.datosReserva();
    }
    public void cancelarReserva(Hotel unHotel)
    {
        int dniIngresado = validarDNI();
        Cliente auxCliente = existeCliente(dniIngresado, unHotel);

        if(auxCliente != null)
        {
            int dimension = auxCliente.listaReservadas.tamanio();
            if(dimension > 0)
            {
                /** ELIGE LA HABITACION A LA QUE QUIERE HACER UN CHECKOUT **/
                Habitacion existeHab = mostrarHabitacionesReservadasYValidar(auxCliente, unHotel);
                System.out.println("Eliminando Reserva del Sistema....");
                int cantDias = (int) ChronoUnit.DAYS.between(existeHab.getFechaFinReserva(), existeHab.getFechaInicioReserva());
                cantDias = cantDias* (-1);
                double senia = (existeHab.getPrecio()*cantDias) *0.20 ;
                Cliente clienteHab = unHotel.listaClientes.obtener(existeHab.getOcupante());
                clienteHab.setSaldo(clienteHab.getSaldo() + senia);

                existeHab.setEstado(Habitacion.Estado.DISPONIBLE);
                existeHab.setOcupante(null);
                existeHab.setFechaInicioReserva(null);
                existeHab.setFechaFinReserva(null);
                pausa(1000);
                System.out.println("...");
                pausa(1000);
                System.out.println("...");
                pausa(1000);
                System.out.println("...");
                pausa(1000);
                System.out.println(".... La cancelacion de la reserva se realizo exitosamente! ....");
                System.out.println("Se le ha devuelto los $" + senia + " de la senia.");
                System.out.println("Datos actalzados de la Habitacion: ");
                System.out.println(existeHab);
                System.out.println("Estado Hab: " + existeHab.getEstado());
                if(existeHab.getOcupante() == null)
                {
                    System.out.println("Ocupante: NULL");
                }else
                {
                    System.out.println(existeHab.getOcupante().toString());
                    System.out.println("Saldo Ocupante Hab: " + existeHab.getOcupante().getSaldo());
                }
            }else
            {
                System.out.println("Usted no esta reservando ninguna habitacion en este hotel");
            }
        }else
        {
            System.out.println("El DNI ingresado no corresponde a ningun cliente en nuestro sistema");
        }
    }

    /** SENIA LA HABITACION A RESERVAR **/
    public void seniarHabitacion(Cliente aux, double precio)
    {
        double nuevoPrecio = precio*0.20;
        if(aux.getSaldo() < nuevoPrecio)
        {
            do
            {
                System.out.println("Usted no tiene el saldo suficiente para pagar la habitacion. Cargue saldo a su cuenta a continuacion: ");
                System.out.println("Precio senia: $" + nuevoPrecio);
                System.out.println("Saldo Actual: $" + aux.getSaldo());
                System.out.print("\nIngrese la cantidad a cargar: ");
                aux.setSaldo(aux.getSaldo() + teclado.nextDouble());
            }while(aux.getSaldo() < nuevoPrecio);
        }
    }

    /** VERIFICA SI HAY HABITACIONES RESERVADAS PARA PONER EN OCUPADAS O DISPONIBLES
     * TAMBIEN REALIZA EL CHECKOUT DE MANERA INTERNA Y AUTOMATICA **/
    public void checkearReservas(Hotel unHotel)
    {
        for(Habitacion auxHab : unHotel.listaHabitaciones)
        {
            if(auxHab.getEstado() == Habitacion.Estado.RESERVADO)
            {
                if(LocalDate.now().isEqual(auxHab.getFechaInicioReserva()) || (LocalDate.now().isAfter(auxHab.getFechaInicioReserva()) && LocalDate.now().isBefore(auxHab.getFechaFinReserva())))
                {
                    /** CAMBIO EL ESTADO DE "RESERVADO" A "OCUPADO" **/
                    auxHab.setEstado(Habitacion.Estado.OCUPADO);
                    /** ELIMINO LA HABITACION DE LA LISTA DE RESERVAS Y LA ANIADO EN LA LISTA DE OCUPADAS **/
                    auxHab.getOcupante().listaReservadas.eliminar(auxHab);
                    /** AGREGO LA HABITACION A LA LISTA DE HABITACIONES OCUPADAS **/
                    auxHab.getOcupante().agregarOcupada(auxHab);
                }
            }else if(auxHab.getFechaFinReserva() != null && auxHab.getFechaInicioReserva() == null)
            {
                if(LocalDate.now().isEqual(auxHab.getFechaFinReserva()) || LocalDate.now().isAfter(auxHab.getFechaFinReserva()))
                {
                    /** SE HACE EL CHEKOUT DE FORMA AUTOMATICA **/
                    int cantDias = (int) ChronoUnit.DAYS.between(auxHab.getFechaFinReserva(), auxHab.getFechaInicioReserva());
                    cantDias = cantDias * (-1);

                    double precioAPagar = (auxHab.getPrecio()*cantDias) - (auxHab.getPrecio()*0.20);
                    auxHab.getOcupante().setSaldo(auxHab.getOcupante().getSaldo() + precioAPagar);
                    auxHab.getOcupante().setSaldo(auxHab.getOcupante().getSaldo() - precioAPagar);

                    /** CARGO UN NUEVO REGISTRO **/
                    Registro nuevoRegistro = new Registro(auxHab.getFechaInicioReserva(), auxHab.getFechaFinReserva(), auxHab, auxHab.getOcupante(), precioAPagar, cantDias);
                    unHotel.agregarRegistro(nuevoRegistro);

                    auxHab.getOcupante().listaOcupadas.eliminar(auxHab);
                    auxHab.setOcupante(null);
                    auxHab.setEstado(Habitacion.Estado.DISPONIBLE);
                    auxHab.setFechaInicioReserva(null);
                    auxHab.setFechaFinReserva(null);
                }
            }
        }
    }




    /** METODOS DE BUSQUEDA DE HABITACIONES **/
    public Habitacion buscarHabitacion(int id, ColeccionGenerica<Habitacion> listaHabitaciones) /** POR ID **/
    {
        Habitacion encontrado = null;

        for(Habitacion aux : listaHabitaciones)
        {
            if(aux.getId() == id)
            {
                encontrado = aux;
            }
        }
        return encontrado;
    }
    public Habitacion buscarHabitacionesReservadas(Hotel unHotel, Cliente cliente, int idHabitacion) /** POR ID HABITACIONES RESERVADAS **/
    {
        Habitacion encontrado = null;
        for(Habitacion auxHab : unHotel.listaHabitaciones)
        {
            if(auxHab.getEstado() == Habitacion.Estado.RESERVADO && auxHab.getOcupante().equals(cliente) && idHabitacion == auxHab.getId())
            {
                encontrado = auxHab;
            }
        }
        return encontrado;
    }
    public Habitacion buscarHabitacionOcupada(Hotel unHotel, Cliente cliente, int idHabitacion) /** POR ID HABITACIONES OCUPADAS **/
    {
        Habitacion encontrado = null;
        for(Habitacion auxHab : unHotel.listaHabitaciones)
        {
            if(auxHab.getEstado() == Habitacion.Estado.OCUPADO && auxHab.getOcupante().equals(cliente) && idHabitacion == auxHab.getId())
            {
                encontrado = auxHab;
            }
        }
        return encontrado;
    }




    /** MOSTRAR HABITACIONES DISPONIBLES Y RESERVADAS **/
    public void mostrarHabitacionesReservadasMismaPersona(Hotel unHotel, int dniIngresado)
    {
        for(Habitacion auxHab : unHotel.listaHabitaciones)
        {
            if(auxHab.getEstado() == Habitacion.Estado.RESERVADO && auxHab.getOcupante().getDni() == dniIngresado)
            {
                System.out.println(auxHab);
            }
        }
    }
    public void mostrarHabitacionesDisponibles(ColeccionGenerica<Habitacion> listaHabitaciones)
    {
        for(Habitacion aux : listaHabitaciones)
        {
            if(aux.getEstado() == Habitacion.Estado.DISPONIBLE)
            {
                System.out.println(aux);
            }
        }
    }




    /** METODOD PAUSA **/
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




    /** MENU RECEPCIONISTA **/
    public void accionesRecepcionista(Scanner teclado, int ref, Hotel datos)
    {
        try
        {
            int seleccion = ref;

            switch(seleccion)
            {
                case 1 -> /** MUESTRA TODAS LAS HABITACIONES DE MANERA SEPARADA POR ESTADO **/
                {
                    System.out.println("\n\nA continuacion se muestran todas las habitaciones: ");
                    datos.mostrarHabitacionesOrdenado();
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.print("\nRealice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 2 -> /** MUESTRA TODOS LOS CLIENTES QUE ESTAN EN EL SISTEMA **/
                {
                    System.out.println("A continuacion se muestran todos los clientes: ");
                    datos.mostrarClientes();
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.print("\nRealice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 3 -> /** CONSULTA EL SUELDO DEL RECEPCIONISTA **/
                {
                    consultarSueldo();
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.print("\nRealice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
                case 4 -> /** REALIZA UN BACKUP DE LOS REGISTROS, SOLO SI TIENE PERMISOS DE ADMINISTRADOR **/
                {
                    if(this.tienePermiso)
                    {
                        hacerBackUp(datos);
                        System.out.println("\n0- Volver");
                        do
                        {
                            System.out.print("\nRealice su eleccion: ");
                            seleccion = teclado.nextInt();
                            teclado.nextLine();
                            if(seleccion != 0)
                            {
                                System.out.println("Opcion invalida. Intente de nuevo");
                            }
                        }while(seleccion != 0);
                    }else
                    {
                        System.out.println("No posee permisos de administrador");
                    }
                }
                case 0 -> System.out.println("\nVolviendo al inicio...");
                default ->
                {
                    System.out.println("Opcion invalida. Intente de nuevo");
                    seleccion = -1;
                }
            }
        }catch(ErrorEnArchivoException e)
        {
            throw new RuntimeException();
        }
    }
}
