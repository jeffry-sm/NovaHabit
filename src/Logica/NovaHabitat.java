/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

//-- Adminitrativo
package Logica;

import java.util.Scanner; // Library = Biblioteca

/**
 * @authorm Jeffry SM
 */

//-- Clase
public class NovaHabitat {

    
    // Llamado a la clase de los métodos
    static Metodos misMetodos = new Metodos();
    
    
    //-- Usar una copia de una clase = Instanciar (New)
    //-- instanciar en una variable
    static Scanner leer = new Scanner(System.in);//Variable Global
    //--Arrays/Arreglos de 1 Dimensión
    static String[] nombreCliente = new String[100];
    
    static String[] nombreEmpleado = new String[100];
    //--Arrays/Arreglos de 2 Dimensiones
    //-- Primer corchete Filas, el segundo las columnas
    
    static String[][] reservacion = new String[100][6];
    /* Columnas: 0 Identificador, 1 Habitacion, 2 Cliente, 3 Fecha In
    4 Fecha Out, 5 Monto $$
    */
    static int numFactura = 258;
    
    
    /**
     * @param args the command line arguments
     */
    
        //-- Método para el Menú Principal
    public static void menuPrincipal(){
        
        //--Llenar el array Reservaciones con vacios opcion 2
        for (int i =0; i < 100; i++ ){ //Filas
            for (int j =0; j < 6; j++){ //Columnas
                reservacion[i][j] = "";
            }
        }
        
       
        int opcion = 0; //Variable Local/contexto
        //-- Inicio de la repetición do-while
        do{
            System.out.println("");
            System.out.println("----------------------------------------");
            System.out.println("|             NOVA HABITAT             |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion (1-9) luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Habitaciones ");
            System.out.println("2. Clientes");
            System.out.println("3. Empleados"); //TAREA -- id Identificación
            System.out.println("4. Reservaciones");
            System.out.println("5. Check-In / Check-Out");
            System.out.println("6. Reportes");
            System.out.println("9. Salir");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();

            //-- Condicional Switch /cambio ( parecido al if )
            switch (opcion){
                case 1 : menuHabitaciones();
                         break;
                case 2 : menuClientes();
                         break;
                case 3 : menuEmpleados();
                         break;
                case 4 : menuReservaciones();
                         break;
                case 5 : menuRegistro();
                         break;
                case 6 : menuReportes();
                         break;
            }
            if (opcion < 0 ){
                System.exit(0); 
            }
        } while (opcion < 7); //condición de finalización del ciclo
        
    }   
    
    //---------------------------------------
    // MENÚ DE HABITACIONES
    //---------------------------------------
    
    public static void menuHabitaciones(){
        int operacion = 0; //Variable Local/contexto
        do{ // inicio de la repetición
            System.out.println("");
            System.out.println("----------------------------------------");
            System.out.println("|           MENU HABITACIONES          |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion (1-5) luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Insertar ");
            System.out.println("2. Modificar ");
            System.out.println("3. Borrar ");
            System.out.println("4. Consultar ");
            System.out.println("5. Buscar ");
            System.out.println("6. Regresar ");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            operacion = leer.nextInt();

            switch (operacion){
                case 1 : misMetodos.insertarHabitacion();
                         break;
                case 2 : misMetodos.modificarHabitacion();
                         break;
                case 3 : misMetodos.borrarHabitacion();
                         break;         
                case 4 : misMetodos.mostrarHabitaciones();
                         break;
                case 5 : misMetodos.consultarHabitacion();
                         break;
            }

        }while (operacion < 6);
    }
    
    //-- Método para el Menú de Clientes
    public static void menuClientes(){
        int operacion = 0; //Variable Local/contexto
        do{
            System.out.println("----------------------------------------");
            System.out.println("|             MENU CLIENTES            |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion (1-6) luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Insertar ");
            System.out.println("2. Modificar ");
            System.out.println("3. Borrar ");
            System.out.println("4. Consultar ");
            System.out.println("5. Buscar ");
            System.out.println("6. Regresar ");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");

            operacion = leer.nextInt();

            switch (operacion) {
                case 1: misMetodos.insertarClientes();
                        break;
                case 2: misMetodos.modificarCliente();
                        break;
                case 3: misMetodos.borrarCliente();
                        break;
                case 4: misMetodos.mostrarClientes();
                        break;
                case 5: misMetodos.consultarCliente();
                        break;
            }
        }while (operacion < 6);
    }
    
    //-- Método para el Menú de Empleados
    public static void menuEmpleados(){
        int operacion = 0; //Variable Local/contexto
        do{
            System.out.println("----------------------------------------");
            System.out.println("|             MENU EMPLEADOS           |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion (1-5) luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Insertar ");
            System.out.println("2. Modificar ");
            System.out.println("3. Borrar ");
            System.out.println("4. Consultar ");
            System.out.println("5. Regresar ");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            operacion = leer.nextInt();
            
            switch (operacion) {
                case 1: misMetodos.insertarEmpleado();
                        break;
                case 2: misMetodos.modificarEmpleado();
                        break;
                case 3: misMetodos.borrarEmpleado();
                        break;
                case 4: misMetodos.mostrarEmpleados();
                        break;
            }
            
        }while (operacion < 5);
    }
    
    
    
    //-- Método para el Menú de Reservaciones
    public static void menuReservaciones(){
        int operacion = 0; //Variable Local/contexto
        do{
            System.out.println("----------------------------------------");
            System.out.println("|          MENU RESERVACIONES          |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion (1-5) luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Insertar ");
            System.out.println("2. Modificar ");
            System.out.println("3. Borrar ");
            System.out.println("4. Consultar ");
            System.out.println("5. Buscar ");
            System.out.println("6. Regresar ");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            operacion = leer.nextInt();
            
            switch (operacion) {
                case 1: misMetodos.insertarReservacion();
                        break;
                case 2: misMetodos.modificarReservacion();
                        break;
                case 3: misMetodos.borrarReservacion();
                        break;        
                case 4: misMetodos.mostrarReservaciones();
                        break;
                case 5: misMetodos.buscarReservacion();
                        break;
            }
        }while (operacion < 6);
    }
    
    //-- Método para el Menú de Check-In / Check-Out
    public static void menuRegistro(){
        int operacion = 0; //Variable Local/contexto
        do{
            System.out.println("----------------------------------------");
            System.out.println("|     MENU CHECK-IN / CHECK-OUT        |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion (1-3) luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Check-In ");
            System.out.println("2. Check-Out ");
            System.out.println("3. Regresar ");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            operacion = leer.nextInt();
            
            switch (operacion) {
                case 1: misMetodos.registrarCheckIn();
                        break;
                case 2: misMetodos.registrarCheckOut();
                        break;
            }
        }while (operacion < 3);
    }
    
    
    //-- Método para el Menú de Reportes
    public static void menuReportes(){
        int operacion = 0; //Variable Local/contexto
        do{
            System.out.println("----------------------------------------");
            System.out.println("|            MENU REPORTES             |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion (1-3) luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Reservaciones ");
            System.out.println("2. Ocupacion ");
            System.out.println("3. Regresar ");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            operacion = leer.nextInt();
            
            switch (operacion) {
                case 1: misMetodos.reporteReservaciones();
                        break;
                case 2: misMetodos.reporteOcupacion();
                         break;
            }
            
        }while (operacion < 3);
    }
    
    //--Método Principal
    //-- static, para usar el elemento real, no su copia
    public static void main(String[] args) {
        
        // TODO code application logic here
        
        misMetodos.nuevosArchivos();
        
        misMetodos.cargarListas();
        
        
        menuPrincipal();
        
        int opcion = 1; 
        
        /* otra varaibale local, puede llamarse igual 
          que variables locales de otros métodos
          u otras estructuras */
    }
    
}