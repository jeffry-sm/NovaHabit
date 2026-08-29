/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *     @author Jeffry SM
 */

/*
Para que una clase, se convierta en un objecto
debe contener las siguientes partes:

1. Declaración - Nombre de la Clase y dentro de la clase:
    2. Atributos / Caracteristicas
    3. Constructor / Constructores
    4. Métodos / Acciones: Qué hace?
*/

public class ObjCliente {   // 1 - Declaración
    
    // 2 - Atributos
    
    private int id;        // identificador único interno
    private String cedula;
    private String nombre;
    private String apellido1;
    private String apellido2; 
    private String telefono;
    private String correo;
    private String direccion;
    private int estado;
    
    
    
    // 3 - constructor

    public ObjCliente() {   // Constructor vacio
    }  
    
    public ObjCliente(int id, String cedula, String nombre, String apellido1, String apellido2, String telefono, String correo, int estado) {
        
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }
    
    
    
    // 4 - Métodos / Acciones
    
    //   Getter y Setter
    
    public int getId() {    // Obtener el valor
        return id;
    }
    
    public void setId( int id ) {  // Asignar el valor del parametro al atributo
        this.id = id;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido1() {
        return apellido1;
    }
    
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    
    public String getApellido2() {
        return apellido2;
    }
    
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
     public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
     public int getEstado() {
        return estado;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
 }

