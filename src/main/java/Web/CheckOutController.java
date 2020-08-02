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
                    Usuario usuario = new UsuarioDAOJDBC().realId(new Usuario(idusuario));

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
