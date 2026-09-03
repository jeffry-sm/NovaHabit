/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.ObjHabitacion;
import Datos.ObjCliente;
import Datos.Estructuras;
import java.util.ArrayList;
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
    
    public int buscarHabitacionActivaPorId(int id){
        ArrayList<ObjHabitacion> misHabitaciones = Almacen.listarHabitaciones();
        for (int i = 0; i < misHabitaciones.size(); i++){
            if (misHabitaciones.get(i).getId() == id && misHabitaciones.get(i).getEstado() == 1){
                return i;
            }
        }
        return -1;
    }
    
    public ObjHabitacion obtenerHabitacion(int indice){
        return Almacen.listarHabitaciones().get(indice);
    }
    
    
    
    //--------------------------------------------
    // Módulo de Clientes
    //--------------------------------------------
    
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

}