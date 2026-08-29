/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.ObjCliente;
import Datos.Estructuras;
import Datos.ObjReservacion;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
//-- Bibliontecas de trabajo con fechas
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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
    
    
    public void nuevosArchivos() {
        
        Almacen.crearArchivo("Clientes");
        Almacen.crearArchivo("Empleados");
        Almacen.crearArchivo("Habitaciones");
        Almacen.crearArchivo("Reservas");
        
    }
    
    public void cargarListas() {
        // Aqui vamos a poner los métodos que cargan en cada lista
        Almacen.leerArchivoClientes();
    }
    
    
    //--Método para devolver el siguiente # de reserva
    public int siguienteReserva() {
        int resultado = 1;
        //--Nueva lista para trabajar localmente (Reservaciones)
        ArrayList<ObjReservacion> misReservas = Almacen.listarReservaciones();
        for (int i = 0; i < misReservas.size(); i++) {
            if (resultado < misReservas.get(i).getId() ) {
                resultado = misReservas.get(i).getId() + 1;
            }
        }
        return resultado;
    }
    
    
    public int buscarCliente(String cedula) {
        int resultado = -1;
        
        ArrayList<ObjCliente> misClientes = Almacen.listarClientes();
        for (int i = 0; i < misClientes.size(); i++) {
            if (cedula.equals(misClientes.get(i).getCedula() ) ) {
                System.out.println(misClientes.get(i).getApellido1() + 
                                   "" + misClientes.get(i).getApellido2() +
                                   "" + misClientes.get(i).getNombre() );
                resultado = misClientes.get(i).getId();
            }
        }
        return resultado;
    }
    
    
    public void insertarReserva() {
        System.out.println("---------------------------------------");
        System.out.println("|       REGISTRAR RESERVACIONES       |");
        System.out.println("---------------------------------------");
        System.out.println("");
        
        int id = siguienteReserva();
        System.out.println("Identificador: " + id);
        System.out.println("");
        System.out.println("Digite la habitación: ");
        int idHabitacion = leer.nextInt();
        
        String cedCliente = "";
        int encuentraCli = -1;
        do {
            System.out.println("");
            System.out.println("Digite la Cedula del Cliente: ");
            cedCliente = leer.nextLine();
            encuentraCli = buscarCliente(cedCliente);
        } while ( encuentraCli == -1 );
        
        //-- Definir el formato de fecha (15/08/2026)
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false); //-- Rechaza fechas invalidas (30/02/2026)
        
        Date ingreso = null;
        Date salida = null;
        int dias = 0;
        
        try {
             System.out.println("");
             System.out.println("Digite la fecha de ingreso (dd/mm/aaaa): ");
             String fechaIn = leer.nextLine();
             ingreso = formato.parse(fechaIn);
             
             System.out.println("Digite la cantidad de dias a hospedar:");
             dias = leer.nextInt();
             
             //-- Variable calendario para manejar cálculos de fechas
             Calendar miCalendario = Calendar.getInstance();
             //-- Asignamos la fecha inicial al calendario
             miCalendario.setTime(ingreso);
             //-- Sumamos los dias a la fecha inicial
             miCalendario.add(Calendar.DAY_OF_YEAR, dias);
             //-- Asignar la nueva a Salida
             salida = miCalendario.getTime();
             System.out.println("");
             System.out.println("Fecha de Salida" + formato.format(salida));
             
        } catch ( ParseException e ) {
            System.out.println(e.toString());
            
        }
        
        System.out.println("");
        System.out.println("Digite el monto del hospedaje: ");
        double monto = leer.nextDouble();
        
        ObjReservacion nuevaReserva = new ObjReservacion (id, 
                                          idHabitacion, cedCliente,
                                          ingreso, salida, monto, 1);
        Almacen.agregarReservacion(nuevaReserva);
        
        // Ingreso; Salida; Monto;
        
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
    
    public int buscarCliente(){
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
}

