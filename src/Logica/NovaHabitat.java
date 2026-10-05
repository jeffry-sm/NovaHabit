/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

//-- Adminitrativo
package Logica;

import java.util.Scanner; // Library = Biblioteca
import Datos.ObjUsuario;

/**
 * @authorm Jeffry SM
 */

//-- Clase
public class NovaHabitat {

    
    // Llamado a la clase de los métodos
    static Metodos misMetodos = new Metodos();

    static Scanner leer = new Scanner(System.in);//Variable Global


    public static void iniciarSesion() {
        String usuario;
        String contrasena;
        ObjUsuario usuarioLogueado = null;
        int intentos = 0;

        do {
            System.out.println("----------------------------------------");
            System.out.println("|                LOGIN                 |");
            System.out.println("----------------------------------------");
            System.out.println("");

            System.out.print("Usuario: ");
            usuario = leer.next();

            System.out.print("Contrasena: ");
            contrasena = leer.next();
            leer.nextLine();

            usuarioLogueado = misMetodos.procesarLogin(usuario, contrasena);

            if (usuarioLogueado != null) {
                System.out.println("");
                System.out.println("----------------------------------------");
                System.out.println("Bienvenido(a): " + usuarioLogueado.getUsuario());
                System.out.println("Rol: "           + usuarioLogueado.getRol());
                System.out.println("----------------------------------------");
                System.out.println("");

                menuPrincipal(usuarioLogueado);
            } else {
                intentos++;
                System.out.println("");
                System.out.println("Usuario o contrasena incorrectos.");
                System.out.println("Intentos restantes: " + (3 - intentos));
                System.out.println("");
            }
        } while (usuarioLogueado == null && intentos < 3);

        if (usuarioLogueado == null) {
            System.out.println("----------------------------------------");
            System.out.println("Se agotaron los 3 intentos.");
            System.out.println("El programa se cerrara.");
            System.out.println("----------------------------------------");
        }
    }
    
    
    
        //-- Método para el Menú Principal
    public static void menuPrincipal(ObjUsuario usuarioLogueado){
        boolean esAdmin = usuarioLogueado.getRol().equalsIgnoreCase("Administrador");

        int opcion = 0;
        do {
            System.out.println("----------------------------------------");
            System.out.println("|             NOVA HABITAT             |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Habitaciones ");
            System.out.println("2. Clientes");
            System.out.println("4. Reservaciones");
            System.out.println("5. Check-In / Check-Out");
            if (esAdmin) {
                System.out.println("3. Empleados");
                System.out.println("6. Reportes");
                System.out.println("7. Usuarios");
            }
            System.out.println("9. Salir");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();

            switch (opcion) {
                case 1 : menuHabitaciones();
                         break;
                case 2 : menuClientes();
                         break;
                case 3 : if (esAdmin) {
                             menuEmpleados();
                         } else {
                             System.out.println("");
                             System.out.println("No tiene permiso para esa opcion.");
                             System.out.println("");
                         }
                         break;
                case 4 : menuReservaciones();
                         break;
                case 5 : menuRegistro();
                         break;
                case 6 : if (esAdmin) {
                             menuReportes();
                         } else {
                             System.out.println("");
                             System.out.println("No tiene permiso para esa opcion.");
                             System.out.println("");
                         }
                         break;
                case 7 : if (esAdmin) {
                             menuUsuarios();
                         } else {
                             System.out.println("");
                             System.out.println("No tiene permiso para esa opcion.");
                             System.out.println("");
                         }
                         break;
            }
        } while (opcion != 9);
    }
    
    
    public static void menuUsuarios(){
        int operacion = 0;
        do {
            System.out.println("----------------------------------------");
            System.out.println("|             MENU USUARIOS            |");
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
                case 1: misMetodos.insertarUsuario();
                        break;
                case 2: misMetodos.modificarUsuario();
                        break;
                case 3: misMetodos.borrarUsuario();
                        break;
                case 4: misMetodos.mostrarUsuarios();
                        break;
                case 5: misMetodos.consultarUsuario();
                        break;
            }
        } while (operacion < 6);
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
            System.out.println("5. Buscar ");
            System.out.println("6. Regresar ");
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
                case 5: misMetodos.consultarEmpleado();
                        break;
            }
            
        }while (operacion < 6);
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
                case 5: misMetodos.consultarReservacion();
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

        iniciarSesion();

        int opcion = 1; 
        
        /* otra varaibale local, puede llamarse igual 
          que variables locales de otros métodos
          u otras estructuras */
    }
    
}