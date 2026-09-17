/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.util.ArrayList;
import javax.swing.JOptionPane;
//-- Bibliotecas para trabajar con Archivos -- Archivos texto (.txt)
import java.io.File;             // File  = Archivo
import java.io.FileReader;      // Reader = Lector
import java.io.FileWriter;     //  Writer = Escritor
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *     @author Jeffry SM
 */

public class Estructuras {

    // -----------------------------------------------
    // 1. LISTAS PRINCIPALES PARA ALMACENAR EN MEMORIA
    //------------------------------------------------
    static ArrayList<ObjHabitacion> listaHabitaciones = new ArrayList<>();
    static ArrayList<ObjCliente> listaClientes = new ArrayList<>();
    static ArrayList<ObjEmpleado> listaEmpleados = new ArrayList<>();
    static ArrayList<ObjReservacion> listaReservaciones = new ArrayList<>();
     
    // Constructor
     
    public Estructuras() {
          
    }
     
    // ------------------------------------------
    // 2. MÉTODOS PARA GESTIONAR CON ARCHIVOS
    // ------------------------------------------ 
    
    // Método para crear un archivo físico en el disco
    public void crearArchivo(String nombre) {
         
        File miArchivo = new File(nombre + ".txt");
         
        try { //--Intento de hacer una operación
            if ( miArchivo.createNewFile() ) {
                 System.out.println("--------------------");
                 System.out.println("   Archivo Creado   ");
                 System.out.println("--------------------");
            } else {
                 System.out.println("-------------------");
                 System.out.println(" Archivo ya Existe ");
                 System.out.println("-------------------");
            }
        } catch ( IOException ex ) { //-- Captura de errores si falla el intento
            JOptionPane.showMessageDialog( null,"Error  al guardar el archivo", 
                                            "Atención", JOptionPane.ERROR_MESSAGE );
            System.out.println( ex.toString() ) ;
        }
         
    }
     
     // Método para limpiar archivos
     public void limpiarArchivo(String nombre) {
         try {
             PrintWriter escritor = new PrintWriter(nombre + ".txt");
             System.out.println("Archivo Limpio."); 
         } catch ( IOException ex ) { //-- Captura de errores si falla el intento
             JOptionPane.showMessageDialog( null,"Error  al limpiar el archivo", 
                                             "Atención", JOptionPane.ERROR_MESSAGE );
             System.out.println( ex.toString() ) ;
             
         }
     }
     
     // ------------------------------------------
     // 3. MÓDULO DE Habitaciones
     // ------------------------------------------
     
    // Método para escribir el archivo de Habitaciones
    public void escribeArchivoHabitaciones() {
        //-- Antes de escribir limpiamos el archivo
        System.out.println("------------------------------------");
        System.out.println("Limpiamos el Archivo de Habitaciones");
        limpiarArchivo("Habitaciones");

        try {
            FileWriter escritor = new FileWriter("Habitaciones.txt", true);
            String linea;
            for (int i = 0; i < listaHabitaciones.size(); i++) {
                ObjHabitacion miHabitacion = listaHabitaciones.get(i);

                linea = String.valueOf(miHabitacion.getId())           + ";" +
                        miHabitacion.getTipoHabitacion()                + ";" +
                        miHabitacion.getEdificio()                      + ";" +
                        String.valueOf(miHabitacion.getPiso())          + ";" +
                        String.valueOf(miHabitacion.getCostoPorNoche()) + ";" +
                        String.valueOf(miHabitacion.getEstado())        + ";\n";
                System.out.println("Escribiendo la linea: " + linea);
                escritor.write(linea);
            }

            escritor.write(10);
            escritor.close();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error  al limpiar el archivo",
                    "Atención", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());
        }
        System.out.println("------------------------------------");
    }
    
     //--Métodos para leer el archivo de Habitaciones (Llenar la lista Habitaciones)
     public void leerArchivoHabitaciones() {

        try {
            FileReader miArchivo = new FileReader("Habitaciones.txt");
            BufferedReader lector = new BufferedReader(miArchivo);

            String linea = lector.readLine();
            String segmento[];

            while (linea != null) {
                segmento = linea.split(";");
                if (!segmento[0].equals("")) {
                    ObjHabitacion miHabitacion = new ObjHabitacion();
                    miHabitacion.setId(Integer.parseInt(segmento[0]));
                    miHabitacion.setTipoHabitacion(segmento[1]);
                    miHabitacion.setEdificio(segmento[2]);
                    miHabitacion.setPiso(Integer.parseInt(segmento[3]));
                    miHabitacion.setCostoPorNoche(Double.parseDouble(segmento[4]));
                    miHabitacion.setEstado(Integer.parseInt(segmento[5]));

                    listaHabitaciones.add(miHabitacion);
                }
                linea = lector.readLine();
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error  al leer el archivo",
                    "Atención", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());

        }
    }
    
     // ------------ Métodos de Trabajo (Habitaciones) ------------
    
     // Insertar habitacion en la lista
     public void agregarHabitacion(ObjHabitacion miHabitacion) {
       listaHabitaciones.add(miHabitacion);  
     }
     
     public void editarHabitacion(int indice, ObjHabitacion miHabitacion) {
        listaHabitaciones.set(indice, miHabitacion);
     }
     
     public void quitarHabitacion(int indice) {
         listaHabitaciones.remove(indice);
     }
     
     public ArrayList<ObjHabitacion> listarHabitaciones() {
        return  new ArrayList<>(listaHabitaciones);
     }  
     
     // ------------------------------------------
     // 4. MÓDULO DE CLIENTES
     // ------------------------------------------
     
     // ------------ Persistencia en Archivo ------------
     
     public  void escribeArchivoClientes() {
         //-- Antes de escribir limpiamos el archivo
         System.out.println("------------------------------------");
         System.out.println("Limpiamos el Archivo de Clientes");
         limpiarArchivo("Clientes");
         
         //--Estructura para guardar los datos en el archivo
        try {
             System.out.println("Entrando en el Try"); 
             FileWriter escritor = new FileWriter("Clientes.txt", true);
             //-- Variable para armar la linea de escritura
             String linea;
             //-- Recorrer la lista de Clientes para armar cada línea
             for ( int i = 0; i < listaClientes.size(); i++ ) {
                 ObjCliente miCliente = listaClientes.get(i);
                 
                 linea = String.valueOf(miCliente.getId())     + ";" +
                         miCliente.getCedula()                 + ";" +
                         miCliente.getNombre()                 + ";" +
                         miCliente.getApellido1()              + ";" +
                         miCliente.getApellido2()              + ";" +
                         miCliente.getTelefono()               + ";" +
                         miCliente.getCorreo()                 + ";" +
                         miCliente.getDireccion()              + ";" +
                         String.valueOf(miCliente.getEstado()) + ";\n" ;
                 System.out.println("Escribiendo la linea: " + linea);
                 escritor.write(linea);
                 
             }
             
              escritor.write(10); // comando de cierre de línea
              escritor.close();
     
         } catch ( IOException ex ) { //-- Captura de errores si falla el intento
             JOptionPane.showMessageDialog( null,"Error  al limpiar el archivo", 
                                             "Atención", JOptionPane.ERROR_MESSAGE );
             System.out.println( ex.toString() ) ;
             
         }
        System.out.println("------------------------------------");
     }
     
     //--Métodos para leer el archivo de Clientes (Llenar la lista Clientes)
     public void leerArchivoClientes() {
         
         try {
             //--Apertura del Archivo -- Fisica
             FileReader miArchivo = new FileReader("Clientes.txt");
             //-- Cargar en la memoria Ram ese archivo para leerlo
             BufferedReader lector = new BufferedReader(miArchivo);
             
             //--Variable para cargar las líneas de texto del archivo
             String linea = lector.readLine(); //-- Auto Primera línea
             //-- Variable para Controlar los segmentos de texto - Vector/Array Simples
             String segmento[];
             
             while ( linea != null ) {
                 //-- Dividir "linea" en cada separador ";" 
                 // cada sub segmento se mete en la variable del objeto
                 segmento = linea.split(";");
                 if ( !segmento[0].equals("")) {
                     ObjCliente miCliente = new ObjCliente();
                     miCliente.setId( Integer.parseInt(segmento[0]) );
                     miCliente.setCedula(segmento[1]);
                     miCliente.setNombre(segmento[2]);
                     miCliente.setApellido1(segmento[3]);
                     miCliente.setApellido2(segmento[4]);
                     miCliente.setTelefono(segmento[5]);
                     miCliente.setCorreo(segmento[6]);
                     miCliente.setDireccion(segmento[7]);
                      miCliente.setEstado( Integer.parseInt(segmento[8]) );
                      
                      listaClientes.add(miCliente);
                 }
                 linea = lector.readLine();  //-- Pasar a la siguiente linea
             }
             
             
         } catch ( IOException ex ) { //-- Captura de errores si falla el intento
             JOptionPane.showMessageDialog( null,"Error  al leer el archivo", 
                                             "Atención", JOptionPane.ERROR_MESSAGE );
             System.out.println( ex.toString() ) ;
         
      }
         
     }
     

     // ------------ Métodos de Trabajo (Clientes) ----------------
     
     // Insertar clientes en la lista
     public void agregarCliente(ObjCliente miCliente) {
       listaClientes.add(miCliente);  
     }
     
     // Modificar un cliente de la lista
     public void editarCliente(int indice, ObjCliente miCliente) {
         listaClientes.set(indice, miCliente);
     }
     
     // Borrar un cliente de la lista
     public void quitarCliente(int indice) {
         listaClientes.remove(indice);
     }
     
     // Devolver la lista de clientes
     public ArrayList<ObjCliente> listarClientes() {
        return  new ArrayList<>(listaClientes);
     }
     
     
     // ------------------------------------------
     // 5. MÓDULO DE EMPLEADOS
     // ------------------------------------------
     
     public void escribeArchivoEmpleados() {
         //-- Antes de escribir limpiamos el archivo
         System.out.println("------------------------------------");
         System.out.println("Limpiamos el Archivo de Clientes");
         limpiarArchivo("Empleados");
         
          //--Estructura para guardar los datos en el archivo
          try {
               System.out.println("Entrando en el Try");
               FileWriter escritor = new FileWriter("Empleados.txt", true);
               //-- Variable para armar la linea de escritura
               String linea;
               //-- Recorrer la lista de Empleados para armar cada línea
               for (int i = 0; i < listaEmpleados.size(); i++) {
                   ObjEmpleado miEmpleado = listaEmpleados.get(i);
                   
                   linea = String.valueOf(miEmpleado.getId())   + ";" +
                           miEmpleado.getCedula()               + ";" +
                           miEmpleado.getNombre()               + ";" +
                           miEmpleado.getApellido1()                + ";" +
                           miEmpleado.getApellido2()                + ";" +
                           miEmpleado.getTelefono()                 + ";" +
                           miEmpleado.getCorreo()                   + ";" +
                           String.valueOf(miEmpleado.getSalario())  + ";" +
                           String.valueOf(miEmpleado.getEstado())   + ";\n" ;
                   System.out.println("Escribiendo la linea: " + linea);
                   escritor.write(linea);
               }
               escritor.write(10); // comando de cierre de línea
               escritor.close();
               
          } catch (IOException ex) { //-- Captura de errores si falla el intento
              JOptionPane.showMessageDialog(null, "Error al limpiar el archivo",
                                        "Atención", JOptionPane.ERROR_MESSAGE);
              System.out.println(ex.toString());
          }
          System.out.println("------------------------------------");
     }
     
     //--Métodos para leer el archivo de Empleados (Llenar la lista Empleados)
     public void leerArchivoEmpleados() {
         try {
            //--Apertura del Archivo -- Fisica
            FileReader miArchivo = new FileReader("Empleados.txt");
            //-- Cargar en la memoria Ram ese archivo para leerlo
            BufferedReader lector = new BufferedReader(miArchivo);

            //--Variable para cargar las líneas de texto del archivo
            String linea = lector.readLine(); //-- Auto Primera línea
            //-- Variable para Controlar los segmentos de texto - Vector/Array Simples
            String segmento[];

            while (linea != null) {
                //-- Dividir "linea" en cada separador ";"
                // cada sub segmento se mete en la variable del objeto
                segmento = linea.split(";");
                if (!segmento[0].equals("")) {
                    ObjEmpleado miEmpleado = new ObjEmpleado();
                    miEmpleado.setId(Integer.parseInt(segmento[0]));
                    miEmpleado.setCedula(segmento[1]);
                    miEmpleado.setNombre(segmento[2]);
                    miEmpleado.setApellido1(segmento[3]);
                    miEmpleado.setApellido2(segmento[4]);
                    miEmpleado.setTelefono(segmento[5]);
                    miEmpleado.setCorreo(segmento[6]);
                    miEmpleado.setSalario(Double.parseDouble(segmento[7]));
                    miEmpleado.setEstado(Integer.parseInt(segmento[8]));

                    listaEmpleados.add(miEmpleado);
                }
                linea = lector.readLine();  //-- Pasar a la siguiente linea
            }

        } catch (IOException ex) { //-- Captura de errores si falla el intento
            JOptionPane.showMessageDialog(null, "Error al leer el archivo",
                                            "Atención", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());
        }

     }
     
     // ------------ Métodos de Trabajo (Clientes) ----------------
     
     // Insertar empleado en la lista
     public void agregarEmpleado(ObjEmpleado miEmpleado) {
       listaEmpleados.add(miEmpleado);  
     }
     
     public void editarEmpleado(int indice, ObjEmpleado miEmpleado) {
        listaEmpleados.set(indice, miEmpleado);
     }
     
     public void quitarEmpleado(int indice) {
         listaEmpleados.remove(indice);
     }
     
     // Devolver la lista de clientes
     public ArrayList<ObjEmpleado> listarEmpleados() {
        return  new ArrayList<>(listaEmpleados);
     } 
     
     
     // ------------------------------------------
     // 6. MÓDULO DE RESERVACIONES
     // ------------------------------------------
     
     // ----------- Persistencia en Archivo ------------
     
     public void escribeArchivoReservaciones() {
        System.out.println("------------------------------------");
        System.out.println("Limpiamos el Archivo de Reservaciones");
        limpiarArchivo("Reservaciones");
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            FileWriter escritor = new FileWriter("Reservaciones.txt", true);
            String linea;
            
            for (int i = 0; i < listaReservaciones.size(); i++) {
                ObjReservacion miReservacion  = listaReservaciones.get(i);
                
                linea = String.valueOf(miReservacion.getId())       + ";" +
                         miReservacion.getIdHabitacion()            + ";" +
                         miReservacion.getCedCliente()              + ";" +
                         formato.format(miReservacion.getIngreso()) + ";" +
                         formato.format(miReservacion.getSalida())  + ";" +
                         String.valueOf(miReservacion.getMonto())   + ";" +
                         String.valueOf(miReservacion.getEstado())  + ";" +
                         miReservacion.getEstadoReservacion()       + ";\n" ;
                 System.out.println("Escribiendo la linea: " + linea);
                 escritor.write(linea);
            }  
                 escritor.write(10);
                 escritor.close();
                 
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo de Reservaciones", 
                                          "Atención", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());
        }
        System.out.println("------------------------------------");
        
     }
     
     public void leerArchivoReservaciones() {
         
         listaReservaciones.clear();
         
         SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
         
         try {
             FileReader miArchivo = new FileReader ("Reservaciones.txt");
             BufferedReader lector = new BufferedReader (miArchivo);
             
             String linea = lector.readLine();
             String segmento[];
             
             while (linea != null) {
                 if (!linea.trim().isEmpty()) {
                     segmento = linea.split(";");
                     if (segmento.length >= 8 && !segmento[0].equals("")) {
                         try {
                              ObjReservacion miReservacion = new ObjReservacion();
                     
                              miReservacion.setId(Integer.parseInt(segmento[0]));
                              miReservacion.setIdHabitacion(Integer.parseInt(segmento[1]));
                              miReservacion.setCedCliente(segmento[2]);
                              miReservacion.setIngreso(formato.parse(segmento[3]));
                              miReservacion.setSalida(formato.parse(segmento[4]));
                              miReservacion.setMonto(Double.parseDouble(segmento[5]));
                              miReservacion.setEstado(Integer.parseInt(segmento[6]));
                              miReservacion.setEstadoReservacion(segmento[7]);
                              listaReservaciones.add(miReservacion);
                         } catch (ParseException ex) {
                         System.out.print("Error al leer fecha: " + ex.toString());
                     }
                 }
                     
                } 
                 linea = lector.readLine();
             }
             lector.close();
             
         } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, "Error al leer el archivo de Reservaciones", 
                                           "Atención", JOptionPane.ERROR_MESSAGE);
             System.out.println(ex.toString());
         }
         
     }
 
    // Insertar reservacion en la lista
     public void agregarReservacion(ObjReservacion miReservacion) {
       listaReservaciones.add(miReservacion);  
     }
     
     public void editarReservacion(int indice, ObjReservacion miReservacion) {
        listaReservaciones.set(indice, miReservacion);
     }
     
     public void quitarReservacion(int indice) {
         listaReservaciones.remove(indice);
     }
     
     public ArrayList<ObjReservacion> listarReservaciones() {
        return  new ArrayList<>(listaReservaciones);
     }  
     
}