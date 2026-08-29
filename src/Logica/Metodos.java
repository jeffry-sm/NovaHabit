/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

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