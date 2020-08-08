/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import Datos.PedidoDAO;
import Datos.PedidosProductosDAO;
import Datos.ProductoDAO;
import Datos.SendMail;
import Datos.UsuarioDAOJDBC;
import Dominio.Pedido;
import Dominio.Producto;
import Dominio.Usuario;
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
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        String path; //Dirección para llegar al jsp
        if (accion != null) {
            switch (accion) {
                case "pedidos": 
                    try {
                    this.pedidos(request, response);
                    path = "/admin.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

                case "envio": 
                    try {
                    this.envios(request, response);
                    path = "/admin2.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

                case "validate": {
                    try {
                        this.validate(request, response);
                        path = "/validate.jsp";
                        request.getRequestDispatcher(path).forward(request, response);
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case "ok2": {
                    try {
                        this.ok2(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case "buscar": {
                    path = "/admin31.jsp";
                    request.getRequestDispatcher(path).forward(request, response);

                }
                break;

                case "no": {
                    path = "/validate.jsp";
                    request.getRequestDispatcher(path).forward(request, response);

                }
                break;

//                case "addproduct":
//
//                    break;
//
//                case "drpproduct":
//
//                    break;
//
//                case "modproduct":
//
//                    break;                
                default:
                    path = "/home.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;
            }

        } else {
            this.accionDefault(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "registro": {
                    try {
                        this.registro(request, response);
                        String path = "/admin3.jsp";
                        request.getRequestDispatcher(path).forward(request, response);
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case "ok": {
                    try {
                        this.ok(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                
                case "no": {
                    try {
                        this.no(request, response);
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }

        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String jspdir = "/home.jsp";
        request.getRequestDispatcher(jspdir).forward(request, response);
    }

    private void pedidos(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        //Verificar si hay pedidos disponibles:
        boolean ok = new PedidoDAO().estado2();
        if (ok) {
            //SI existen pedidos disponibles
            List<Pedido> pedidos = new ArrayList<Pedido>();
            pedidos = new PedidoDAO().estado2(2);
            List<Usuario> clientes = new ArrayList<Usuario>();
            for (Pedido pedido : pedidos) {
                Usuario cliente = new UsuarioDAOJDBC().realId(new Usuario(pedido.getIdCliente()));
                clientes.add(cliente);
            }
            int size = clientes.size() - 1;
            request.setAttribute("size", size);
            request.setAttribute("clientes", clientes);
            request.setAttribute("pedidos", pedidos);
        } else {
            //No existen pedidos disponibles

        }
    }

    private void envios(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        boolean ok = new PedidoDAO().estado3();
        if (ok) {
            //SI existen pedidos disponibles
            List<Pedido> pedidos = new ArrayList<Pedido>();
            pedidos = new PedidoDAO().estado2(3);
            List<Usuario> clientes = new ArrayList<Usuario>();
            for (Pedido pedido : pedidos) {
                Usuario cliente = new UsuarioDAOJDBC().realId(new Usuario(pedido.getIdCliente()));
                clientes.add(cliente);
            }
            int size = clientes.size() - 1;
            request.setAttribute("size", size);
            request.setAttribute("clientes", clientes);
            request.setAttribute("pedidos", pedidos);
        } else {
            //No existen envios disponibles

        }
    }

    private void validate(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String idPedido = request.getParameter("idPedido");
            Pedido pedido = new PedidoDAO().realId(new Pedido(Integer.parseInt(idPedido)));
            Usuario usuario = new UsuarioDAOJDBC().realId(new Usuario(pedido.getIdCliente()));
            List<Integer> idProductos = new ArrayList<Integer>();
            idProductos = new PedidosProductosDAO().listarIds(pedido);
            List<Integer> cantProductos = new ArrayList<Integer>();
            cantProductos = new PedidosProductosDAO().listarCants(pedido);
            List<Producto> productos = new ArrayList<Producto>();
            for (Integer idProducto : idProductos) {
                Producto producto = new ProductoDAO().buscar(new Producto(idProducto));
                productos.add(producto);
            }
            List<Integer> precios = new ArrayList<Integer>();
            for (int i = 0; i < productos.size(); i++) {
                int precio = cantProductos.get(i) * productos.get(i).getPrecio();
                precios.add(precio);
            }

            int size = productos.size() - 1;
            request.setAttribute("size", size);
            request.setAttribute("precios", precios);
            request.setAttribute("productos", productos);
            request.setAttribute("idPedido", idPedido);
            request.setAttribute("pedido", pedido);
            request.setAttribute("cliente", usuario);
            request.setAttribute("cantidad", cantProductos);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void ok(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idPedido = request.getParameter("pedido");
        Pedido pedido = new PedidoDAO().realId(new Pedido(Integer.parseInt(idPedido)));
        pedido.setEstado(3);
        boolean ok = new PedidoDAO().actualizarEstado(pedido);
        //SendEmail
        Usuario usuario = new UsuarioDAOJDBC().realId(new Usuario(pedido.getIdCliente()));
        SendMail.sendValidator(usuario.getEmail());
        
        if (ok) {
            this.envios(request, response);
            String path = "/admin2.jsp";
            request.getRequestDispatcher(path).forward(request, response);
        }
    }
    
    private void no(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException, ServletException, IOException {
        String idPedido = request.getParameter("pedido");
        Pedido pedido = new PedidoDAO().realId(new Pedido(Integer.parseInt(idPedido)));
        boolean ok = new PedidoDAO().delete(pedido);

        if (ok) {
            String path = "/admin.jsp";
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    private void ok2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idPedido = request.getParameter("idPedido");
        Pedido pedido = new PedidoDAO().realId(new Pedido(Integer.parseInt(idPedido)));
        pedido.setEstado(4);
        //SendEmail
        Usuario usuario = new UsuarioDAOJDBC().realId(new Usuario(pedido.getIdCliente()));
        SendMail.sendSended(usuario.getEmail());

        boolean ok = new PedidoDAO().actualizarEstado(pedido);
        if (ok) {
            this.envios(request, response);
            String path = "/admin.jsp";
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    private void registro(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        String Mes = request.getParameter("DOBMonth");
        String Año = request.getParameter("DOBYear");
        boolean ok = new PedidoDAO().estado4(Integer.valueOf(Mes), Integer.valueOf(Año));
        if (ok) {
            //SI existen pedidos disponibles
            List<Pedido> pedidos = new ArrayList<Pedido>();
            pedidos = new PedidoDAO().estado24(4, Integer.valueOf(Mes), Integer.valueOf(Año));
            List<Usuario> clientes = new ArrayList<Usuario>();
            for (Pedido pedido : pedidos) {
                Usuario cliente = new UsuarioDAOJDBC().realId(new Usuario(pedido.getIdCliente()));
                clientes.add(cliente);
            }
            int size = clientes.size() - 1;
            request.setAttribute("size", size);
            request.setAttribute("clientes", clientes);
            request.setAttribute("pedidos", pedidos);
        } else {
            //No existen envios disponibles

        }
    }

}
