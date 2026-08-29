/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *      @author Jeffry SM
 */
public class ObjHabitacion {
    
    // Atributos 
    
    private int id;
    private String tipoHabitacion;
    private String edificio;
    private int piso;
    private double costoPorNoche;
    private int estado;
    
    
 // Constructor

    public ObjHabitacion() {
    }

    public ObjHabitacion(int id, String tipoHabitacion, String edificio, int piso, double costoPorNoche, int estado) {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.edificio = edificio;
        this.piso = piso;
        this.costoPorNoche = costoPorNoche;
        this.estado = estado;
    }
    
   
 // 4 - Métodos / Acciones
    
 
 public int getId() {
     return id;
 }
 
 public void setId(int id) {
     this.id = id;
 }
 
 public String getTipoHabitacion() {
     return tipoHabitacion;
 }
 
 public void setTipoHabitacion(String tipoHabitacion) {
     this.tipoHabitacion = tipoHabitacion;
 }
 
 public String getEdificio() {
     return edificio;
 }
 
 public void setEdificio(String edificio){
     this.edificio = edificio;
 }
 
 public int getPiso() {
     return piso;
 }
 
 public void setPiso(int piso) {
     this.piso = piso;
 }
 
 public double getCostoPorNoche() {
     return costoPorNoche;
 }
 
 public void setCostoPorNoche(double costoPorNoche) {
     this.costoPorNoche = costoPorNoche;
 }
 
public int getEstado() {
        return estado;
 }

public void setEstado(int estado) {
        this.estado = estado;
 }

    
}
