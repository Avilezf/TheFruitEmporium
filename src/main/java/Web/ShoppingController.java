/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import java.io.IOException;
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
        String path; //Dirección para llegar al jsp
        if (accion != null) {
            if (!accion.substring(0, 3).equals("shop")) {
                path = "/"+accion+".jsp";
                request.getRequestDispatcher(path).forward(request, response);
            } else {
                switch (accion) {
                    case "shop"://Shopping Default
                        path = "/shop.jsp";
                        request.getRequestDispatcher(path).forward(request, response);
                        break;

                    case "shopF"://Shopping Fruits
                        path = "/shop.jsp";
                        request.getRequestDispatcher(path).forward(request, response);
                        break;

                    case "shopV"://Shopping Vegetables
                        path = "/shop.jsp";
                        request.getRequestDispatcher(path).forward(request, response);
                        break;

                    case "shopJ"://Shopping Juices
                        path = "/shop.jsp";
                        request.getRequestDispatcher(path).forward(request, response);
                        break;

                    case "ShopS"://Shopping Dried
                        path = "/shop.jsp";
                        request.getRequestDispatcher(path).forward(request, response);
                        break;

                }
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insert":
                    //this.insertUsuario(request, response);
                    break;

                case "login":
                    //this.loginUsuario(request, response);
                    break;
            }
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String jspdir = "/vegefoods/home.jsp";
        request.getRequestDispatcher(jspdir).forward(request, response);
    }

}
