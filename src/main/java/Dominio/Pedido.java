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

    int idPedido;
    int idCliente;
    int estado;
    int carrito;
    String fecha;
    
    public Pedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(int idCliente, String fecha) {
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estado = 0;
    }

    public Pedido(int idCliente, int estado, int carrito, String fecha) {
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estado = estado;
        this.carrito = carrito;
    }

    public Pedido(int idPedido, int idCliente, String fecha, int estado, int carrito) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estado = estado;
        this.carrito = carrito;
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
    
    

}
