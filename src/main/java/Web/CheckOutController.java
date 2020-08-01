/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import Datos.PedidoDAO;
import Datos.PedidosProductosDAO;
import Datos.ProductoDAO;
import Datos.UsuarioDAOJDBC;
import Dominio.Pedido;
import Dominio.PedidosProductos;
import Dominio.Producto;
import Dominio.Usuario;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CL SMA
 */
@WebServlet("/CheckOutController")
public class CheckOutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        String path = ""; //Dirección para llegar al jsp
        if (accion != null) {

            this.accionDefault(request, response, path, accion);
        } else {
            this.accionDefault(request, response, path, accion);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        switch (accion) {
            
            case "checkout":
                String idusuario = request.getParameter("usuario");
                String idpedido = request.getParameter("pedido");
                String subtotal = request.getParameter("subtotal");
                String total = request.getParameter("total");
                String path = ""; //Dirección para llegar al jsp

                try {
                    //Se recupera el usuario
                    Usuario usuario = new UsuarioDAOJDBC().id(new Usuario(idusuario));

                    request.setAttribute("usuario", usuario);
                    request.setAttribute("idpedido", idpedido);
                    request.setAttribute("subtotal", subtotal);
                    request.setAttribute("total", total);

                    this.accionDefault(request, response, path, accion);
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(CheckOutController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response, String path, String accion) throws IOException, ServletException {
        path = "/" + accion + ".jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    private void ActualUser(HttpServletRequest request, HttpServletResponse response, String ip, String path, int order, int ide, String fecha, int cant) throws ServletException, IOException, InstantiationException, IllegalAccessException {
        //Usar usuario existente
        Usuario temp = new Usuario(ip);
        Usuario usuario = new UsuarioDAOJDBC().id(temp);

        Pedido pedido = new PedidoDAO().id(new Pedido(usuario.getIdUsuario(), fecha)); //busca el id del usuario para verificar si tiene pedidos

        if (pedido.getEstado() == 0) {//Si es igual a 0, el pedido o está cancelado

            //Insertar producto
            boolean estado = new PedidosProductosDAO().nuevo(new PedidosProductos(pedido.getIdPedido(), ide, cant));
            if (estado) {//Si es diferente de 0, eso significa que sí pasó
                //Reenviar
                Order(request, response, order, path, pedido.getCarrito());
            }

        } else {
            //El pedido no es nuevo
            PedidosProductos PP = new PedidosProductos(pedido.getIdPedido(), ide, cant);
            boolean prod = new PedidosProductosDAO().verificar2(PP);//Se verifica si el producto está en el carro
            if (prod) {
                //Si el producto ya estaba en el carrito
                PedidosProductos PedidosProductos = new PedidosProductosDAO().buscar(PP);
                //Update
                boolean estado = new PedidosProductosDAO().update(PedidosProductos);
                if (estado) {
                    Order(request, response, order, path, pedido.getCarrito());
                }

            } else {
                //Si el producto NO está en el carrito

                boolean estado = new PedidosProductosDAO().nuevo(PP);
                if (estado) {//Si es diferente de 0, eso significa que sí pasó
                    //Reenviar
                    pedido.setCarrito(pedido.getCarrito() + 1);
                    boolean ok = new PedidoDAO().actualizar(pedido);
                    if (ok) {
                        Order(request, response, order, path, pedido.getCarrito());
                    }
                }
            }

        }
    }

    private void NotActualUser(HttpServletRequest request, HttpServletResponse response, String ip, String path, int order, int ide, String fecha, int cant) throws ServletException, IOException, InstantiationException, IllegalAccessException {
        //Crear el usuario
        Usuario temp = new Usuario(ip);
        int sw = new UsuarioDAOJDBC().insertar(temp);

        if (sw != 0) {
            Usuario usuario = new UsuarioDAOJDBC().id(temp);
            //Creamos un pedido
            Pedido p = new Pedido(usuario.getIdUsuario(), 1, 1, fecha);
            boolean n = new PedidoDAO().nuevo(p);
            if (n) {
                Pedido pedido = new PedidoDAO().id(p);

                //Insertar producto
                boolean estado = new PedidosProductosDAO().nuevo(new PedidosProductos(pedido.getIdPedido(), ide, cant));
                if (estado) {//Si es diferente de 0, eso significa que sí pasó
                    //Reenviar
                    Order(request, response, order, path, pedido.getCarrito());
                }
            }

        }
    }

    private void Order(HttpServletRequest request, HttpServletResponse response, int order, String path, int carrito) throws ServletException, IOException {
        String shop = "shop" + order + "&carrito=" + carrito;
        request.setAttribute("shop", shop);
        switch (String.valueOf(order)) {
            case "0"://Shopping Default
                path = "/redirect.jsp";
                request.getRequestDispatcher(path).forward(request, response);
                break;

            case "1"://Shopping Fruits
                path = "/redirect.jsp";
                request.getRequestDispatcher(path).forward(request, response);
                break;

            case "2"://Shopping Vegetables
                path = "/redirect.jsp";
                request.getRequestDispatcher(path).forward(request, response);
                break;

            case "3"://Shopping Juices
                path = "/redirect.jsp";
                request.getRequestDispatcher(path).forward(request, response);
                break;

            case "4"://Shopping Dried
                path = "/redirect.jsp";
                request.getRequestDispatcher(path).forward(request, response);
                break;

        }
    }

}
