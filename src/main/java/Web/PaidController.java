package Web;

import Datos.PedidoDAO;
import Datos.PedidosProductosDAO;
import Datos.ProductoDAO;
import Datos.SendMail;
import Datos.UsuarioDAOJDBC;
import Model.Pedido;
import Model.PedidosProductos;
import Model.Producto;
import Model.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
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
 *  Paid Controller: In charge of the payment part of the software
 * 
 * @author Ing. Luis Llanos / BNT SAS
 * @version Fruit 2.0
 */
@WebServlet("/PaidController")
public class PaidController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        String path = ""; //Dirección para llegar al jsp
        if (accion != null) {
            try {
                String ip = request.getRemoteHost();

                if (ip == null) {
                    ip = "192.168.1.11";
                }

                boolean sw2 = new UsuarioDAOJDBC().ip(ip);
                if (sw2) {
                    ActualUser(request, response, ip, path, accion);
                } else {
                    this.accionDefault(request, response, path, accion);
                }

            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                Logger.getLogger(PaidController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            this.accionDefault(request, response, path, accion);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        String path = ""; //Dirección para llegar al jsp
        if (accion != null) {
            switch (accion) {
                case "buy":
                    //Usuario
                    String nombre = request.getParameter("nombre");
                    String apellido = request.getParameter("apellido");
                    String pais = request.getParameter("pais");
                    String direccion = request.getParameter("direccion");
                    String add = request.getParameter("add");
                    String ciudad = request.getParameter("ciudad");
                    String cedula = request.getParameter("cedula");
                    String celular = request.getParameter("celular");
                    String email = request.getParameter("email");
                    String yes = request.getParameter("yes");
                    String radio = request.getParameter("radio");
                    String subtotal = request.getParameter("subtotal");
                    String total = request.getParameter("total");
                    String idPedido = request.getParameter("idPedido");

                    try {
                        Pedido pedido = new PedidoDAO().realId(new Pedido(Integer.valueOf(idPedido)));

                        //UPDATE USUARIO
                        Usuario usuario = new UsuarioDAOJDBC().realId(new Usuario(pedido.getIdCliente()));
                        usuario.setNombre(nombre);
                        usuario.setApellido(apellido);
                        usuario.setPais(pais);
                        usuario.setDireccion(direccion);
                        usuario.setAdd(add);
                        usuario.setCiudad(ciudad);
                        usuario.setCelular(celular);
                        usuario.setEmail(email);
                        usuario.setCedula(cedula);
                        boolean ok = new UsuarioDAOJDBC().Update(usuario);
                        if (ok) {
                            this.Pago(radio, pedido, subtotal, total, request, response);

                        }

                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(PaidController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(PaidController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;

                case "checkout":
                    String idusuario = request.getParameter("usuario");
                    String idpedido = request.getParameter("pedido");
                    subtotal = request.getParameter("subtotal");
                    String descripcion = request.getParameter("descripcion");
                    total = request.getParameter("total");
                    path = ""; //Dirección para llegar al jsp

                    try {
                        //Se recupera el usuario
                        Usuario usuario = new UsuarioDAOJDBC().realId(new Usuario(idusuario));

                        request.setAttribute("usuario", usuario);
                        request.setAttribute("idpedido", idpedido);
                        request.setAttribute("subtotal", subtotal);
                        request.setAttribute("total", total);

                        if (!descripcion.isEmpty()) {
                            //Enviar la descripción
                            Pedido pedido = new Pedido(Integer.parseInt(idpedido), descripcion);
                            boolean sw = new PedidoDAO().updatedescripcion(pedido);
                            if (sw) {
                                this.accionDefault(request, response, path, accion);
                            }
                        } else {
                            this.accionDefault(request, response, path, accion);
                        }

                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(PaidController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

            }
        } else {
            this.accionDefault(request, response, path, accion);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response, String path, String accion) throws IOException, ServletException {
        path = "/" + accion + ".jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    private void ActualUser(HttpServletRequest request, HttpServletResponse response, String ip, String path, String accion) throws InstantiationException, IllegalAccessException, IOException, ServletException, ClassNotFoundException {
        //Usar usuario existente
        Usuario temp = new Usuario(ip);
        Usuario usuario = new UsuarioDAOJDBC().ip(temp);
        Date date = new Date();
        String fecha = date.toString();
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DATE);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        Pedido pedido = new PedidoDAO().id(new Pedido(usuario.getIdUsuario(), fecha, month, year, day, week)); //busca el id del usuario para verificar si tiene pedidos

        if (pedido.getEstado() != 1) {//Si es igual a 0, el pedido o está cancelado
            path = "/home.jsp";
            request.getRequestDispatcher(path).forward(request, response);
        } else {
            //El pedido no es nuevo

            if (accion.equals("delete")) {
                //Por si es eliminar
                int ide = Integer.parseInt(request.getParameter("idProducto"));
                int ide2 = Integer.parseInt(request.getParameter("numPedido"));
                PedidosProductos PP = new PedidosProductos(ide2, ide, 0);
                boolean prod = new PedidosProductosDAO().verificar2(PP);//Se verifica si el producto está en el carro
                if (prod) {
                    //Si el producto ya estaba en el carrito
                    PedidosProductos PedidosProductos = new PedidosProductosDAO().buscar(PP);
                    //Update
                    boolean estado = new PedidosProductosDAO().delete(PedidosProductos);
                    if (estado) {
                        pedido.setCarrito(pedido.getCarrito() - 1);
                        boolean ok = new PedidoDAO().actualizar(pedido);
                        if (ok) {
                            this.cart(request, response, path, pedido, usuario);
                        }

                    }

                }

            } else {
                this.cart(request, response, path, pedido, usuario);
            }

        }

    }

    private void cart(HttpServletRequest request, HttpServletResponse response, String path, Pedido pedido, Usuario usuario) throws IOException, ServletException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        List<Integer> cants = new PedidosProductosDAO().listarCants(new Pedido(pedido.getIdPedido()));
        List<Integer> ids = new PedidosProductosDAO().listarIds(new Pedido(pedido.getIdPedido()));
        List<Producto> productos = new ArrayList<>();
        List<Integer> precios = new ArrayList<>();
        Producto producto;
        for (Integer id : ids) {
            producto = new ProductoDAO().buscarId(id);
            productos.add(producto);
        }
        int sum = 0;
        for (int i = 0; i < productos.size(); i++) {
            int mul = cants.get(i) * productos.get(i).getPrecio();
            precios.add(mul);
            sum = sum + mul;
        }
        int envio = sum + 4000;
        int num = productos.size() - 1;
        if (num < 0) {
            path = "/home.jsp";
            request.getRequestDispatcher(path).forward(request, response);
        } else {
            request.setAttribute("carrito", pedido.getCarrito());
            request.setAttribute("sum", sum);
            request.setAttribute("productos", productos);
            request.setAttribute("precios", precios);
            request.setAttribute("pedido", pedido.getIdPedido());
            request.setAttribute("usuario", usuario.getIdUsuario());
            request.setAttribute("envio", envio);
            request.setAttribute("num", num);
            request.setAttribute("cants", cants);
            path = "/cart.jsp";
            request.getRequestDispatcher(path).forward(request, response);
        }

    }

    private void Pago(String radio, Pedido pedido, String subtotal, String total, HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException, ServletException, IOException, Exception {
        Date date = new Date();
        String fecha = date.toString();
        pedido.setFecha(fecha);
        pedido.setEstado(2);
        pedido.setSubtotal(Integer.valueOf(subtotal));
        pedido.setTotal(Integer.valueOf(total));
        boolean ok = new PedidoDAO().actualizarEstado(pedido);
        if (ok) {
            String to = "TheFruitEmporiumCo@gmail.com";
            SendMail.sendPedido(to, pedido);
            String path = "";
            request.setAttribute("pedido", pedido);
            switch (radio) {
                case "0"://Transferencia
                    path = "/payroll0.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                case "1"://Pago por QR
                    path = "/payroll1.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                case "2"://NEQUI
                    path = "/payroll2.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                default:
                    path = "/payroll0.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;
            }
        }

    }


}
