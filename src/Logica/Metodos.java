/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.ObjHabitacion;
import Datos.ObjCliente;
import Datos.ObjReservacion;
import Datos.Estructuras;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *    @author Jeffry SM
 */

//-- Clase, es la maneja la lógica del uso de las listas y objetos
public class Metodos {

    Scanner leer = new Scanner(System.in);//Variable Global
    Estructuras Almacen = new Estructuras();
    
    public Metodos() {
    }

    //-- Metodos de Trabajo
    
    // ------------------------------------------
    // MÉTODOS GENERALES
    // ------------------------------------------
    
    public void nuevosArchivos() {
        
        Almacen.crearArchivo("Habitaciones");
        Almacen.crearArchivo("Clientes");
        Almacen.crearArchivo("Empleados");
        Almacen.crearArchivo("Reservas");
        
    }
    
    public void cargarListas() {
        // Aqui vamos a poner los métodos que cargan en cada lista
        Almacen.leerArchivoHabitaciones();
        Almacen.leerArchivoClientes();
       
    }
    
    //--------------------------------------------
    // Módulo de Habitaciones
    //--------------------------------------------
    
    public int siguienteHabitacion() {
        int resultado = 1;
        ArrayList<ObjHabitacion> misHabitaciones = Almacen.listarHabitaciones();
        for (int i = 0; i < misHabitaciones.size(); i++){
            if (resultado <= misHabitaciones.get(i).getId()){
                resultado = misHabitaciones.get(i).getId() + 1;
            }
        }
        return resultado;
    }
    
    public int buscarHabitacionActivaPorId(int id){
        ArrayList<ObjHabitacion> misHabitaciones = Almacen.listarHabitaciones();
        for (int i = 0; i < misHabitaciones.size(); i++){
            if (misHabitaciones.get(i).getId() == id 
                    && misHabitaciones.get(i).getEstado() == 1){
                return i;
            }
        }
        return -1;
    }
    
    public ObjHabitacion obtenerHabitacion(int indice){
        return Almacen.listarHabitaciones().get(indice);
    }
    
    public int buscarHabitacion(){
        System.out.println("---------------------------------------");
        System.out.println("|         BUSCAR  HABITACION          |");
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println("Digite el identificador de la habitacion: ");
        int id = leer.nextInt();
        int indice = -1;

        ArrayList<ObjHabitacion> misHabitaciones = Almacen.listarHabitaciones();
        for (int i = 0; i < misHabitaciones.size(); i++){
            if (misHabitaciones.get(i).getId() == id){
                indice = i;
                break;
            }
        }
        return indice;
    }
    
    public void insertarHabitacion() {
        ObjHabitacion nuevaHabitacion = new ObjHabitacion();
        System.out.println("---------------------------------------");
        System.out.println("|       REGISTRAR HABITACION          |");
        System.out.println("---------------------------------------");
        System.out.println("");

        int id = siguienteHabitacion();
        System.out.println("Identificador: " + id);
        nuevaHabitacion.setId(id);
        leer.nextLine();

        System.out.println("Digite el tipo de Habitacion: ");
        String tipHab = leer.nextLine();
        nuevaHabitacion.setTipoHabitacion(tipHab);

        System.out.println("Digite el nombre del Edificio: ");
        String edificio = leer.nextLine();
        nuevaHabitacion.setEdificio(edificio);

        System.out.println("Digite el numero de piso: ");
        int piso = leer.nextInt();
        nuevaHabitacion.setPiso(piso);

        System.out.println("Digite el costo por noche de la habitacion: ");
        double costo = leer.nextDouble();
        nuevaHabitacion.setCostoPorNoche(costo);

        nuevaHabitacion.setEstado(1);

        Almacen.agregarHabitacion(nuevaHabitacion);
        Almacen.escribeArchivoHabitaciones();
    }
    
    public void modificarHabitacion(){
        int indice = buscarHabitacion();
        if (indice == -1){
            System.out.println("No se encontro la habitacion ");
        } else {
            ArrayList<ObjHabitacion> misHabitaciones = Almacen.listarHabitaciones();
            ObjHabitacion habitacion = misHabitaciones.get(indice);

            leer.nextLine();
            System.out.println("Digite el nuevo tipo de Habitacion: ");
            String tipHab = leer.nextLine();
            habitacion.setTipoHabitacion(tipHab);

            System.out.println("Digite el nuevo nombre del Edificio: ");
            String edificio = leer.nextLine();
            habitacion.setEdificio(edificio);

            System.out.println("Digite el nuevo numero de piso: ");
            int piso = leer.nextInt();
            habitacion.setPiso(piso);

            System.out.println("Digite el nuevo costo por noche: ");
            double costo = leer.nextDouble();
            habitacion.setCostoPorNoche(costo);

            Almacen.editarHabitacion(indice, habitacion);
            Almacen.escribeArchivoHabitaciones();
        }
    }
    
    public void borrarHabitacion(){
        int indice = buscarHabitacion();
        if (indice == -1){
            System.out.println("No se encontro la habitacion ");
        } else {
            ArrayList<ObjHabitacion> misHabitaciones = Almacen.listarHabitaciones();
            ObjHabitacion habitacion = misHabitaciones.get(indice);

            habitacion.setEstado(0);

            Almacen.editarHabitacion(indice, habitacion);
            Almacen.escribeArchivoHabitaciones();
        }
    }
    
    public void mostrarHabitaciones(){
        System.out.println("---------------------------------------");
        System.out.println("|       LISTADO DE HABITACIONES       |");
        System.out.println("---------------------------------------");
        System.out.println("");

        ArrayList<ObjHabitacion> misHabitaciones = Almacen.listarHabitaciones();
        for (int i = 0; i < misHabitaciones.size(); i++){
            ObjHabitacion habitacion = misHabitaciones.get(i);
            if (habitacion.getEstado() == 1){
                System.out.println("Identificador: "   + habitacion.getId());
                System.out.println("Tipo: "            + habitacion.getTipoHabitacion());
                System.out.println("Edificio: "        + habitacion.getEdificio());
                System.out.println("Piso: "            + habitacion.getPiso());
                System.out.println("Costo por Noche: " + habitacion.getCostoPorNoche());
                System.out.println("");
                System.out.println("---------------------------------------");
            }
        }
    }
    
    //--------------------------------------------
    // Módulo de Clientes
    //--------------------------------------------
    
    public int buscarClienteActivoPorCedula(String cedula){
        ArrayList<ObjCliente> misClientes = Almacen.listarClientes();
        for (int i = 0; i < misClientes.size(); i++){
            if (misClientes.get(i).getCedula().equals(cedula) 
                    && misClientes.get(i).getEstado() == 1){
                return i;
            }
        }
        return -1;
    }
    
    public ObjCliente obtenerCliente(int indice){
        return Almacen.listarClientes().get(indice);
    }
    
    public int buscarCliente() {
        System.out.println("---------------------------------------");
        System.out.println("|          BUSCAR  CLIENTE            |");
        System.out.println("---------------------------------------");
        System.out.println(""); 
        System.out.println("Digite la cedula del cliente: ");
        //leer.nextLine();
        String cedula = leer.nextLine();
        int indice    = -1;
        
        //--Nueva lista para trabajar localmente (clientes)
        ArrayList<ObjCliente> misClientes = new ArrayList<>();
        //-- Llenamos esta lista con una copia de la original
        misClientes = Almacen.listarClientes();
        //--Recorrer la copia de la lista, para extraer los objetos
        for(int i = 0; i < misClientes.size();i++){
            ObjCliente cliente = new ObjCliente();
            cliente = misClientes.get(i);
            if (cliente.getCedula().equals(cedula)){
                indice = i;
                break;
            }
            
        }
        return indice;
    }
 
    public void insertarClientes(){
        //-- variable, instancia del objeto a usar
        ObjCliente nuevoCliente = new ObjCliente();
        System.out.println("---------------------------------------");
        System.out.println("|         REGISTRAR CLIENTES          |");
        System.out.println("---------------------------------------");
        System.out.println("");
        
        System.out.println("Digite el Identificador de cliente: ");
        int id = leer.nextInt();
        nuevoCliente.setId(id);
        
        leer.nextLine();
        System.out.println("Digite la Cedula: ");
        String cedula = leer.nextLine();
        nuevoCliente.setCedula(cedula);
        
        System.out.println("Digite el Nombre: ");
        String nombre = leer.nextLine();
        nuevoCliente.setNombre(nombre);
        
        System.out.println("Digite el Primer Apellido: ");
        String apellido1 = leer.nextLine();
        nuevoCliente.setApellido1(apellido1);
        
        System.out.println("Digite el Segundo Apellido: ");
        String apellido2 = leer.nextLine();
        nuevoCliente.setApellido2(apellido2);
        
        System.out.println("Digite el Telefono: ");
        String telefono = leer.nextLine();
        nuevoCliente.setTelefono(telefono);
        
        System.out.println("Digite el Correo: ");
        String correo = leer.nextLine();
        nuevoCliente.setCorreo(correo);
        
        System.out.println("Digite la Direccion: ");
        String direccion = leer.nextLine();
        nuevoCliente.setDireccion(direccion);
        
        int estado = 1;        
        nuevoCliente.setEstado(estado);
        
        Almacen.agregarCliente(nuevoCliente);
        Almacen.escribeArchivoClientes();
    }
    
    public void modificarCliente(){
        int indice = buscarCliente();
        if (indice == -1){
            System.out.println("No se encontro el cliente ");
        } else {
            ArrayList<ObjCliente> misClientes = new ArrayList<>();
            misClientes = Almacen.listarClientes();
            System.out.println("Cedula: " + 
                                misClientes.get(indice).getCedula()  );
            System.out.println("Nombre: " + 
                                misClientes.get(indice).getApellido1() + " " +
                                misClientes.get(indice).getNombre());
            
            ObjCliente cliente = new ObjCliente();
            cliente = misClientes.get(indice);
            
            System.out.println("Digite el Nuevo Telefono: ");
            String telefono = leer.nextLine();
            cliente.setTelefono(telefono);

            System.out.println("Digite el Nuevo Correo: ");
            String correo = leer.nextLine();
            cliente.setCorreo(correo);

            System.out.println("Digite la Nueva Direccion: ");
            String direccion = leer.nextLine();
            cliente.setDireccion(direccion);   
            
            Almacen.editarCliente(indice, cliente);
            Almacen.escribeArchivoClientes();
        }
    }
    
    
    public void borrarCliente(){
        int indice = buscarCliente();
        if (indice == -1){
            System.out.println("No se encontro el cliente ");
        } else {
            ArrayList<ObjCliente> misClientes = new ArrayList<>();
            misClientes = Almacen.listarClientes();
            System.out.println("Cedula: " + 
                                misClientes.get(indice).getCedula()  );
            System.out.println("Nombre: " + 
                                misClientes.get(indice).getApellido1() + " " +
                                misClientes.get(indice).getNombre());
            Almacen.quitarCliente(indice);
            Almacen.escribeArchivoClientes();
                                   // Procedencia, Mensaje o información
            JOptionPane.showMessageDialog(null, "Cliente Borrado",
                    "Atención", JOptionPane.INFORMATION_MESSAGE);
                              // Titulo      Icono
     }
   }   
    public void mostrarClientes(){
        System.out.println("---------------------------------------");
        System.out.println("|          LISTAR  CLIENTES           |");
        System.out.println("---------------------------------------");
        System.out.println("");        
        
        //--Nueva lista para trabajar localmente (clientes)
        ArrayList<ObjCliente> misClientes = new ArrayList<>();
        //-- Llenamos esta lista con una copia de la original
        misClientes = Almacen.listarClientes();
        //--Recorrer la copia de la lista, para extraer los objetos
        for(int i = 0; i < misClientes.size();i++){
            ObjCliente cliente = new ObjCliente();
            cliente = misClientes.get(i);
            
            System.out.println("Id Cliente: " + cliente.getId() );
            System.out.println("Nombre: " + cliente.getApellido1() +
                               " " + cliente.getNombre());
            System.out.println("Cedula " + cliente.getCedula());
            System.out.println("Telefono: " + cliente.getTelefono() );
            System.out.println("");
            System.out.println("---------------------------------------");
        }
    }
    
    //--------------------------------------------
    // Módulo de Reservaciones
    //--------------------------------------------
    
    //--Método para obtener la siguiente Reservación
    public int siguienteReservacion() {
        int resultado = 1;
        ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();
        for (int i = 0; i < misReservaciones.size(); i++) {
            if (resultado <= misReservaciones.get(i).getId()) {
                resultado = misReservaciones.get(i).getId() + 1;
            }
        }
        return resultado;
    }  
    
    public int buscarReservacion() {
        System.out.println("---------------------------------------");
        System.out.println("|        BUSCAR  RESERVACION          |");
        System.out.println("---------------------------------------");
        System.out.println(""); 
        System.out.println("Digite el id de la reservacion: ");
        //leer.nextLine();
        int id = leer.nextInt();
        int indice    = -1;
        

        ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();
 
        for(int i = 0; i < misReservaciones.size();i++){
            ObjReservacion reservacion = misReservaciones.get(i);
            if (reservacion.getId() == id){
                indice = i;
                break;
            }
            
        }
        return indice;
    }
    
    
    //-- Método para insertar Reservaciones
    public void insertarReservacion() {
        
        ObjReservacion nuevaReservacion = new ObjReservacion();
        
        System.out.println("---------------------------------------");
        System.out.println("|      REGISTRO DE RESERVACIONES      |");
        System.out.println("---------------------------------------");
        System.out.println("");
        
        int id = siguienteReservacion();
        System.out.println("Identificador de la reservacion " + id);
        nuevaReservacion.setId(id);
        leer.nextLine();
        
        // Id de la habitacion
        int idHabitacion;
        int indiceHabitacion;
        do {
            System.out.println("");
            System.out.println("Digite el id de la habitacion: ");
            idHabitacion = leer.nextInt();
            indiceHabitacion = buscarHabitacionActivaPorId(idHabitacion);
            if (indiceHabitacion == -1) {
                System.out.println("La habitacion digitada no existe o esta inactiva.");
                System.out.println(""); 
            }
        } while (indiceHabitacion == -1);
        nuevaReservacion.setIdHabitacion(idHabitacion);
        leer.nextLine();

        // Cedula del cliente
        String cedula;
        int indiceCliente;
        do {
            System.out.println("");
            System.out.println("Digite la cedula del cliente: ");
            cedula = leer.nextLine();
            indiceCliente = buscarClienteActivoPorCedula(cedula);
            if (indiceCliente == -1) {
                System.out.println("El cliente digitado no existe o esta inactivo");
                System.out.println("");
            }
        } while (indiceCliente == -1);
        nuevaReservacion.setCedCliente(cedula);
        
        // Fecha de ingreso
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        Date fechaIngreso = null;
        do {
            System.out.println("");
            System.out.println("Digite la fecha (dd/MM/yyyy): ");
            String textoFecha = leer.nextLine();
            try {
                fechaIngreso = formato.parse(textoFecha);
            } catch (ParseException ex) {
                System.out.println("Fecha invalida, intente de nuevo.");
            }
        } while (fechaIngreso == null);
        nuevaReservacion.setIngreso(fechaIngreso);   
        
        // Fecha de salida
        Date fechaSalida = null;
        do {
            System.out.println("");
            System.out.println("Digite la fecha (dd/MM/yyyy): ");
            String textoFecha = leer.nextLine();
            try {
                fechaSalida = formato.parse(textoFecha);
                if (fechaSalida.before(fechaIngreso)) {
                    System.out.println("La fecha de salida no puede ser anterior a la fecha de ingreso.");
                    fechaSalida = null;
                }
            } catch (ParseException ex) {
                System.out.println("Fecha invalida, intente de nuevo.");
            }
        } while (fechaSalida == null);
        
        // Calculo del monto
        ObjHabitacion habitacion = obtenerHabitacion(indiceHabitacion);  // Obtener la habitación seleccionada
        double costoPorNoche = habitacion.getCostoPorNoche();  // Costo por noche
        // Calcular cantidad de noches
        long diferencia = fechaSalida.getTime() - fechaIngreso.getTime();
        long noches = diferencia / (1000 * 60 * 60 * 24);
        double monto = noches * costoPorNoche;  // Calcular monto total
        nuevaReservacion.setMonto(monto);
        System.out.println("");
        System.out.println("Cantidad de noches: " + noches);
        System.out.println("Costo por noche: " + costoPorNoche);
        System.out.println("Monto total de la reservacion: " + monto);
        
        nuevaReservacion.setEstado(1);
        
        Almacen.agregarReservacion(nuevaReservacion);
        Almacen.escribeArchivoReservaciones();
        
        System.out.println("");
        System.out.println("Reservacion registrada correctamente.");
        System.out.println("Identificador de la reservacion: " + id);
    
    }   
    
    // Método para modificar una Reservación existente
    public void modificarReservacion() {
        int indice = buscarReservacion();
        if (indice == -1) {
            System.out.println("");
            System.out.println("No se encontro la reservacion.");
            System.out.println("");
        } else {
            ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();
            ObjReservacion reservacion = misReservaciones.get(indice);
            System.out.println("");
            System.out.println("Reservacion encontrada:");
            System.out.println("Habitacion: "   + reservacion.getIdHabitacion());
            System.out.println("Cliente: "      + reservacion.getCedCliente());
            System.out.println("Monto actual: " + reservacion.getMonto());
            System.out.println("");
            
            // Id de la habitacion
            int idHabitacion;
            int indiceHabitacion;
            do {
                System.out.println("Digite el nuevo id de la habitacion: ");
                idHabitacion = leer.nextInt();
                indiceHabitacion = buscarHabitacionActivaPorId(idHabitacion);
                if (indiceHabitacion == -1) {
                    System.out.println("La habitacion digitada no existe o esta inactiva.");
                    System.out.println("");
                }
            } while (indiceHabitacion == -1);
            reservacion.setIdHabitacion(idHabitacion);
            leer.nextLine();
            
            // Cedula del clinete
            String cedula;
            int indiceCliente;
            do {
                System.out.println("");
                System.out.println("Digite la nueva cedula del cliente: ");
                cedula = leer.nextLine();
                indiceCliente = buscarClienteActivoPorCedula(cedula);
                if (indiceCliente == -1) {
                    System.out.println("El cliente digitado no existe o esta inactivo.");
                    System.out.println("");
                }
            } while (indiceCliente == -1);
            reservacion.setCedCliente(cedula);
            
            // Fecha de ingreso
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false);
            Date fechaIngreso = null;
            do {
                System.out.println("");
                System.out.println("Digite la nueva fecha de ingreso (dd/MM/yyyy): ");
                String textoFecha = leer.nextLine();
                try {
                    fechaIngreso = formato.parse(textoFecha);
                } catch (ParseException ex) {
                    System.out.println("Fecha invalida, intente de nuevo.");
                }
            } while (fechaIngreso == null);
            reservacion.setIngreso(fechaIngreso);

            // Fecha de salida
            Date fechaSalida = null;
            do {
                System.out.println("");
                System.out.println("Digite la nueva fecha de salida (dd/MM/yyyy): ");
                String textoFecha = leer.nextLine();
                try {
                    fechaSalida = formato.parse(textoFecha);
                    if (fechaSalida.before(fechaIngreso)) {
                        System.out.println("La fecha de salida no puede ser anterior a la fecha de ingreso.");
                        fechaSalida = null;
                    }
                } catch (ParseException ex) {
                    System.out.println("Fecha invalida, intente de nuevo.");
                }
            } while (fechaSalida == null);
            reservacion.setSalida(fechaSalida);
            
            // Recalculo del monto con la habitacion y fechas actualizadas
            ObjHabitacion habitacion = obtenerHabitacion(indiceHabitacion);
            double costoPorNoche = habitacion.getCostoPorNoche();
            long diferencia = fechaSalida.getTime() - fechaIngreso.getTime();
            long noches = diferencia / (1000 * 60 * 60 * 24);
            double monto = noches * costoPorNoche;
            reservacion.setMonto(monto);
            System.out.println("");
            System.out.println("Cantidad de noches: "  + noches);
            System.out.println("Costo por noche: "     + costoPorNoche);
            System.out.println("Nuevo monto total: "   + monto);
            
            Almacen.editarReservacion(indice, reservacion);
            Almacen.escribeArchivoReservaciones();
            
            System.out.println("");
            System.out.println("Reservacion modificada correctamente.");
            System.out.println("");
          }    
    }
    
    //-- Método para borrar (desactivar) una Reservación existente
    public void borrarReservacion() {
        int indice = buscarReservacion();
        if (indice == -1) {
            System.out.println("");
            System.out.println("No se encontro la reservacion.");
            System.out.println("");
        } else {
            ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();
            ObjReservacion reservacion = misReservaciones.get(indice);

            System.out.println("");
            System.out.println("Reservacion encontrada:");
            System.out.println("Habitacion: " + reservacion.getIdHabitacion());
            System.out.println("Cliente: "    + reservacion.getCedCliente());
            System.out.println("Monto: "      + reservacion.getMonto());
            System.out.println("");

            reservacion.setEstado(0);

            Almacen.editarReservacion(indice, reservacion);
            Almacen.escribeArchivoReservaciones();

            System.out.println("Reservacion eliminada correctamente.");
            System.out.println("");
        }
    }
    
    //-- Método para mostrar el listado de Reservaciones activas
    public void mostrarReservaciones() {
        System.out.println("---------------------------------------");
        System.out.println("|      LISTADO DE RESERVACIONES       |");
        System.out.println("---------------------------------------");
        System.out.println("");

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();

        for (int i = 0; i < misReservaciones.size(); i++) {
            ObjReservacion reservacion = misReservaciones.get(i);
            if (reservacion.getEstado() == 1) {
                System.out.println("Identificador: " + reservacion.getId());
                System.out.println("Habitacion: "    + reservacion.getIdHabitacion());
                System.out.println("Cliente: "       + reservacion.getCedCliente());
                System.out.println("Ingreso: "       + formato.format(reservacion.getIngreso()));
                System.out.println("Salida: "        + formato.format(reservacion.getSalida()));
                System.out.println("Monto: "         + reservacion.getMonto());
                System.out.println("");
                System.out.println("---------------------------------------");
            }
        }
    }

    
}