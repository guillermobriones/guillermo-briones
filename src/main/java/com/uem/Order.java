package com.uem;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Order {

    @SerializedName("id")
    protected String Id;

    public List<Article> articles;
    public Order() {
    }

    public Order(String Id, List<Article> articles) {
        this.Id = Id;
        this.articles = articles;
    }
    public String getId() {
        return Id;
    }
    public void setId(String Id) {
        this.Id = Id;
    } 

    public List<Article> getArticles() {
        return articles;
    }
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    public double GetGrossTotal (){
        double total = 0;
        for (Article article : articles) {
            total += article.GetGrossTotal();
        }
        return total;
    }

    public double GetDiscountedTotal (){
        double total = 0;
        for (Article article : articles) {
            total += article.GetDiscountedamount();
        }
        return total;
    }
    public String ToString(){
        for (Article article : articles) {
            System.out.println("Articulo: " + article.getName() + ", Cantidad: " + article.getCantidad() + ", Precio: " + article.getPrecio() + ", Descuento: " + article.getDescuento());
        }
        return "";
    }
}