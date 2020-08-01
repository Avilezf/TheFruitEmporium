/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import Datos.ProductoDAO;
import Dominio.Producto;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet("/ShoppingController")
public class ShoppingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        String path = ""; //Dirección para llegar al jsp
        if (accion != null) {
            if (!accion.substring(0, 4).equals("shop")) {
                if (accion.equals("buscar")) {
                    try {
                        this.ShopProducto(request, response, path, accion);
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                        Logger.getLogger(ShoppingController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    this.accionDefault(request, response, path, accion);
                }
            } else {
                if (accion.length() == 5) {
                    this.accionCategory(request, response, path, accion);
                }

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        String path = ""; //Dirección para llegar al jsp
        if (accion != null) {
            if (!accion.substring(0, 4).equals("shop")) {
                if (accion.equals("buscar")) {
                    try {
                        this.ShopProducto(request, response, path, accion);
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                        Logger.getLogger(ShoppingController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    this.accionDefault(request, response, path, accion);
                }
            } else {
                if (accion.length() == 5) {
                    this.accionCategory(request, response, path, accion);
                }

            }
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response, String path, String accion) throws IOException, ServletException {
        path = "/" + accion + ".jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    private void accionCategory(HttpServletRequest request, HttpServletResponse response, String path, String accion) throws IOException, ServletException {
        try {
            String num = accion.substring(4, 5);
            List<Producto> productos = new ProductoDAO().listar(num);
            request.setAttribute("productos", productos);
            List<String> numact = add(Integer.parseInt(num));
            request.setAttribute("active", numact);
            String carrito = request.getParameter("carrito");
            request.setAttribute("carrito", carrito);
            switch (num) {
                case "0"://Shopping Default
                    path = "/shop.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                case "1"://Shopping Fruits
                    path = "/shop.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                case "2"://Shopping Vegetables
                    path = "/shop.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                case "3"://Shopping Juices
                    path = "/shop.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                case "4"://Shopping Dried
                    path = "/shop.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

            }
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException ex) {
            System.out.println("--------------------------------Error-------------------------------");
            Logger.getLogger(ShoppingController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("--------------------------------Error-------------------------------");
        }
    }

    private void ShopProducto(HttpServletRequest request, HttpServletResponse response, String path, String accion) throws IOException, ServletException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        int ide = Integer.parseInt(request.getParameter("idProducto"));
        Producto producto = new ProductoDAO().buscar(new Producto(ide));
        request.setAttribute("producto", producto);
        int num = producto.getCategoria();
        List<Producto> productos = new ProductoDAO().seleccionar(String.valueOf(num));
        request.setAttribute("productos", productos);
        String jsp = "/product-single.jsp";
        request.getRequestDispatcher(jsp).forward(request, response);
    }

    private List<String> add(int num) {
        List<String> numero = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            numero.add("");
        }
        numero.set(num, "active");
        numero.set(5, String.valueOf(num));
        return numero;
    }

}
