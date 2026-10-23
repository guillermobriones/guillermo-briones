package com.uem.Model;

import com.uem.Calculator;
import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("name")
    protected String nombre; 
    @SerializedName("quantity")
    protected int cantidad; 
    @SerializedName("unitPrice")
    protected double precio;
    @SerializedName("discount")
    protected double descuento; 


    public Article(String nombre, int cantidad, double precio, double descuento) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getDescuento() {
        return descuento;
    }
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    public double GetGrossTotal(){
        double total = this.cantidad * this.precio;
        System.out.println ("El gross del articulo " + this.nombre + " es: " + total); 
        return total;
    }
    public double GetDiscountedamount (){
        Calculator c1 = new Calculator (); 
        double total = c1.discount(GetGrossTotal(), this.descuento);
        System.out.println ("El descuento del articulo " + this.nombre + " es: " + total); 
        return total;
    }


}