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
import Model.Pedido;
import Model.Producto;
import Model.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Admin Controller: Is the one in charge of all the admin part
 *
 * @author Ing. Luis Llanos / BNT SAS
 * @version 2.0
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        String path; //Dirección para llegar al jsp
        if (accion != null) {
            if (accion.length() > 4) {
                if (accion.substring(0, 4).equals("list")) {
                    path = "";
                    this.List(request, response, path, accion);
                }
            }

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

                case "dashboard": {
                    path = "";
                    try {
                        this.dashboard(request, response, path);

                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                break;

                case "trash":
                     try {
                    String ide = request.getParameter("idProducto");
                    Producto producto = new ProductoDAO().buscarId(Integer.getInteger(ide));
                    //Reemplazar
                    File f = new File("/" + producto.getImg());
                    if (f.delete()) {
                        System.out.println("Image Deleted...");
                        boolean ok = new ProductoDAO().eliminar(ide);
                        if (ok) {
                            System.out.println("Prodcuto Deleted");
                            path = "/redirect3.jsp";
                            request.getRequestDispatcher(path).forward(request, response);
                        }
                    }

                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(PaidController.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

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
        String path = "";
        if (accion != null) {
            switch (accion) {
                case "registro": {
                    try {
                        this.registro(request, response);
                        path = "/admin3.jsp";
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

                case "agregar":
                    //Producto
                    String nombre = "";
                    String precio = "";
                    String cantidad = "";
                    String categoria = "";
                    String img = "";
                    String id = "";
                    ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());

                    try {
                        int num = 0;
                        List<FileItem> files = sf.parseRequest(request);
                        for (FileItem file : files) {
                            if (file.isFormField()) {
                                switch (num) {
                                    case 0:
                                        nombre = file.getString();
                                        break;

                                    case 1:
                                        precio = file.getString();
                                        break;

                                    case 2:
                                        cantidad = file.getString();
                                        break;

                                    case 3:
                                        categoria = file.getString();
                                        break;

                                    case 4:
                                        img = file.getString();
                                        break;

                                    case 5:
                                        id = file.getString();
                                        break;
                                }
                            } else {
                                //Reemplazar
                                file.write(new File("/" + img));
                            }
                            num++;
                        }
                    } catch (FileUploadException ex) {
                        Logger.getLogger(PaidController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(PaidController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Uploaded...");

                    if (id == null || id.equals("")) {
                        try {
                            //Crear producto
                            Producto producto = new Producto(nombre, Integer.parseInt(precio), Integer.parseInt(cantidad), img, Integer.parseInt(categoria));
                            boolean ok = new ProductoDAO().insertar(producto);
                            if (ok) {
                                path = "/redirect3.jsp";
                                request.getRequestDispatcher(path).forward(request, response);

                            }
                        } catch (InstantiationException | IllegalAccessException ex) {
                            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        //Modificar producto
                        try {
                            //UPDATE producto
                            Producto producto = new ProductoDAO().buscarId(Integer.parseInt(id));
                            producto.setNombre(nombre);
                            producto.setPrecio(Integer.parseInt(precio));
                            producto.setCantidad(Integer.parseInt(cantidad));
                            producto.setCategoria(Integer.parseInt(categoria));
                            producto.setImg(img);
                            boolean ok = new ProductoDAO().actualizar(producto);
                            if (ok) {
                                path = "/redirect3.jsp";
                                request.getRequestDispatcher(path).forward(request, response);

                            }

                        } catch (InstantiationException | IllegalAccessException ex) {
                            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    break;

                case "add":
                    path = "/object.jsp"; //Dirección para llegar al jsp
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                case "mod":
                    String idProducto = request.getParameter("idProducto");
                    path = ""; //Dirección para llegar al jsp

                    try {
                        //Se recupera el usuario
                        Producto producto = new ProductoDAO().buscarId(Integer.parseInt(idProducto));
                        request.setAttribute("producto", producto);
                        path = "/object.jsp"; //Dirección para llegar al jsp
                        request.getRequestDispatcher(path).forward(request, response);
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case "login":
                    String usuario = request.getParameter("username");
                    String password = request.getParameter("pass");
                    if (usuario.equals("admin")) {
                        if (password.equals("llanosperez")) {
                            //Entró
                            path = "/redirect2.jsp";
                            request.getRequestDispatcher(path).forward(request, response);
                        } else {
                            path = "/login.jsp";
                            request.getRequestDispatcher(path).forward(request, response);
                        }
                    } else {
                        path = "/login.jsp";
                        request.getRequestDispatcher(path).forward(request, response);
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
            request.setAttribute("descripcion", pedido.getDescripcion());
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
        SendMail.sendValidator(usuario.getEmail(), pedido);

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

    private void List(HttpServletRequest request, HttpServletResponse response, String path, String accion) throws ServletException, IOException {
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
                    path = "/products.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                case "1"://Shopping Fruits
                    path = "/products.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                case "2"://Shopping Vegetables
                    path = "/products.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                case "3"://Shopping Juices
                    path = "/products.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

                case "4"://Shopping Dried
                    path = "/products.jsp";
                    request.getRequestDispatcher(path).forward(request, response);
                    break;

            }
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException ex) {
            System.out.println("--------------------------------Error-------------------------------");
            Logger.getLogger(StoreController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("--------------------------------Error-------------------------------");
        }
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

    private void dashboard(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException, InstantiationException, IllegalAccessException {
        List<Pedido> confirm = new PedidoDAO().estado2(2);
        List<Pedido> send = new PedidoDAO().estado2(3);
        List<Pedido> complete = new PedidoDAO().estado2(4);
        int sells = new PedidoDAO().sells();

        int year[] = new int[12];
        for (int i = 0; i < year.length; i++) {
            year[i] = 0;
        }
        int weeksells[] = new int[7];
        int weekbuy[] = new int[7];
        for (int i = 0; i < weeksells.length; i++) {
            weeksells[i] = 0;
            weekbuy[i] = 0;
        }

        //Mensual sells
        Calendar today = Calendar.getInstance();
        for (Pedido pedido : complete) {
            year[pedido.getMonth()] = year[pedido.getMonth()] + 1;
            int todaysweek = today.get(Calendar.WEEK_OF_YEAR);
            if (pedido.getWeek() == todaysweek) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(pedido.getYear(), pedido.getMonth(), pedido.getDay());
                weeksells[calendar.get(Calendar.DAY_OF_WEEK)] = weeksells[calendar.get(Calendar.DAY_OF_WEEK)] + pedido.getTotal();
            }
        }

        for (Pedido pedido : confirm) {
            int todaysweek = today.get(Calendar.WEEK_OF_YEAR);
            if (pedido.getWeek() == todaysweek) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(pedido.getYear(), pedido.getMonth(), pedido.getDay());
                weekbuy[calendar.get(Calendar.DAY_OF_WEEK)] = weekbuy[calendar.get(Calendar.DAY_OF_WEEK)] + 1;
            }
        }

        request.setAttribute("confirm", confirm.size());
        request.setAttribute("send", send.size());
        request.setAttribute("complete", complete.size());
        request.setAttribute("sells", sells);
        request.setAttribute("year", year);
        request.setAttribute("weeksells", weeksells);
        request.setAttribute("weekbuy", weekbuy);
        path = "/dashboard.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

}
