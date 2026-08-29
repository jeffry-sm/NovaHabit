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
    
    static String[][] nombreHabitaciones = new String[200][5]; // 1 pt +
    
    static String[] nombreEmpleado = new String[100];
    //--Arrays/Arreglos de 2 Dimensiones
    //-- Primer corchete Filas, el segundo las columnas
    
    static String[][] reservacion = new String[100][6];
    /* Columnas: 0 Identificador, 1 Habitacion, 2 Cliente, 3 Fecha In
    4 Fecha Out, 5 Monto $$
    */
    static int numFactura = 258;
    static int numHabitacion = 100;
    
    
    /**
     * @param args the command line arguments
     */
    
        //-- Método para el Menú Principal
    public static void menuPrincipal(){
        
        //--Llenar el array Habitaciones con vacios
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 5; j++){
            nombreHabitaciones[i][j] = "";
        }
        }
        //--Llenar el array Reservaciones con vacios opcion 2
        for (int i =0; i < 100; i++ ){ //Filas
            for (int j =0; j < 6; j++){ //Columnas
                reservacion[i][j] = "";
            }
        }
        
       
        int opcion = 0; //Variable Local/contexto
        //-- Inicio de la repetición do-while
        do{
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
    
    
    //--Método para obtener la siguiente posición automáticamente -Insertar
    public static int siguienteHab(){
        int indice = -1;
        for (int i = 0; i < 200; i++){
            if ( nombreHabitaciones[i][0].equals("") ){
                indice = i;
                break;
            } 
        }
        return indice;
    }
    
    //--Metodo para obtener el nombre de la habitación/Cliente
    public static String obtenerNombre(String tabla){ //Parámetros
        System.out.println("Digite el nombre de " + tabla);
        return leer.nextLine();
    }
    
        //-- Método para registrar habitaciones
    public static void insertarHabitaciones(){
        System.out.println("---------------------------------------");
        System.out.println("|       REGISTRO DE HABITACIONES      |");
        System.out.println("---------------------------------------");
        System.out.println("");
        int fila = siguienteHab();
        leer.nextLine(); //Evitar error lectura
        
        // Columnas: 0 Identificador, 1 Tipo de Habitación, 2 Nombre Edificio, 3 Número de Piso 4 Costo por noche
        // identificador o numero de factura correspondiente
        int id = (fila+1) + numHabitacion; 
        
        System.out.println("");
        System.out.println("Digite el tipo de Habitacion: ");
        String tipHab = leer.nextLine();
        
        System.out.println("");
        System.out.println("Digite el nombre del Edificio: ");
        String edificio = leer.nextLine();
        
        System.out.println("");
        System.out.println("Digite el numero de piso: ");
        int piso = leer.nextInt();
        
        System.out.println("");
        System.out.println("Digite el monto por noche de la habitacion: ");
        double monto = leer.nextDouble();
        
        nombreHabitaciones[fila][0] = String.valueOf(id);
        nombreHabitaciones[fila][1] = tipHab;
        nombreHabitaciones[fila][2] = edificio;
        nombreHabitaciones[fila][3] = String.valueOf(piso);
        nombreHabitaciones[fila][4] = String.valueOf(monto);        
    }     
    
        //-- Método para modificar habitaciones
    public static void modificarHabitaciones(){
        System.out.println("---------------------------------------");
        System.out.println("|     MODIFICACION DE HABITACIONES    |");
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println("Digite el numero de Habitacion a Modificar ");
        //Numero de identificador 
        leer.nextLine();//manejo de errores de lectura
        String buscaHab = leer.nextLine();
        for (int i = 0; i < 200; i++){         
            if ( buscaHab.equals( nombreHabitaciones[i][0] )  ){
                System.out.println("");
                System.out.println("Digite el nuevo tipo de Habitacion: ");
                String tipHab = leer.nextLine();

                System.out.println("");
                System.out.println("Digite el nuevo nombre del Edificio: ");
                String edificio = leer.nextLine();

                System.out.println("");
                System.out.println("Digite el nuevo numero de piso: ");
                int piso = leer.nextInt();

                System.out.println("");
                System.out.println("Digite el nuevo monto por noche de la habitacion: ");
                double monto = leer.nextDouble();

                nombreHabitaciones[i][1] = tipHab;
                nombreHabitaciones[i][2] = edificio;
                nombreHabitaciones[i][3] = String.valueOf(piso);
                nombreHabitaciones[i][4] = String.valueOf(monto); 
            }
        }    
    }     
            
        //-- Método para borrar habitaciones
        public static void borrarHabitaciones(){
        System.out.println("---------------------------------------");
        System.out.println("|     ELIMINAACION DE HABITACIONES    |");
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println("Digite el identificador para eliminar ");
        System.out.println("La Habitacion (1-200): ");
        
        leer.nextLine();//manejo de errores de lectura
        String buscaHab = leer.nextLine();
        for (int i = 0; i < 100; i++){         
            if ( buscaHab.equals( nombreHabitaciones[i][0] )  ){
                for (int j = 0; j < 5; j++) {
                    nombreHabitaciones[i][j] = "";
                }
            }
        }
    }        
    
        //-- Método para Mostrar habitaciones
    public static void mostrarHabitaciones(){
        System.out.println("---------------------------------------");
        System.out.println("|       LISTADO DE HABITACIONES       |");
        System.out.println("---------------------------------------");
        System.out.println("");
        
        for (int i = 0; i < 200; i++){
            if ((nombreHabitaciones[i][0] != null) 
                    && ( !nombreHabitaciones[i][0].equals("")) ){
                System.out.println("Identificador: " + nombreHabitaciones[i][0]);
                System.out.println("Tipo: "          + nombreHabitaciones[i][1]);
                System.out.println("Edificio: "      + nombreHabitaciones[i][2]);
                System.out.println("Piso: "          + nombreHabitaciones[i][3]);
                System.out.println("Monto por Noche: "  + nombreHabitaciones[i][4]);
                System.out.println("");
                System.out.println("---------------------------------------");
            }
        }
    }    
    
    //-- Método para e  l Menú de Habitaciones
    public static void menuHabitaciones(){
        int operacion = 0; //Variable Local/contexto
        do{ // inicio de la repetición
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
            System.out.println("5. Regresar ");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            operacion = leer.nextInt();
            
            switch (operacion){
                case 1 : insertarHabitaciones();
                         break;
                case 2 : modificarHabitaciones();
                         break;
                case 3 : borrarHabitaciones();
                         break;         
                case 4 : mostrarHabitaciones();
                         break;
            }
            
        }while (operacion < 5);
    }
    
    //-- Método para el Menú de Clientes
    public static void menuClientes(){
        int operacion = 0; //Variable Local/contexto
        do{
            System.out.println("----------------------------------------");
            System.out.println("|             MENU CLIENTES            |");
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
                case 1: misMetodos.insertarClientes();
                        break;
                case 2: misMetodos.modificarCliente();
                        break;
                case 3: misMetodos.borrarCliente();
                        break;
                case 4: misMetodos.mostrarClientes();
                        break;
            }
        }while (operacion < 5);
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
        }while (operacion < 5);
    }
    
    //--Método para obtener la siguiente Reservación
    public static int siguienteRes(){
        int indice = -1;
        for (int i = 0; i < 100; i++){
            if (reservacion[i][0] == ""){
                indice = i;
                break;
            } 
        }
        return indice;
    }
    
//-- Método para buscar reservaciones por cliente
    public static int buscarHabitacion(String id){
        int resultado = -1;
        
        for (int i = 0; i < 200; i++){         
            if ( id.equals( nombreHabitaciones[i][0] )  ){
                System.out.println("Tipo Habitacion: "  + nombreHabitaciones[i][1]);
                System.out.println("---------------------------------------");
                System.out.println("");
                resultado = i;
                break;
            }
        }
        return resultado;
    }    
    
    //-- Método para insertar Reservaciones
    public static void insertarReservaciones(){
        //--Matriz = Array de 2 dimensinoes
        System.out.println("---------------------------------------");
        System.out.println("|      REGISTRO DE RESERVACIONES      |");
        System.out.println("---------------------------------------");
        System.out.println("");
        int fila = siguienteRes();
        leer.nextLine(); //Evitar error lectura
        
        // identificador o numero de factura correspondiente
        int id = (fila+1) + numFactura; 
        String habitacion = "";
        
            // encuentraHab guarda el indice de la fila que se devuelve de buscarHabitacion 
        int encuentraHab = -1; //--   -1 no encontrado, cualquier otro numero = encontrado
        do{
            System.out.println("");
            System.out.println("Digite la habitacion: ");
            habitacion = leer.nextLine();
            encuentraHab = buscarHabitacion(habitacion);
            if (encuentraHab == -1) {
                System.out.println("La habitacion digitada no existe ");
                System.out.println("");
            }
        } while ( encuentraHab == -1);
        
        System.out.println("");
        System.out.println("Digite el cliente: ");
        String cliente = leer.nextLine();
        
        System.out.println("");
        System.out.println("Digite la fecha de entrada (aaaammdd): ");
        int fechaIn = leer.nextInt();
        
        System.out.println("");
        System.out.println("Digite la fecha de salida (aaaammdd): ");
        int fechaOut = leer.nextInt(); 
        
        int dias = fechaOut - fechaIn;
        
        System.out.println("");
        double monto = dias * Double.parseDouble(nombreHabitaciones[encuentraHab][4]);
        System.out.println("El monto antes de impuestos de la reserva es: " + monto);
        
        
        reservacion[fila][0] = String.valueOf(id);
        reservacion[fila][1] = habitacion;
        reservacion[fila][2] = cliente;
        reservacion[fila][3] = String.valueOf(fechaIn);
        reservacion[fila][4] = String.valueOf(fechaOut);
        reservacion[fila][5] = String.valueOf(monto);
    }
    
    //-- Método para mostrar las reservaciones
    public static void mostrarReservaciones(){
        System.out.println("---------------------------------------");
        System.out.println("|       LISTADO DE RESERVACIONES      |");
        System.out.println("---------------------------------------");
        System.out.println("");
        for (int i = 0; i < 100; i++){
            if (reservacion[i][0] != ""){
                System.out.println("Numero: "        + reservacion[i][0]);
                System.out.println("Habitacion: "    + reservacion[i][1]);
                System.out.println("Cliente: "       + reservacion[i][2]);
                System.out.println("Fecha Ingreso: " + reservacion[i][3]);
                System.out.println("Fecha Salida: "  + reservacion[i][4]);
                System.out.println("Monto: "         + reservacion[i][5]);
                double impuesto = Double.parseDouble(reservacion[i][5]) * (0.13);
                System.out.println("impuesto : " + impuesto);
                double total = Double.parseDouble(reservacion[i][5]) + impuesto;
                System.out.println("Total a Pagar : " + total);
                System.out.println("---------------------------------------");
                System.out.println("");
            }
        }
    }
    
    //-- Método para buscar reservaciones por cliente
    public static void buscarReservacion(){
        System.out.println("---------------------------------------");
        System.out.println("|      BUSQUEDA DE RESERVACIONES      |");
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println("Digite el nombre del cliente a buscar: ");
        leer.nextLine();//manejo de errores de lectura
        String buscaCli = leer.nextLine();
        for (int i = 0; i < 100; i++){         
            if ( buscaCli.equals( reservacion[i][2] )  ){
                System.out.println("Numero: "        + reservacion[i][0]);
                System.out.println("Habitacion: "    + reservacion[i][1]);
                System.out.println("Cliente: "       + reservacion[i][2]);
                System.out.println("Fecha Ingreso: " + reservacion[i][3]);
                System.out.println("Fecha Salida: "  + reservacion[i][4]);
                System.out.println("Monto: "         + reservacion[i][5]);
                System.out.println("---------------------------------------");
                System.out.println("");
            }
        }
    }
    
    //-- Método para borrar reservaciones por cliente
    public static void borrarReservacion(){
        System.out.println("---------------------------------------");
        System.out.println("|       BORRADO DE RESERVACIONES      |");
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println("Digite el numero de factura a borrar ");
        leer.nextLine();//manejo de errores de lectura
        String buscaFact = leer.nextLine();
        for (int i = 0; i < 100; i++){         
            if ( buscaFact.equals( reservacion[i][0] )  ){
                for (int j = 0; j < 6; j++) {
                    reservacion[i][j] = "";
                }
            }
        }
    }    
    
//-- Método para Modificar reservaciones por cliente
    public static void modificaReservacion(){
        System.out.println("---------------------------------------");
        System.out.println("|       EDICION DE RESERVACIONES      |");
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println("Digite el numero de factura a Modificar ");
        //Numero de factura es nuestro identificador
        leer.nextLine();//manejo de errores de lectura
        String buscaFact = leer.nextLine();
        for (int i = 0; i < 100; i++){         
            if ( buscaFact.equals( reservacion[i][0] )  ){
                System.out.println("");
                System.out.println("Digite la habitacion: ");
                String habitacion = leer.nextLine();

                System.out.println("");
                System.out.println("Digite el cliente: ");
                String cliente = leer.nextLine();

                System.out.println("");
                System.out.println("Digite la fecha de entrada (dd-mm-aaaa): ");
                String fechaIn = leer.nextLine();

                System.out.println("");
                System.out.println("Digite la fecha de salida (dd-mm-aaaa): ");
                String fechaOut = leer.nextLine(); 

                System.out.println("");
                System.out.println("Digite el monto de la reserva: ");
                double monto = leer.nextDouble();

                reservacion[i][1] = habitacion;
                reservacion[i][2] = cliente;
                reservacion[i][3] = fechaIn;
                reservacion[i][4] = fechaOut;
                reservacion[i][5] = String.valueOf(monto);
            }
        }
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
                case 1: insertarReservaciones();
                        break;
                case 2: modificaReservacion();
                        break;
                case 3: borrarReservacion();
                        break;        
                case 4: mostrarReservaciones();
                        break;
                case 5: buscarReservacion();
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