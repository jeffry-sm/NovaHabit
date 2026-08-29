/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.util.Date;

/**
 *        @author Jeffry SM
 */
public class ObjReservacion {
    
    // Atributos
    
    private int id;           
    private int idHabitacion;
    private String cedCliente;
    private Date ingreso;
    private Date salida;
    private double monto;
    private int estado;
    

    // Constructor

    public ObjReservacion() {
    }

    public ObjReservacion(int id, int idHabitacion, String cedCliente, Date ingreso, Date salida, double monto, int estado) {
        this.id = id;
        this.idHabitacion = idHabitacion;
        this.cedCliente = cedCliente;
        this.ingreso = ingreso;
        this.salida = salida;
        this.monto = monto;
        this.estado = estado;
    }
    
    
    // 4Getter y Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getCedCliente() {
        return cedCliente;
    }

    public void setCedCliente(String cedCliente) {
        this.cedCliente = cedCliente;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
