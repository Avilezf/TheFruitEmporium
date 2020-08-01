/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Ing. Luis Llanos
 */
public class Producto {

    int ide;
    String nombre;
    int precio;
    int cantidad;
    String img;
    int categoria;

    public Producto(int ide, String nombre, int precio, int cantidad, String img, int categoria) {
        this.ide = ide;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.img = img;
        this.categoria = categoria;
    }

    public Producto(String nombre, int precio, int cantidad, String img, int categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.img = img;
        this.categoria = categoria;
    }

    public Producto(int ide) {
        this.ide = ide;
        this.nombre = "";
        this.precio = 0;
        this.cantidad = 0;
        this.img = "";
        this.categoria = 0;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

}
