package com.uem;

public class Article {
    protected String name; 
    protected int cantidad; 
    protected double precio;
    protected double descuento; 


    public Article(String name, int cantidad, double precio, double descuento) {
        this.name = name;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
        System.out.println ("El gross del articulo " + this.name + " es: " + total); 
        return total;
    }
    public double GetDiscountedamount (){
        Calculator c1 = new Calculator (); 
        double total = c1.discount(GetGrossTotal(), this.descuento);
        System.out.println ("El descuento del articulo " + this.name + " es: " + total); 
        return total;
    }


}