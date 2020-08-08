/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Luis
 */
public class Pedido {
    
     /*
    //PEDIDOS:
    //ESTADOS:
        estado - 0: Pedido apenas creado sin productos
        estado - 1: Pedido con productos
        estado - 2: Pedido cancelado falta verificación
        estado - 3: Pedido aprobado pero no enviado
        estado - 4: Pedido enviado, pasa a ser guardado en el historial.
   */ 

    int idPedido;
    int idCliente;
    int estado;
    int carrito;
    int subtotal;
    int total;
    String fecha;
    int month;
    int year;
    
    public Pedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(int idCliente, String fecha, int month, int year) {
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estado = 0;
        this.carrito = 0;
        this.subtotal = 0;
        this.total = 0;
        this.month = month;
        this.year = year;
    }

    public Pedido(int idCliente, int estado, int carrito, String fecha, int month, int year) {
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estado = estado;
        this.carrito = carrito;
        this.month = month;
        this.year = year;
    }

    public Pedido(int idPedido, int idCliente, String fecha, int estado, int carrito, int subtotal, int total, int year, int month) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estado = estado;
        this.carrito = carrito;
        this.subtotal = subtotal;
        this.total = total;
        this.month = month;
        this.year = year;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCarrito() {
        return carrito;
    }

    public void setCarrito(int carrito) {
        this.carrito = carrito;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    

}
