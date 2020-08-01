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
public class PedidosProductos {
    int idpxp;
    int idpedido;
    int idproducto;
    int cantproducto;
    
    public PedidosProductos(int idpedida) {
        this.idpedido = idpedida;
    }

    public PedidosProductos(int idpedida, int idproducto, int cantproducto) {
        this.idpedido = idpedida;
        this.idproducto = idproducto;
        this.cantproducto = cantproducto;
    }

    public PedidosProductos(int idpxp, int idpedida, int idproducto, int cantproducto) {
        this.idpxp = idpxp;
        this.idpedido = idpedida;
        this.idproducto = idproducto;
        this.cantproducto = cantproducto;
    }

    public int getIdpxp() {
        return idpxp;
    }

    public void setIdpxp(int idpxp) {
        this.idpxp = idpxp;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedida) {
        this.idpedido = idpedida;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantproducto() {
        return cantproducto;
    }

    public void setCantproducto(int cantproducto) {
        this.cantproducto = cantproducto;
    }
    
    
}
