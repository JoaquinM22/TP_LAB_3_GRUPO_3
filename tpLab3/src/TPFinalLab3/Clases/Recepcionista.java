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
        System.out.println("Hola Recepcionista" + this.getNombre() + ", tu sueldo es de $" + this.getSueldo() + " pesos por mes");
    }
    public void hacerBackUp(Hotel unHotel) throws ErrorEnArchivoException
    {
        if(this.tienePermiso)
        {
            Administrador admin = unHotel.retornarAdministrador();
            admin.hacerBackUp(unHotel);
        }
    }



    public Cliente existeCliente(int dato, Hotel datos) /** Si no existe lanzar excepcion **/
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

            System.out.println("\nPor favor ingrese su nombre: ");
            aux.setNombre(teclado.nextLine());
            teclado.nextLine();

            System.out.println("\nIngrese su direccion: ");
            aux.setDireccion(teclado.nextLine());

            aux.setSaldo(validarImporte());
            teclado.nextLine();

            datos.listaClientes.agregar(aux);
        }

        return aux;
    }
    @Override
    public double validarImporte()
    {
        double saldo = 0;
        do{
            System.out.println("\nIngrese el saldo de su cuenta: ");
            saldo = teclado.nextDouble();
            if(saldo < 5000){
                System.out.println("El saldo minimo a cargar es de $5000");
            }
        }while(saldo < 5000);
        return saldo;
    }
    @Override
    public int validarDNI()
    {
        int dni = 0;
        do {
            System.out.println("\nPor favor ingrese su DNI: ");
            dni = teclado.nextInt();
            if(dni < 1000000){
                System.out.println("Los DNI tienen que tener como minimo 7 digitos");
            }
        }while(dni < 1000000);
        return dni;
    }



    public void mostrarHabitacionesDisponibles(ColeccionGenerica<Habitacion> listaHabitaciones)
    {
        for(Habitacion aux : listaHabitaciones)
        {
            if(aux.getEstado() == Habitacion.Estado.DISPONIBLE)
            {
                System.out.println(aux.toString());
            }
        }
    }
    private Habitacion buscarHabitacion(int id, ColeccionGenerica<Habitacion> listaHabitaciones)
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
    @Override
    public void cargarSaldo(Cliente aux, double precio)
    {
        if(aux.getSaldo() < precio)
        {
            do
            {
                System.out.println("Usted no tiene el saldo suficiente para pagar la habitacion. Cargue saldo a su cuenta a continuacion: ");
                System.out.println("Precio habitacion: $" + precio);
                System.out.println("Ingrese la cantidad a cargar: ");
                aux.setSaldo(aux.getSaldo() + teclado.nextDouble());
            }while(aux.getSaldo() < precio);
        }
    }
    public void checkIn(Hotel datos)
    {
        checkearReservas(datos);
        Cliente aux = agregarCliente(datos);
        System.out.println("A continuacion le mostramos las habitaciones disponibles:");
        mostrarHabitacionesDisponibles(datos.listaHabitaciones);
        Habitacion existeHab;
        do
        {
            System.out.println("Ingrese el id de la habitacion que desea: ");
            existeHab = buscarHabitacion(teclado.nextInt(), datos.listaHabitaciones);
            if(existeHab == null)
            {
                System.out.println("El id ingresado no le corresponde a ninguna habitacion. Intente de nuevo");
            }

        }while(existeHab == null);

        existeHab.setOcupante(aux);

        System.out.println("Cuantos dias va a hospedarse? ");
        int cantDias = teclado.nextInt();

        LocalDate fechaEntrada = LocalDate.now(); /** SE UTILIZAN PARA EL REGISTRO **/
        LocalDate fechaSalida = fechaEntrada.plusDays(cantDias); /** SE UTILIZAN PARA EL REGISTRO **/

        cargarSaldo(aux, (existeHab.getPrecio()*cantDias));

        aux.setSaldo(aux.getSaldo() - (existeHab.getPrecio()*cantDias));
        existeHab.setEstado(Habitacion.Estado.OCUPADO);
        /** METODO PARA CARGAR REGISTRO **/

        Registro reg = new Registro(fechaEntrada,null, existeHab, aux, existeHab.getPrecio()*cantDias);
        datos.agregarRegistro(reg);

        /** CARGA HABITACION ELEGIDA EN LISTAOCUPADAS DE CLIENTE **/
        aux.agregar(existeHab);


        System.out.println(".... El CheckIn se realizo exitosamene! ....");
        System.out.println("A continuacion se muestra los datos de la habitacion:");
        existeHab.datosHabitacion();
        System.out.println("\n\n");
    }
    

    public void checkOut(Hotel datos)
    {
        /**
         * CAMBIA EL ESTADO DE LA HABITACION
         * A DISPONIBLE O EN MANTENIMIENTO Y QUITA LA PERSONA DE ELLA
         **/

        int dniIngresado = validarDNI();
        Cliente aux = existeCliente(dniIngresado,datos);
        if(aux != null)
        {
            boolean tiene = validarOcupadas(aux);
            if(tiene == true)
            {
                Habitacion existeHab = validarID(aux,datos);

                Registro existeReg = datos.buscarRegistro(dniIngresado);

                existeReg.setFechaSalida(LocalDate.now());
                existeReg.setOcupante(null);
                existeReg.getOcupada().setEstado(Habitacion.Estado.DISPONIBLE);
                aux.eliminar(existeHab);
                System.out.println("CheckOut realizado con exito");
            }
            else {
                System.out.println("Usted no esta ocupando ninguna habitacion en este hotel");
            }
        }
        else{
            System.out.println("El dni ingresado no corresponde a ningun cliente en nuestro sistema");
        }
    }

    private Habitacion validarID(Cliente aux, Hotel datos)
    {
        Habitacion existeHab = null;
        int id = 0;
        do{
            System.out.println("Habitaciones que usted actualmente ocupa");
            mostrarOcupadasCliente(aux, datos.listaHabitaciones);
            System.out.println("Ingrese el ID de la habitacion que se retira");
            int idRetirar = teclado.nextInt();
            existeHab = buscarHabitacionOcupada(datos,aux,idRetirar);
            if(existeHab == null)
            {
                System.out.println("\nLa habitacion buscada no existe.");
            }
        }while(existeHab == null);

        return existeHab;
    }

    private boolean validarOcupadas(Cliente aux)
    {
        boolean resp = false;
        if(aux.tamanio() > 0)
        {
            resp = true;
        }
        return resp;
    }

    public void mostrarOcupadasCliente(Cliente aux, ColeccionGenerica<Habitacion> listaHabitaciones)
    {
        for(Habitacion hab : listaHabitaciones)
        {
            if(aux.equals(hab.getOcupante()))
            {
                System.out.println(hab.toString());
            }
        }
    }

    private void seniarHabitacion(Cliente aux, double precio)
    {
        double nuevoPrecio = precio*0.20;
        if(aux.getSaldo() < nuevoPrecio)
        {
            do
            {
                System.out.println("Usted no tiene el saldo suficiente para pagar la habitacion. Cargue saldo a su cuenta a continuacion: ");
                System.out.println("Precio senia: $" + nuevoPrecio);
                System.out.println("Saldo Actual: $" + aux.getSaldo());
                System.out.println("Ingrese la cantidad a cargar: ");
                aux.setSaldo(aux.getSaldo() + teclado.nextDouble());
            }while(aux.getSaldo() < nuevoPrecio);
        }
    }
    public void checkearReservas(Hotel unHotel)
    {
        for(Habitacion aux : unHotel.listaHabitaciones)
        {
            if(aux.getEstado() == Habitacion.Estado.RESERVADO)
            {
                if(LocalDate.now().isEqual(aux.getFechaInicioReserva()) || (LocalDate.now().isAfter(aux.getFechaInicioReserva()) && LocalDate.now().isBefore(aux.getFechaFinReserva())))
                {
                    Cliente clienteAux = aux.getOcupante();
                    int cantDias = (int) ChronoUnit.DAYS.between(aux.getFechaFinReserva(), aux.getFechaInicioReserva());
                    cantDias = cantDias * (-1);
                    double precioAPagar = (aux.getPrecio()*cantDias) - (aux.getPrecio()*0.20);

                    if(clienteAux.getSaldo() < precioAPagar)
                    {
                        clienteAux.setSaldo(clienteAux.getSaldo() + precioAPagar);
                        clienteAux.setSaldo(clienteAux.getSaldo() - precioAPagar);
                    }
                    aux.setEstado(Habitacion.Estado.OCUPADO);

                    /** METODO PARA CARGAR REGISTRO **/

                    Registro reg = new Registro(aux.getFechaInicioReserva(), null, aux, clienteAux, precioAPagar);
                    unHotel.agregarRegistro(reg);
                    aux.setFechaInicioReserva(null);

                    /** FALTA CARGAR HABITACION ELEGIDA EN LISTAOCUPADAS DE CLIENTE **/

                }
            }else if(aux.getFechaFinReserva() != null)
            {
                if(LocalDate.now().isEqual(aux.getFechaFinReserva()))
                {
                    /** checkout **/
                    checkOut(unHotel);

                    /** SI CONSUMIO CAMBIAR A MANTENIMIEENTO **/
                    /** Registro **/
                }else if(LocalDate.now().isAfter(aux.getFechaFinReserva()))
                {
                    /** CARGAR REGISTRO **/
                    //Que tengo que cargar exactamente??
                }
            }
        }
    }
    public Habitacion buscarHabitacionesReservadas(Hotel unHotel, int dniIngresado)
    {
        Habitacion encontrado = null;
        for(Habitacion auxHab : unHotel.listaHabitaciones)
        {
            if(auxHab.getEstado() == Habitacion.Estado.RESERVADO && auxHab.getOcupante().getDni() == dniIngresado)
            {
                encontrado = auxHab;
            }
        }
        return encontrado;
    }
    public void hacerReserva(Hotel unHotel)
    {
        Cliente aux = agregarCliente(unHotel);

        System.out.println("Que habitacion desea reservar?");
        System.out.println("Para la reserva debe abonar el 20% del total del precio, y el resto al momento del CheckIn");
        System.out.println("Si cancela la reserva se le devolvera el dinero");
        mostrarHabitacionesDisponibles(unHotel.listaHabitaciones);
        Habitacion existeHab;
        do
        {
            System.out.println("Ingrese el id de la habitacion que desea reservar: ");
            existeHab = buscarHabitacion(teclado.nextInt(), unHotel.listaHabitaciones);
            if(existeHab == null)
            {
                System.out.println("El id ingresado no le corresponde a ninguna habitacion. Intente de nuevo");
            }

        }while(existeHab == null);

        existeHab.setOcupante(aux);

        System.out.println("Cuantos dias va a hospedarse? ");
        int cantDias = teclado.nextInt();

        System.out.println("Ingrese fecha de ingreso: ");
        System.out.print("\nDia: ");
        int diaIngreso = teclado.nextInt();
        System.out.print("\nMes: ");
        int mesIngreso = teclado.nextInt();
        System.out.print("\nAnio: ");
        int anioIngreso = teclado.nextInt();
        LocalDate inicioReserva = LocalDate.of(anioIngreso, mesIngreso, diaIngreso);
        LocalDate finReserva = inicioReserva.plusDays(cantDias);
        double nuevoPrecio = existeHab.getPrecio()*cantDias;

        seniarHabitacion(aux, nuevoPrecio);

        double senia = nuevoPrecio*0.20;
        aux.setSaldo(aux.getSaldo() - senia);
        existeHab.setEstado(Habitacion.Estado.RESERVADO);
        existeHab.setFechaInicioReserva(inicioReserva);
        existeHab.setFechaFinReserva(finReserva);

        System.out.println(".... La Reserva se realizo exitosamene! ....");
        System.out.println("A continuacion se muestran los datos de la Reserva:");
        existeHab.datosReserva();
        /** FALTA METODO PARA CARGAR REGISTRO **/
        //Ambas fechas en null porque no se realizo el checkIn
        Registro reg = new Registro(null, null, existeHab, aux, senia);
        unHotel.agregarRegistro(reg);

    }

    public Habitacion buscarHabitacionOcupada(Hotel unHotel, Cliente cliente, int idHabitacion)
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
    public void cancelarReserva(Hotel unHotel)
    {
        System.out.println("Ingrese dni: ");
        int dniIngresado = teclado.nextInt();
        Habitacion existeHab = buscarHabitacionesReservadas(unHotel, dniIngresado);
        if(existeHab == null)
        {
            System.out.println("No hay ninguna habitacion reservada a su nombre.");
        }else
        {
            existeHab.datosReserva();
            int cantDias = (int) ChronoUnit.DAYS.between(existeHab.getFechaFinReserva(), existeHab.getFechaInicioReserva());
            cantDias = cantDias* (-1);
            double senia = (existeHab.getPrecio()*cantDias) *0.20 ;
            Cliente clienteHab = unHotel.listaClientes.obtener(existeHab.getOcupante());
            clienteHab.setSaldo(clienteHab.getSaldo() + senia);
            System.out.println("Se le ha devuelto los $" + senia + " de la senia.");

            existeHab.setEstado(Habitacion.Estado.DISPONIBLE);
            existeHab.setOcupante(null);
            existeHab.setFechaInicioReserva(null);
            existeHab.setFechaFinReserva(null);
            System.out.println(".... La cancelacion de la reserva se hizo exitosamente! ....");
            System.out.println(existeHab.toString());
            System.out.println("Estado Hab: " + existeHab.getEstado());
            if(existeHab.getOcupante() == null)
            {
                System.out.println("Ocupante: NULL");
            }else
            {
                System.out.println(existeHab.getOcupante().toString());
                System.out.println("Saldo Ocupante Hab: " + existeHab.getOcupante().getSaldo());
            }

        }
    }



    public void verHabitaciones(Hotel datos) /** MIRA TODAS LAS HABITACIONES JUNTO A SUS ESTADOS **/
    {
        datos.mostrarHabitaciones();
    }

}
