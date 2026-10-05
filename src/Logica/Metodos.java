/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.ObjUsuario;
import Datos.ObjHabitacion;
import Datos.ObjCliente;
import Datos.ObjEmpleado;
import Datos.ObjReservacion;
import Datos.Estructuras;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.InputMismatchException;

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
        
        Almacen.crearArchivo("Usuarios");
        Almacen.crearArchivo("Habitaciones");
        Almacen.crearArchivo("Clientes");
        Almacen.crearArchivo("Empleados");
        Almacen.crearArchivo("Reservaciones");
        
    }
    
    public void cargarListas() {
        // Aqui vamos a poner los métodos que cargan en cada lista
        Almacen.leerArchivoUsuarios();
         if (Almacen.listarUsuarios().isEmpty()) {
        ObjUsuario admin = new ObjUsuario();
        admin.setId(1);
        admin.setUsuario("admin");
        admin.setContrasena("123");
        admin.setRol("Administrador");
        admin.setEstado(1);

        ObjUsuario recepcion = new ObjUsuario();
        recepcion.setId(2);
        recepcion.setUsuario("recepcion");
        recepcion.setContrasena("123");
        recepcion.setRol("Recepcionista");
        recepcion.setEstado(1);

        Almacen.agregarUsuario(admin);
        Almacen.agregarUsuario(recepcion);
        Almacen.escribeArchivoUsuarios();
    }
        
        
        Almacen.leerArchivoHabitaciones();
        Almacen.leerArchivoClientes();
        Almacen.leerArchivoEmpleados();
        Almacen.leerArchivoReservaciones();
       
    }
    
    
    
    //--------------------------------------------
    // Módulo de Usuarios (Login)
    //--------------------------------------------
    
    
    public int siguienteUsuario() {
        int resultado = 1;
        ArrayList<ObjUsuario> misUsuarios = Almacen.listarUsuarios();
        for (int i = 0; i < misUsuarios.size(); i++) {
            if (resultado <= misUsuarios.get(i).getId()) {
                resultado = misUsuarios.get(i).getId() + 1;
            }
        }
        return resultado;
    }

    public int buscarUsuarioActivoPorUsuario(String usuario) {
        ArrayList<ObjUsuario> misUsuarios = Almacen.listarUsuarios();
        for (int i = 0; i < misUsuarios.size(); i++) {
            if (misUsuarios.get(i).getUsuario().equals(usuario)
                    && misUsuarios.get(i).getEstado() == 1) {
                return i;
            }
        }
        return -1;
    }

    //-- Valida usuario/contrasena para el login. Devuelve el usuario si es correcto, null si no.
    public ObjUsuario procesarLogin(String usuario, String contrasena) {
        ArrayList<ObjUsuario> misUsuarios = Almacen.listarUsuarios();
        for (int i = 0; i < misUsuarios.size(); i++) {
            ObjUsuario miUsuario = misUsuarios.get(i);
            if (miUsuario.getUsuario().equals(usuario) && miUsuario.getContrasena().equals(contrasena)) {
                if (miUsuario.getEstado() == 1) {
                    return miUsuario;
                } else {
                    System.out.println("El usuario ingresado esta inactivo.");
                    return null;
                }
            }
        }
        return null;
    }

    public int buscarUsuario() {
        System.out.println("---------------------------------------");
        System.out.println("|           BUSCAR  USUARIO           |");
        System.out.println("---------------------------------------");
        System.out.println("");
        leer.nextLine(); // limpia el salto de linea pendiente del menu (nextInt)
        System.out.println("Digite el nombre de usuario: ");
        String usuario = leer.nextLine();
        int indice = -1;

        ArrayList<ObjUsuario> misUsuarios = Almacen.listarUsuarios();
        for (int i = 0; i < misUsuarios.size(); i++) {
            if (misUsuarios.get(i).getUsuario().equals(usuario)) {
                indice = i;
                break;
            }
        }
        return indice;
    }

    public ObjUsuario obtenerUsuario(int indice) {
        return Almacen.listarUsuarios().get(indice);
    }

    public void insertarUsuario() {
        ObjUsuario nuevoUsuario = new ObjUsuario();
        System.out.println("");
        System.out.println("---------------------------------------");
        System.out.println("|         REGISTRAR USUARIO           |");
        System.out.println("---------------------------------------");
        System.out.println("");

        int id = siguienteUsuario();
        System.out.println("Identificador: " + id);
        nuevoUsuario.setId(id);
        leer.nextLine();

        String usuario;
        int indiceExistente;
        do {
            System.out.println("");
            System.out.println("Digite el nombre de usuario: ");
            usuario = leer.nextLine().trim();
            if (usuario.isEmpty()) {
                System.out.println("El nombre de usuario no puede quedar vacio.");
                indiceExistente = -1;
            } else {
                indiceExistente = buscarUsuarioActivoPorUsuario(usuario);
                if (indiceExistente != -1) {
                    System.out.println("Ya existe un usuario activo con ese nombre.");
                }
            }
        } while (usuario.isEmpty() || indiceExistente != -1);
        nuevoUsuario.setUsuario(usuario);

        String contrasena;
        do {
            System.out.println("");
            System.out.println("Digite la contrasena: ");
            contrasena = leer.nextLine();
            if (contrasena.isEmpty()) {
                System.out.println("La contrasena no puede quedar vacia.");
            }
        } while (contrasena.isEmpty());
        nuevoUsuario.setContrasena(contrasena);

        int opcionRol = 0;
        boolean rolValido = false;
        do {
            System.out.println("");
            System.out.println("Seleccione el Rol:");
            System.out.println("1. Administrador");
            System.out.println("2. Recepcionista");
            System.out.print("Opcion: ");
            try {
                opcionRol = leer.nextInt();
                if (opcionRol == 1 || opcionRol == 2) {
                    rolValido = true;
                } else {
                    System.out.println("Opcion invalida.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Debe digitar un numero entero.");
                leer.next();
            }
        } while (!rolValido);
        leer.nextLine();

        nuevoUsuario.setRol(opcionRol == 1 ? "Administrador" : "Recepcionista");
        nuevoUsuario.setEstado(1);

        Almacen.agregarUsuario(nuevoUsuario);
        Almacen.escribeArchivoUsuarios();

        System.out.println("");
        System.out.println("Usuario registrado correctamente (ID: " + id + ").");
        System.out.println("");
    }

    public void modificarUsuario() {
        int indice = buscarUsuario();
        if (indice == -1) {
            System.out.println("");
            System.out.println("No se encontro el usuario.");
            System.out.println("");
        } else {
            ArrayList<ObjUsuario> misUsuarios = Almacen.listarUsuarios();
            ObjUsuario usuarioEditar = misUsuarios.get(indice);

            System.out.println("");
            System.out.println("Usuario encontrado:");
            System.out.println("Usuario: " + usuarioEditar.getUsuario());
            System.out.println("Rol: "     + usuarioEditar.getRol());
            System.out.println("");

            String contrasena;
            do {
                System.out.println("Digite la nueva contrasena: ");
                contrasena = leer.nextLine();
                if (contrasena.isEmpty()) {
                    System.out.println("La contrasena no puede quedar vacia.");
                }
            } while (contrasena.isEmpty());
            usuarioEditar.setContrasena(contrasena);

            int opcionRol = 0;
            boolean rolValido = false;
            do {
                System.out.println("");
                System.out.println("Seleccione el nuevo Rol:");
                System.out.println("1. Administrador");
                System.out.println("2. Recepcionista");
                System.out.print("Opcion: ");
                try {
                    opcionRol = leer.nextInt();
                    if (opcionRol == 1 || opcionRol == 2) {
                        rolValido = true;
                    } else {
                        System.out.println("Opcion invalida.");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Debe digitar un numero entero.");
                    leer.next();
                }
            } while (!rolValido);
            leer.nextLine();
            usuarioEditar.setRol(opcionRol == 1 ? "Administrador" : "Recepcionista");

            Almacen.editarUsuario(indice, usuarioEditar);
            Almacen.escribeArchivoUsuarios();

            System.out.println("");
            System.out.println("Usuario modificado correctamente.");
            System.out.println("");
        }
    }

    public void borrarUsuario() {
        int indice = buscarUsuario();
        if (indice == -1) {
            System.out.println("");
            System.out.println("No se encontro el usuario.");
            System.out.println("");
        } else {
            ArrayList<ObjUsuario> misUsuarios = Almacen.listarUsuarios();
            ObjUsuario usuarioEliminar = misUsuarios.get(indice);

            System.out.println("");
            System.out.println("Usuario encontrado:");
            System.out.println("Usuario: " + usuarioEliminar.getUsuario());
            System.out.println("Rol: "     + usuarioEliminar.getRol());
            System.out.println("");

            if (usuarioEliminar.getEstado() == 0) {
                System.out.println("Ese usuario ya estaba eliminado.");
                System.out.println("");
            } else {
                usuarioEliminar.setEstado(0);

                Almacen.editarUsuario(indice, usuarioEliminar);
                Almacen.escribeArchivoUsuarios();

                System.out.println("Usuario eliminado correctamente.");
                System.out.println("");
            }
        }
    }

    public void mostrarUsuarios() {
        System.out.println("---------------------------------------");
        System.out.println("|          LISTADO DE USUARIOS        |");
        System.out.println("---------------------------------------");
        System.out.println("");

        ArrayList<ObjUsuario> misUsuarios = Almacen.listarUsuarios();
        for (int i = 0; i < misUsuarios.size(); i++) {
            ObjUsuario miUsuario = misUsuarios.get(i);
            if (miUsuario.getEstado() == 1) {
                System.out.println("Identificador: " + miUsuario.getId());
                System.out.println("Usuario: "       + miUsuario.getUsuario());
                System.out.println("Rol: "           + miUsuario.getRol());
                System.out.println("");
                System.out.println("---------------------------------------");
            }
        }
    }

    //-- Buscar un usuario puntual y mostrar sus datos
    public void consultarUsuario() {
        int indice = buscarUsuario();
        if (indice == -1) {
            System.out.println("");
            System.out.println("No se encontro el usuario.");
            System.out.println("");
        } else {
            ObjUsuario miUsuario = obtenerUsuario(indice);

            System.out.println("");
            System.out.println("Identificador: " + miUsuario.getId());
            System.out.println("Usuario: "       + miUsuario.getUsuario());
            System.out.println("Rol: "           + miUsuario.getRol());
            System.out.println("Estado: "        + (miUsuario.getEstado() == 1 ? "Activo" : "Inactivo"));
            System.out.println("");
        }
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
        System.out.println("");
        System.out.println("---------------------------------------");
        System.out.println("|         BUSCAR  HABITACION          |");
        System.out.println("---------------------------------------");
        System.out.println("");

        int id = 0;
        boolean idValido = false;
        do {
            System.out.println("Digite el identificador de la habitacion: ");
            try {
                id = leer.nextInt();
                idValido = true;
            } catch (InputMismatchException ex) {
                System.out.println("Debe digitar un numero entero.");
                leer.next();
            }
        } while (!idValido);

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
        System.out.println("");
        System.out.println("---------------------------------------");
        System.out.println("|       REGISTRAR HABITACION          |");
        System.out.println("---------------------------------------");
        System.out.println("");

        int id = siguienteHabitacion();
        System.out.println("Identificador: " + id);
        nuevaHabitacion.setId(id);

        String tipHab;
        do {
            System.out.println("");
            System.out.println("Digite el tipo de Habitacion: ");
            tipHab = leer.nextLine().trim();
            if (tipHab.isEmpty()) {
                System.out.println("El tipo de habitacion no puede quedar vacio.");
            }
        } while (tipHab.isEmpty());
        nuevaHabitacion.setTipoHabitacion(tipHab);

        String edificio;
        do {
            System.out.println("");
            System.out.println("Digite el nombre del Edificio: ");
            edificio = leer.nextLine().trim();
            if (edificio.isEmpty()) {
                System.out.println("El edificio no puede quedar vacio.");
            }
        } while (edificio.isEmpty());
        nuevaHabitacion.setEdificio(edificio);

        int piso = 0;
        boolean pisoValido = false;
        do {
            System.out.println("");
            System.out.println("Digite el numero de piso: ");
            try {
                piso = leer.nextInt();
                if (piso < 0) {
                    System.out.println("El piso no puede ser negativo.");
                } else {
                    pisoValido = true;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Debe digitar un numero entero.");
                leer.next();
            }
        } while (!pisoValido);
        nuevaHabitacion.setPiso(piso);

         double costo = 0;
        boolean costoValido = false;
        do {
            System.out.println("");
            System.out.println("Digite el costo por noche de la habitacion: ");
            try {
                costo = leer.nextDouble();
                if (costo <= 0) {
                    System.out.println("El costo debe ser mayor a cero.");
                } else {
                    costoValido = true;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Debe digitar un numero valido.");
                leer.next();
            }
        } while (!costoValido);
        leer.nextLine();
        nuevaHabitacion.setCostoPorNoche(costo);

        nuevaHabitacion.setEstado(1);

        Almacen.agregarHabitacion(nuevaHabitacion);
        Almacen.escribeArchivoHabitaciones();
        
        System.out.println("");
        System.out.println("Habitacion registrada correctamente (ID: " + id + ").");
        System.out.println("");
    }
    
    public void modificarHabitacion(){
        int indice = buscarHabitacion();
        if (indice == -1){
            System.out.println("");
            System.out.println("No se encontro la habitacion.");
            System.out.println("");
        } else {
            ArrayList<ObjHabitacion> misHabitaciones = Almacen.listarHabitaciones();
            ObjHabitacion habitacion = misHabitaciones.get(indice);
            leer.nextLine();

            System.out.println("");
            System.out.println("Habitacion encontrada:");
            System.out.println("Tipo: "            + habitacion.getTipoHabitacion());
            System.out.println("Edificio: "        + habitacion.getEdificio());
            System.out.println("Piso: "            + habitacion.getPiso());
            System.out.println("Costo por Noche: " + habitacion.getCostoPorNoche());
            System.out.println("");

            String tipHab;
            do {
                System.out.println("");
                System.out.println("Digite el nuevo tipo de Habitacion: ");
                tipHab = leer.nextLine().trim();
                if (tipHab.isEmpty()) {
                    System.out.println("El tipo de habitacion no puede quedar vacio.");
                }
            } while (tipHab.isEmpty());
            habitacion.setTipoHabitacion(tipHab);

            String edificio;
            do {
                System.out.println("");
                System.out.println("Digite el nuevo nombre del Edificio: ");
                edificio = leer.nextLine().trim();
                if (edificio.isEmpty()) {
                    System.out.println("El edificio no puede quedar vacio.");
                }
            } while (edificio.isEmpty());
            habitacion.setEdificio(edificio);

            int piso = 0;
            boolean pisoValido = false;
            do {
                System.out.println("");
                System.out.println("Digite el nuevo numero de piso: ");
                try {
                    piso = leer.nextInt();
                    if (piso < 0) {
                        System.out.println("El piso no puede ser negativo.");
                    } else {
                        pisoValido = true;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Debe digitar un numero entero.");
                    leer.next();
                }
            } while (!pisoValido);
            habitacion.setPiso(piso);

            double costo = 0;
            boolean costoValido = false;
            do {
                System.out.println("");
                System.out.println("Digite el nuevo costo por noche: ");
                try {
                    costo = leer.nextDouble();
                    if (costo <= 0) {
                        System.out.println("El costo debe ser mayor a cero.");
                    } else {
                        costoValido = true;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Debe digitar un numero valido.");
                    leer.next();
                }
            } while (!costoValido);
            leer.nextLine();
            habitacion.setCostoPorNoche(costo);

            Almacen.editarHabitacion(indice, habitacion);
            Almacen.escribeArchivoHabitaciones();

            System.out.println("");
            System.out.println("Habitacion modificada correctamente.");
            System.out.println("");
        }
    }
    
    public void borrarHabitacion(){
        int indice = buscarHabitacion();
        if (indice == -1){
            System.out.println("");
            System.out.println("No se encontro la habitacion.");
            System.out.println("");
        } else {
            ArrayList<ObjHabitacion> misHabitaciones = Almacen.listarHabitaciones();
            ObjHabitacion habitacion = misHabitaciones.get(indice);

            System.out.println("");
            System.out.println("Habitacion encontrada:");
            System.out.println("Tipo: "     + habitacion.getTipoHabitacion());
            System.out.println("Edificio: " + habitacion.getEdificio());
            System.out.println("");

            if (habitacion.getEstado() == 0) {
                System.out.println("Esa habitacion ya estaba eliminada.");
                System.out.println("");
            } else {
                habitacion.setEstado(0);

                Almacen.editarHabitacion(indice, habitacion);
                Almacen.escribeArchivoHabitaciones();

                System.out.println("Habitacion eliminada correctamente.");
                System.out.println("");
            }
        }
    }
    
    //-- Buscar una habitacion puntual y mostrar sus datos
    public void consultarHabitacion(){
        int indice = buscarHabitacion();
        if (indice == -1){
            System.out.println("");
            System.out.println("No se encontro la habitacion.");
            System.out.println("");
        } else {
            ObjHabitacion habitacion = obtenerHabitacion(indice);

            System.out.println("");
            System.out.println("Identificador: "   + habitacion.getId());
            System.out.println("Tipo: "            + habitacion.getTipoHabitacion());
            System.out.println("Edificio: "        + habitacion.getEdificio());
            System.out.println("Piso: "            + habitacion.getPiso());
            System.out.println("Costo por Noche: " + habitacion.getCostoPorNoche());
            System.out.println("Estado: "          + (habitacion.getEstado() == 1 ? "Activa" : "Inactiva"));
            System.out.println("");
            System.out.println("---------------------------------------");
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
    
    public int siguienteCliente() {
        int resultado = 1;
        ArrayList<ObjCliente> misClientes = Almacen.listarClientes();
        for (int i = 0; i < misClientes.size(); i++){
            if (resultado <= misClientes.get(i).getId()){
                resultado = misClientes.get(i).getId() + 1;
            }
        }
        return resultado;
    }
    
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
        ObjCliente nuevoCliente = new ObjCliente();
        System.out.println("");
        System.out.println("---------------------------------------");
        System.out.println("|         REGISTRAR CLIENTES           |");
        System.out.println("---------------------------------------");
        System.out.println("");

        int id = siguienteCliente();
        System.out.println("Identificador: " + id);
        nuevoCliente.setId(id);

        String cedula;
        int indiceExistente;
        do {
            System.out.println("");
            System.out.println("Digite la Cedula: ");
            cedula = leer.nextLine().trim();
            if (cedula.isEmpty()) {
                System.out.println("La cedula no puede quedar vacia.");
                indiceExistente = -1;
            } else {
                indiceExistente = buscarClienteActivoPorCedula(cedula);
                if (indiceExistente != -1) {
                    System.out.println("");
                    System.out.println("Ya existe un cliente activo con esa cedula.");
                }
            }
        } while (cedula.isEmpty() || indiceExistente != -1);
        nuevoCliente.setCedula(cedula);

        String nombre;
        do {
            System.out.println("");
            System.out.println("Digite el Nombre: ");
            nombre = leer.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede quedar vacio.");
            }
        } while (nombre.isEmpty());
        nuevoCliente.setNombre(nombre);

        String apellido1;
        do {
            System.out.println("");
            System.out.println("Digite el Primer Apellido: ");
            apellido1 = leer.nextLine().trim();
            if (apellido1.isEmpty()) {
                System.out.println("El primer apellido no puede quedar vacio.");
            }
        } while (apellido1.isEmpty());
        nuevoCliente.setApellido1(apellido1);

        System.out.println("");
        System.out.println("Digite el Segundo Apellido: ");
        String apellido2 = leer.nextLine();
        nuevoCliente.setApellido2(apellido2);

        System.out.println("");
        System.out.println("Digite el Telefono: ");
        String telefono = leer.nextLine();
        nuevoCliente.setTelefono(telefono);

        System.out.println("");
        System.out.println("Digite el Correo: ");
        String correo = leer.nextLine();
        nuevoCliente.setCorreo(correo);

        System.out.println("");
        System.out.println("Digite la Direccion: ");
        String direccion = leer.nextLine();
        nuevoCliente.setDireccion(direccion);

        nuevoCliente.setEstado(1);

        Almacen.agregarCliente(nuevoCliente);
        Almacen.escribeArchivoClientes();

        System.out.println("");
        System.out.println("Cliente registrado correctamente (ID: " + id + ").");
        System.out.println("");
    }
    
    public void modificarCliente(){
        int indice = buscarCliente();
        if (indice == -1){
            System.out.println("");
            System.out.println("No se encontro el cliente.");
            System.out.println("");
        } else {
            ArrayList<ObjCliente> misClientes = Almacen.listarClientes();
            ObjCliente cliente = misClientes.get(indice);

            System.out.println("");
            System.out.println("Cliente encontrado:");
            System.out.println("Cedula: " + cliente.getCedula());
            System.out.println("Nombre: " + cliente.getNombre() + " " + cliente.getApellido1());
            System.out.println("");

            System.out.println("Digite el nuevo Telefono: ");
            String telefono = leer.nextLine();
            cliente.setTelefono(telefono);

            System.out.println("");
            System.out.println("Digite el nuevo Correo: ");
            String correo = leer.nextLine();
            cliente.setCorreo(correo);

            System.out.println("");
            System.out.println("Digite la nueva Direccion: ");
            String direccion = leer.nextLine();
            cliente.setDireccion(direccion);

            Almacen.editarCliente(indice, cliente);
            Almacen.escribeArchivoClientes();

            System.out.println("");
            System.out.println("Cliente modificado correctamente.");
            System.out.println("");
        }
    }
    
    
    public void borrarCliente(){
        int indice = buscarCliente();
        if (indice == -1){
            System.out.println("");
            System.out.println("No se encontro el cliente.");
            System.out.println("");
        } else {
            ArrayList<ObjCliente> misClientes = Almacen.listarClientes();
            ObjCliente cliente = misClientes.get(indice);

            System.out.println("");
            System.out.println("Cliente encontrado:");
            System.out.println("Cedula: " + cliente.getCedula());
            System.out.println("Nombre: " + cliente.getNombre() + " " + cliente.getApellido1());
            System.out.println("");

            if (cliente.getEstado() == 0) {
                System.out.println("Ese cliente ya estaba eliminado.");
                System.out.println("");
            } else {
                cliente.setEstado(0);

                Almacen.editarCliente(indice, cliente);
                Almacen.escribeArchivoClientes();

                System.out.println("");
                System.out.println("Cliente eliminado correctamente.");
                System.out.println("");
            }
        }
    }
    public void mostrarClientes(){
        System.out.println("---------------------------------------");
        System.out.println("|         LISTADO DE CLIENTES         |");
        System.out.println("---------------------------------------");
        System.out.println("");

        ArrayList<ObjCliente> misClientes = Almacen.listarClientes();
        for (int i = 0; i < misClientes.size(); i++){
            ObjCliente cliente = misClientes.get(i);
            if (cliente.getEstado() == 1){
                System.out.println("Id Cliente: " + cliente.getId());
                System.out.println("Nombre: "     + cliente.getNombre() + " " + cliente.getApellido1());
                System.out.println("Cedula: "     + cliente.getCedula());
                System.out.println("Telefono: "   + cliente.getTelefono());
                System.out.println("");
                System.out.println("---------------------------------------");
            }
        }
    }
    
    //-- Buscar un cliente puntual y mostrar sus datos
    public void consultarCliente(){
        int indice = buscarCliente();
        if (indice == -1){
            System.out.println("");
            System.out.println("No se encontro el cliente.");
            System.out.println("");
        } else {
            ObjCliente cliente = obtenerCliente(indice);

            System.out.println("");
            System.out.println("Id Cliente: " + cliente.getId());
            System.out.println("Cedula: "     + cliente.getCedula());
            System.out.println("Nombre: "     + cliente.getNombre() + " " +
                                                  cliente.getApellido1() + " " +
                                                  cliente.getApellido2());
            System.out.println("Telefono: "   + cliente.getTelefono());
            System.out.println("Correo: "     + cliente.getCorreo());
            System.out.println("Direccion: "  + cliente.getDireccion());
            System.out.println("Estado: "     + (cliente.getEstado() == 1 ? "Activo" : "Inactivo"));
            System.out.println("");
        }
    }
    
    //--------------------------------------------
    // Módulo de Empleados
    //--------------------------------------------
    
    public int buscarEmpleadoActivoPorCedula(String cedula) {
        ArrayList<ObjEmpleado> misEmpleados = Almacen.listarEmpleados();
        for (int i = 0; i < misEmpleados.size(); i++) {
            if (misEmpleados.get(i).getCedula().equals(cedula)
                    && misEmpleados.get(i).getEstado() == 1) {
                return i;
            }
        }
        return -1;
    }

    public ObjEmpleado obtenerEmpleado(int indice) {
        return Almacen.listarEmpleados().get(indice);
    }
    
    public int siguienteEmpleado() {
        int resultado = 1;
        ArrayList<ObjEmpleado> misEmpleados = Almacen.listarEmpleados();
        for (int i = 0; i < misEmpleados.size(); i++) {
            if (resultado <= misEmpleados.get(i).getId()) {
                resultado = misEmpleados.get(i).getId() + 1;
            }
        }
        return resultado;
    }
    
    public int buscarEmpleado() {
        System.out.println("---------------------------------------");
        System.out.println("|          BUSCAR  EMPLEADO           |");
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println("Digite la cedula del empleado: ");
        String cedula = leer.nextLine();
        int indice = -1;

        ArrayList<ObjEmpleado> misEmpleados = Almacen.listarEmpleados();
        for (int i = 0; i < misEmpleados.size(); i++) {
            if (misEmpleados.get(i).getCedula().equals(cedula)) {
                indice = i;
                break;
            }
        }
        return indice;
    }
    
    public void insertarEmpleado() {
        ObjEmpleado nuevoEmpleado = new ObjEmpleado();
        System.out.println("");
        System.out.println("---------------------------------------");
        System.out.println("|         REGISTRAR EMPLEADO          |");
        System.out.println("---------------------------------------");
        System.out.println("");

        int id = siguienteEmpleado();
        System.out.println("Identificador: " + id);
        nuevoEmpleado.setId(id);

        String cedula;
        int indiceExistente;
        do {
            System.out.println("");
            System.out.println("Digite la Cedula: ");
            cedula = leer.nextLine().trim();
            if (cedula.isEmpty()) {
                System.out.println("La cedula no puede quedar vacia.");
                indiceExistente = -1;
            } else {
                indiceExistente = buscarEmpleadoActivoPorCedula(cedula);
                if (indiceExistente != -1) {
                    System.out.println("Ya existe un empleado activo con esa cedula.");
                }
            }
        } while (cedula.isEmpty() || indiceExistente != -1);
        nuevoEmpleado.setCedula(cedula);

        String nombre;
        do {
            System.out.println("");
            System.out.println("Digite el Nombre: ");
            nombre = leer.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede quedar vacio.");
            }
        } while (nombre.isEmpty());
        nuevoEmpleado.setNombre(nombre);

        String apellido1;
        do {
            System.out.println("");
            System.out.println("Digite el Primer Apellido: ");
            apellido1 = leer.nextLine().trim();
            if (apellido1.isEmpty()) {
                System.out.println("El primer apellido no puede quedar vacio.");
            }
        } while (apellido1.isEmpty());
        nuevoEmpleado.setApellido1(apellido1);

        System.out.println("");
        System.out.println("Digite el Segundo Apellido: ");
        String apellido2 = leer.nextLine();
        nuevoEmpleado.setApellido2(apellido2);

        System.out.println("");
        System.out.println("Digite el Telefono: ");
        String telefono = leer.nextLine();
        nuevoEmpleado.setTelefono(telefono);

        System.out.println("");
        System.out.println("Digite el Correo: ");
        String correo = leer.nextLine();
        nuevoEmpleado.setCorreo(correo);

        double salario = 0;
        boolean salarioValido = false;
        do {
            System.out.println("");
            System.out.println("Digite el Salario (sin puntos ni comas, ejemplo: 450000): ");
            try {
                salario = leer.nextDouble();
                if (salario <= 0) {
                    System.out.println("");
                    System.out.println("El salario debe ser mayor a cero.");
                } else {
                    salarioValido = true;
                }
            } catch (InputMismatchException ex) {
                System.out.println("");
                System.out.println("Debe digitar un numero valido.");
                leer.next();
            }
        } while (!salarioValido);
        leer.nextLine();
        nuevoEmpleado.setSalario(salario);

        nuevoEmpleado.setEstado(1);

        Almacen.agregarEmpleado(nuevoEmpleado);
        Almacen.escribeArchivoEmpleados();

        System.out.println("");
        System.out.println("Empleado registrado correctamente (ID: " + id + ").");
        System.out.println("");
    }
    
    public void modificarEmpleado() {
        int indice = buscarEmpleado();
        if (indice == -1) {
            System.out.println("");
            System.out.println("No se encontro el empleado.");
            System.out.println("");
        } else {
            ArrayList<ObjEmpleado> misEmpleados = Almacen.listarEmpleados();
            ObjEmpleado empleado = misEmpleados.get(indice);

            System.out.println("");
            System.out.println("Empleado encontrado:");
            System.out.println("Nombre: " + empleado.getNombre() + " " + empleado.getApellido1());
            System.out.println("Cedula: " + empleado.getCedula());
            System.out.println("");

            System.out.println("Digite el nuevo Telefono: ");
            String telefono = leer.nextLine();
            empleado.setTelefono(telefono);

            System.out.println("Digite el nuevo Correo: ");
            String correo = leer.nextLine();
            empleado.setCorreo(correo);

            double salario = 0;
            boolean salarioValido = false;
            do {
                System.out.println("Digite el nuevo Salario (sin puntos ni comas, ejemplo: 450000): ");
                try {
                    salario = leer.nextDouble();
                    if (salario <= 0) {
                        System.out.println("El salario debe ser mayor a cero.");
                    } else {
                        salarioValido = true;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Debe digitar un numero valido.");
                    leer.next();
                }
            } while (!salarioValido);
            leer.nextLine();
            empleado.setSalario(salario);

            Almacen.editarEmpleado(indice, empleado);
            Almacen.escribeArchivoEmpleados();

            System.out.println("");
            System.out.println("Empleado modificado correctamente.");
            System.out.println("");
        }
    }
    
    public void borrarEmpleado() {
        int indice = buscarEmpleado();
        if (indice == -1) {
            System.out.println("");
            System.out.println("No se encontro el empleado.");
            System.out.println("");
        } else {
            ArrayList<ObjEmpleado> misEmpleados = Almacen.listarEmpleados();
            ObjEmpleado empleado = misEmpleados.get(indice);

            System.out.println("");
            System.out.println("Empleado encontrado:");
            System.out.println("Nombre: " + empleado.getNombre() + " " + empleado.getApellido1());
            System.out.println("Cedula: " + empleado.getCedula());
            System.out.println("");

            if (empleado.getEstado() == 0) {
                System.out.println("Ese empleado ya estaba eliminado.");
                System.out.println("");
            } else {
                empleado.setEstado(0);

                Almacen.editarEmpleado(indice, empleado);
                Almacen.escribeArchivoEmpleados();

                System.out.println("Empleado eliminado correctamente.");
                System.out.println("");
            }
        }
    }
    
    public void mostrarEmpleados() {
        System.out.println("---------------------------------------");
        System.out.println("|         LISTADO DE EMPLEADOS        |");
        System.out.println("---------------------------------------");
        System.out.println("");

        ArrayList<ObjEmpleado> misEmpleados = Almacen.listarEmpleados();
        for (int i = 0; i < misEmpleados.size(); i++) {
            ObjEmpleado empleado = misEmpleados.get(i);
            if (empleado.getEstado() == 1) {
                System.out.println("Identificador: " + empleado.getId());
                System.out.println("Cedula: "       + empleado.getCedula());
                System.out.println("Nombre: "       + empleado.getNombre() + " " +
                                                        empleado.getApellido1() + " " +
                                                        empleado.getApellido2());
                System.out.println("Telefono: "     + empleado.getTelefono());
                System.out.println("Correo: "       + empleado.getCorreo());
                System.out.println("Salario: "      + empleado.getSalario());
                System.out.println("");
                System.out.println("---------------------------------------");
            }
        }
    }
    
    //-- Buscar un empleado puntual y mostrar sus datos
    public void consultarEmpleado() {
        int indice = buscarEmpleado();
        if (indice == -1) {
            System.out.println("");
            System.out.println("No se encontro el empleado.");
            System.out.println("");
        } else {
            ObjEmpleado empleado = obtenerEmpleado(indice);

            System.out.println("");
            System.out.println("Identificador: " + empleado.getId());
            System.out.println("Cedula: "        + empleado.getCedula());
            System.out.println("Nombre: "        + empleado.getNombre() + " " +
                                                     empleado.getApellido1() + " " +
                                                     empleado.getApellido2());
            System.out.println("Telefono: "      + empleado.getTelefono());
            System.out.println("Correo: "        + empleado.getCorreo());
            System.out.println("Salario: "       + empleado.getSalario());
            System.out.println("Estado: "        + (empleado.getEstado() == 1 ? "Activo" : "Inactivo"));
            System.out.println("");
        }
    }
    
    //--------------------------------------------
    // Módulo de Reservaciones
    //--------------------------------------------
    
    //-- Revisa si una habitacion ya tiene otra reservacion activa en ese rango de fechas
    public boolean existeSolapamientoReserva(int idHabitacion, Date ingreso, Date salida, int idReservacionExcluir) {
        ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();
        for (int i = 0; i < misReservaciones.size(); i++) {
            ObjReservacion reservacion = misReservaciones.get(i);
            if (reservacion.getId() != idReservacionExcluir
                    && reservacion.getIdHabitacion() == idHabitacion
                    && reservacion.getEstado() == 1
                    && !reservacion.getEstadoReservacion().equals("Check-Out")
                    && ingreso.before(reservacion.getSalida())
                    && salida.after(reservacion.getIngreso())) {
                return true;
            }
        }
        return false;
    }
    
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

        int id = 0;
        boolean idValido = false;
        do {
            System.out.println("Digite el id de la reservacion: ");
            try {
                id = leer.nextInt();
                idValido = true;
            } catch (InputMismatchException ex) {
                System.out.println("Debe digitar un numero entero.");
                leer.next();
            }
        } while (!idValido);

        int indice = -1;
        ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();
        for (int i = 0; i < misReservaciones.size(); i++) {
            ObjReservacion reservacion = misReservaciones.get(i);
            if (reservacion.getId() == id) {
                indice = i;
                break;
            }
        }
        return indice;
    }
    
    
    public void insertarReservacion() {
        ObjReservacion nuevaReservacion = new ObjReservacion();

        System.out.println("---------------------------------------");
        System.out.println("|      REGISTRO DE RESERVACIONES      |");
        System.out.println("---------------------------------------");
        System.out.println("");

        int id = siguienteReservacion();
        System.out.println("Identificador de la reservacion " + id);
        nuevaReservacion.setId(id);

        // Id de la habitacion
        int idHabitacion = 0;
        int indiceHabitacion = -1;
        boolean habitacionValida = false;
        do {
            System.out.println("");
            System.out.println("Digite el id de la habitacion: ");
            try {
                idHabitacion = leer.nextInt();
                indiceHabitacion = buscarHabitacionActivaPorId(idHabitacion);
                if (indiceHabitacion == -1) {
                    System.out.println("");
                    System.out.println("La habitacion digitada no existe o esta inactiva.");
                } else {
                    habitacionValida = true;
                }
            } catch (InputMismatchException ex) {
                System.out.println("");
                System.out.println("Debe digitar un numero entero.");
                leer.next();
            }
        } while (!habitacionValida);
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

        // Fechas de ingreso y salida juntas, para poder corregir ambas si hay choque
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        Date fechaIngreso;
        Date fechaSalida;
        boolean fechasValidas;
        do {
            fechaIngreso = null;
            do {
                System.out.println("");
                System.out.println("Digite la fecha de ingreso (dd/MM/yyyy): ");
                String textoFecha = leer.nextLine();
                try {
                    fechaIngreso = formato.parse(textoFecha);
                } catch (ParseException ex) {
                    System.out.println("Fecha invalida, intente de nuevo.");
                }
            } while (fechaIngreso == null);

            fechaSalida = null;
            do {
                System.out.println("");
                System.out.println("Digite la fecha de salida (dd/MM/yyyy): ");
                String textoFecha = leer.nextLine();
                try {
                    fechaSalida = formato.parse(textoFecha);
                    if (!fechaSalida.after(fechaIngreso)) {
                        System.out.println("La fecha de salida debe ser posterior a la fecha de ingreso.");
                        fechaSalida = null;
                    }
                } catch (ParseException ex) {
                    System.out.println("Fecha invalida, intente de nuevo.");
                }
            } while (fechaSalida == null);

            if (existeSolapamientoReserva(idHabitacion, fechaIngreso, fechaSalida, -1)) {
                System.out.println("");
                System.out.println("Esa habitacion ya tiene una reservacion que se cruza con esas fechas.");
                System.out.println("Digite el rango de fechas nuevamente.");
                fechasValidas = false;
            } else {
                fechasValidas = true;
            }
        } while (!fechasValidas);
        nuevaReservacion.setIngreso(fechaIngreso);
        nuevaReservacion.setSalida(fechaSalida);

        // Calculo del monto
        ObjHabitacion habitacion = obtenerHabitacion(indiceHabitacion);
        double costoPorNoche = habitacion.getCostoPorNoche();
        long diferencia = fechaSalida.getTime() - fechaIngreso.getTime();
        long noches = diferencia / (1000 * 60 * 60 * 24);
        double monto = noches * costoPorNoche;
        nuevaReservacion.setMonto(monto);
        System.out.println("");
        System.out.println("Cantidad de noches: " + noches);
        System.out.println("Costo por noche: " + costoPorNoche);
        System.out.println("Monto total de la reservacion: " + monto);

        nuevaReservacion.setEstado(1);
        nuevaReservacion.setEstadoReservacion("Pendiente");

        Almacen.agregarReservacion(nuevaReservacion);
        Almacen.escribeArchivoReservaciones();

        System.out.println("");
        System.out.println("Reservacion registrada correctamente (ID: " + id + ").");
        System.out.println("");
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

         if (reservacion.getEstado() == 0) {
             System.out.println("");
             System.out.println("Esa reservacion fue eliminada, no se puede modificar.");
             System.out.println("");
             return;
         }

         System.out.println("");
         System.out.println("Reservacion encontrada:");
         System.out.println("Habitacion: "   + reservacion.getIdHabitacion());
         System.out.println("Cliente: "      + reservacion.getCedCliente());
         System.out.println("Monto actual: " + reservacion.getMonto());
         System.out.println("");

         // Id de la habitacion
         int idHabitacion = 0;
         int indiceHabitacion = -1;
         boolean habitacionValida = false;
         do {
             System.out.println("Digite el nuevo id de la habitacion: ");
             try {
                 idHabitacion = leer.nextInt();
                 indiceHabitacion = buscarHabitacionActivaPorId(idHabitacion);
                 if (indiceHabitacion == -1) {
                     System.out.println("La habitacion digitada no existe o esta inactiva.");
                 } else {
                     habitacionValida = true;
                 }
             } catch (InputMismatchException ex) {
                 System.out.println("Debe digitar un numero entero.");
                 leer.next();
             }
         } while (!habitacionValida);
         reservacion.setIdHabitacion(idHabitacion);
         leer.nextLine();

         // Cedula del cliente
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

         // Fechas de ingreso y salida juntas
         SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
         formato.setLenient(false);
         Date fechaIngreso;
         Date fechaSalida;
         boolean fechasValidas;
         do {
             fechaIngreso = null;
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

             fechaSalida = null;
             do {
                 System.out.println("");
                 System.out.println("Digite la nueva fecha de salida (dd/MM/yyyy): ");
                 String textoFecha = leer.nextLine();
                 try {
                     fechaSalida = formato.parse(textoFecha);
                     if (!fechaSalida.after(fechaIngreso)) {
                         System.out.println("La fecha de salida debe ser posterior a la fecha de ingreso.");
                         fechaSalida = null;
                     }
                 } catch (ParseException ex) {
                     System.out.println("Fecha invalida, intente de nuevo.");
                 }
             } while (fechaSalida == null);

             if (existeSolapamientoReserva(idHabitacion, fechaIngreso, fechaSalida, reservacion.getId())) {
                 System.out.println("");
                 System.out.println("Esa habitacion ya tiene otra reservacion que se cruza con esas fechas.");
                 System.out.println("Digite el rango de fechas nuevamente.");
                 fechasValidas = false;
             } else {
                 fechasValidas = true;
             }
         } while (!fechasValidas);
         reservacion.setIngreso(fechaIngreso);
         reservacion.setSalida(fechaSalida);

         // Recalculo del monto
         ObjHabitacion habitacion = obtenerHabitacion(indiceHabitacion);
         double costoPorNoche = habitacion.getCostoPorNoche();
         long diferencia = fechaSalida.getTime() - fechaIngreso.getTime();
         long noches = diferencia / (1000 * 60 * 60 * 24);
         double monto = noches * costoPorNoche;
         reservacion.setMonto(monto);
         System.out.println("");
         System.out.println("Cantidad de noches: " + noches);
         System.out.println("Costo por noche: "    + costoPorNoche);
         System.out.println("Nuevo monto total: "  + monto);

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

            if (reservacion.getEstado() == 0) {
                System.out.println("Esa reservacion ya estaba eliminada.");
                System.out.println("");
            } else {
                reservacion.setEstado(0);

                Almacen.editarReservacion(indice, reservacion);
                Almacen.escribeArchivoReservaciones();

                System.out.println("Reservacion eliminada correctamente.");
                System.out.println("");
            }
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
                System.out.println("Identificador: "        + reservacion.getId());
                System.out.println("Habitacion: "           + reservacion.getIdHabitacion());
                System.out.println("Cliente: "              + reservacion.getCedCliente());
                System.out.println("Ingreso: "              + formato.format(reservacion.getIngreso()));
                System.out.println("Salida: "               + formato.format(reservacion.getSalida()));
                System.out.println("Monto: "                + reservacion.getMonto());
                System.out.println("Estado de la reserva: " + reservacion.getEstadoReservacion());
                System.out.println("");
                System.out.println("---------------------------------------");
            }
        }
    }
 
    
    //-- Buscar una reservacion puntual y mostrar sus datos
    public void consultarReservacion() {
        int indice = buscarReservacion();
        if (indice == -1) {
            System.out.println("");
            System.out.println("No se encontro la reservacion.");
            System.out.println("");
        } else {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();
            ObjReservacion reservacion = misReservaciones.get(indice);

            System.out.println("");
            System.out.println("Identificador: "        + reservacion.getId());
            System.out.println("Habitacion: "           + reservacion.getIdHabitacion());
            System.out.println("Cliente: "              + reservacion.getCedCliente());
            System.out.println("Ingreso: "              + formato.format(reservacion.getIngreso()));
            System.out.println("Salida: "               + formato.format(reservacion.getSalida()));
            System.out.println("Monto: "                + reservacion.getMonto());
            System.out.println("Estado de la reserva: " + reservacion.getEstadoReservacion());
            System.out.println("Estado: "               + (reservacion.getEstado() == 1 ? "Activa" : "Inactiva"));
            System.out.println("");
        }
    }

    //--------------------------------------------
    // Módulo de Check-In / Check-Out
    //--------------------------------------------

    public void registrarCheckIn() {
        int indice = buscarReservacion();
        if (indice == -1) {
            System.out.println("");
            System.out.println("No se encontro la reservacion.");
            System.out.println("");
        } else {
            ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();
            ObjReservacion reservacion = misReservaciones.get(indice);

            if (reservacion.getEstado() == 0) {
                System.out.println("");
                System.out.println("Esa reservacion fue eliminada.");
                System.out.println("");
            } else if (!reservacion.getEstadoReservacion().equals("Pendiente")) {
                System.out.println("");
                System.out.println("Esa reservacion ya tiene Check-In o Check-Out registrado (estado actual: "
                                     + reservacion.getEstadoReservacion() + ").");
                System.out.println("");
            } else {
                reservacion.setEstadoReservacion("Check-In");

                Almacen.editarReservacion(indice, reservacion);
                Almacen.escribeArchivoReservaciones();

                System.out.println("");
                System.out.println("Check-In registrado correctamente.");
                System.out.println("Habitacion: " + reservacion.getIdHabitacion());
                System.out.println("Cliente: "    + reservacion.getCedCliente());
                System.out.println("");
            }
        }
    }
    
    public void registrarCheckOut() {
        int indice = buscarReservacion();
        if (indice == -1) {
            System.out.println("");
            System.out.println("No se encontro la reservacion.");
            System.out.println("");
        } else {
            ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();
            ObjReservacion reservacion = misReservaciones.get(indice);

            if (!reservacion.getEstadoReservacion().equals("Check-In")) {
                System.out.println("");
                System.out.println("Esa reservacion no tiene un Check-In activo (estado actual: "
                                     + reservacion.getEstadoReservacion() + ").");
                System.out.println("");
            } else {
                reservacion.setEstadoReservacion("Check-Out");

                Almacen.editarReservacion(indice, reservacion);
                Almacen.escribeArchivoReservaciones();

                System.out.println("");
                System.out.println("Check-Out registrado correctamente.");
                System.out.println("Habitacion: "     + reservacion.getIdHabitacion());
                System.out.println("Cliente: "        + reservacion.getCedCliente());
                System.out.println("Monto a cobrar: " + reservacion.getMonto());
                System.out.println("");
            }
        }
    }
    
    //--------------------------------------------
    // Módulo de Reportes
    //--------------------------------------------

    public void reporteReservaciones() {
        System.out.println("----------------------------------------");
        System.out.println("|        REPORTE DE RESERVACIONES       |");
        System.out.println("----------------------------------------");
        System.out.println("");

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();
        double totalGeneral = 0;

        System.out.printf("%-5s %-10s %-14s %-12s %-12s %-10s %-10s%n",
                "ID", "Habitacion", "Cliente", "Ingreso", "Salida", "Monto", "Estado");
        System.out.println("--------------------------------------------------------------------");

        for (int i = 0; i < misReservaciones.size(); i++) {
            ObjReservacion reservacion = misReservaciones.get(i);
            if (reservacion.getEstado() == 1) {
                System.out.printf("%-5d %-10d %-14s %-12s %-12s %-10.2f %-10s%n",
                        reservacion.getId(),
                        reservacion.getIdHabitacion(),
                        reservacion.getCedCliente(),
                        formato.format(reservacion.getIngreso()),
                        formato.format(reservacion.getSalida()),
                        reservacion.getMonto(),
                        reservacion.getEstadoReservacion());
                totalGeneral += reservacion.getMonto();
            }
        }
        System.out.println("");
        System.out.printf("Monto total de reservaciones activas: %.2f%n", totalGeneral);
        System.out.println("");
    }
    
    public void reporteOcupacion() {
        System.out.println("----------------------------------------");
        System.out.println("|         REPORTE DE OCUPACION          |");
        System.out.println("----------------------------------------");
        System.out.println("");

        ArrayList<ObjHabitacion> misHabitaciones = Almacen.listarHabitaciones();
        ArrayList<ObjReservacion> misReservaciones = Almacen.listarReservaciones();

        int totalHabitaciones = 0;
        int habitacionesOcupadas = 0;

        System.out.printf("%-8s %-18s %-12s %-14s%n", "ID", "Tipo", "Estado", "Cliente Actual");
        System.out.println("----------------------------------------------------");

        for (int i = 0; i < misHabitaciones.size(); i++) {
            ObjHabitacion habitacion = misHabitaciones.get(i);
            if (habitacion.getEstado() == 1) {
                totalHabitaciones++;
                boolean ocupada = false;
                String clienteActual = "-";

                for (int j = 0; j < misReservaciones.size(); j++) {
                    ObjReservacion reservacion = misReservaciones.get(j);
                    if (reservacion.getIdHabitacion() == habitacion.getId()
                            && reservacion.getEstado() == 1
                            && reservacion.getEstadoReservacion().equals("Check-In")) {
                        ocupada = true;
                        clienteActual = reservacion.getCedCliente();
                        break;
                    }
                }

                if (ocupada) {
                    habitacionesOcupadas++;
                }

                System.out.printf("%-8d %-18s %-12s %-14s%n", habitacion.getId(),
                        habitacion.getTipoHabitacion(),
                        ocupada ? "Ocupada" : "Disponible",
                        clienteActual);
            }
        }

        System.out.println("----------------------------------------------------");
        System.out.println("");

        double porcentaje = 0;
        if (totalHabitaciones > 0) {
            porcentaje = (habitacionesOcupadas * 100.0) / totalHabitaciones;
        }

        System.out.println("Habitaciones activas: "  + totalHabitaciones);
        System.out.println("Habitaciones ocupadas: " + habitacionesOcupadas);
        System.out.printf("Porcentaje de ocupacion: %.2f%%%n", porcentaje);
        System.out.println("");
    }
    
    
}