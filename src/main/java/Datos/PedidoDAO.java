package Datos;

import Dominio.Pedido;
import Dominio.Producto;
import Dominio.Usuario;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CL SMA
 */
public class PedidoDAO {

    private static final String SQL_SELECT_EXAMEN = "SELECT id_Examen, nombreExamen, asignatura, calificacion" + " FROM examen";
    private static final String SQL_SELECT = "SELECT *" + " FROM productos ORDER BY nombre";
    private static final String SQL_SELECT2 = "SELECT *" + " FROM productos WHERE categoria = ?";
    private static final String SQL_SELECT21 = "SELECT *" + " FROM productos WHERE categoria = ? LIMIT 4";
    private static final String SQL_SELECT_USUARIO = "SELECT * " + "FROM users";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM pedidos WHERE idpedidos = ?";
    private static final String SQL_SELECT_ESTADO2 = "SELECT * FROM pedidos WHERE estado = 2";
    private static final String SQL_SELECT_ESTADOX = "SELECT * FROM pedidos WHERE estado = ?";
    private static final String SQL_SELECT_ESTADOY = "SELECT * FROM pedidos WHERE estado = ? and yearf = ? and monthf = ?";
    private static final String SQL_SELECT_ESTADO3 = "SELECT * FROM pedidos WHERE estado = 3";
    private static final String SQL_SELECT_ESTADO4 = "SELECT * FROM pedidos WHERE estado = 4 and yearf = ? and monthf = ?";
    private static final String SQL_SELECT_BY_IDCLIENTE = "SELECT * FROM pedidos WHERE idcliente = ? ORDER BY idpedidos DESC LIMIT 1";
    private static final String SQL_INSERT = "INSERT INTO usuario(nombre, apellido, email, rol) " + " VALUES(?,?,?,?)";
    private static final String SQL_INSERT_USR = " INSERT INTO users(namusr, lasusr, maiusr, pasusr) " + "VALUES (?,?,?,?)";
    private static final String SQL_INSERT_PEDIDO = " INSERT INTO pedidos(idcliente, estado, carrito, fecha, subtotal, total, yearf, monthf) " + "VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_ESTADO = "UPDATE pedido" + " SET estado=? WHERE idpedido=?";
    private static final String SQL_UPDATE_CARRITO = "UPDATE pedidos" + " SET carrito=? WHERE idpedidos=?";
    private static final String SQL_UPDATE_ESTADO2 = "UPDATE pedidos" + " SET estado=?, subtotal=?, total=?, fecha=? WHERE idpedidos=?";
    private static final String SQL_DELETE = "DELETE FROM pedidos WHERE idpedidos = ?";

//    ORACLE    
//    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
//    private static final String JDBC_USER = "prometeus";
//    private static final String JDBC_PASSWORD = "adminprometeus";
    //POSGRESQL
    //private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/prometodb";
    //private static final String JDBC_USER = "postgres";
    //private static final String JDBC_PASSWORD = "J450n450AC";
    //SENTECIA SELECT USUARIOS
    public List<Producto> listar(String num) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<Producto>();

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            String query;
            if (num.equals("0")) {
                query = SQL_SELECT;
                stmt = conn.prepareStatement(query);
            } else {
                query = SQL_SELECT2;
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, Integer.parseInt(num));
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                int ide = rs.getInt("ide");
                String nombre = rs.getString("nombre");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                String img = rs.getString("img");
                int categoria = rs.getInt("categoria");
                producto = new Producto(ide, nombre, precio, cantidad, img, categoria);
                productos.add(producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (InstantiationException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return productos;
    }

    //SETENCIA SELECT BY ID
    //SETENCIA INSERT INTO
    public boolean nuevo(Pedido pedido) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows;
        boolean sw = false;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_PEDIDO);
            stmt.setInt(1, pedido.getIdCliente());
            stmt.setInt(2, 1);
            stmt.setInt(3, 1);
            stmt.setString(4, pedido.getFecha());
            stmt.setInt(5, pedido.getSubtotal());
            stmt.setInt(6, pedido.getTotal());
            stmt.setInt(7, pedido.getYear());
            stmt.setInt(8, pedido.getMonth());

            rows = stmt.executeUpdate();
            sw = true;
            System.out.println(" --------------------------------------------------------------------------- Rows " + rows);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return sw;
    }

    //SETENCIA UPDATE
    public boolean actualizar(Pedido pedido) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;//Registros modificados
        boolean sw = false;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_CARRITO);
            stmt.setInt(1, pedido.getCarrito());
            stmt.setInt(2, pedido.getIdPedido());

            rows = stmt.executeUpdate();
            
            sw = true;
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return sw;
    }
    
    public boolean actualizarEstado(Pedido pedido) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;//Registros modificados
        boolean sw = false;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_ESTADO2);
            stmt.setInt(1, pedido.getEstado());
            stmt.setInt(2, pedido.getSubtotal());
            stmt.setInt(3, pedido.getTotal());
            stmt.setString(4, pedido.getFecha());
            stmt.setInt(5, pedido.getIdPedido());

            rows = stmt.executeUpdate();
            
            sw = true;
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return sw;
    }

    //SENTENCIA DELETE
//    public int eliminar(Usuario usuario) throws InstantiationException, IllegalAccessException {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        int rows = 0;//Registros afectados
//
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
//            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//            stmt = conn.prepareStatement(SQL_DELETE);
//            stmt.setInt(1, cliente.getIdCliente());
//
//            rows = stmt.executeUpdate();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace(System.out);
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            Conexion.close(stmt);
//            Conexion.close(conn);
//        }
//
//        return rows;
//    }
    public Producto buscar(Producto producto) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, producto.getIde());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int ide = rs.getInt("ide");
                String nombre = rs.getString("nombre");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                String img = rs.getString("img");
                int categoria = rs.getInt("categoria");
                producto = new Producto(ide, nombre, precio, cantidad, img, categoria);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return producto;
    }

    public List<Producto> seleccionar(String num) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<Producto>();

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            String query;
            if (num.equals("0")) {
                query = SQL_SELECT;
                stmt = conn.prepareStatement(query);
            } else {
                query = SQL_SELECT21;
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, Integer.parseInt(num));
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                int ide = rs.getInt("ide");
                String nombre = rs.getString("nombre");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                String img = rs.getString("img");
                int categoria = rs.getInt("categoria");
                producto = new Producto(ide, nombre, precio, cantidad, img, categoria);
                productos.add(producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (InstantiationException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return productos;
    }

    public Pedido id(Pedido pedido) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_IDCLIENTE);
            stmt.setInt(1, pedido.getIdCliente());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPedido = rs.getInt("idpedidos");
                int idCliente = rs.getInt("idcliente");
                String fecha = rs.getString("fecha");
                int estado = rs.getInt("estado");
                int carrito = rs.getInt("carrito");
                int subtotal = rs.getInt("subtotal");
                int total = rs.getInt("total");
                int year = rs.getInt("yearf");
                int month = rs.getInt("monthf");
                pedido = new Pedido(idPedido, idCliente, fecha, estado, carrito, subtotal, total, year, month);
            }

        } catch (SQLException ex) {
            System.out.println("Error NULO");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return pedido;
    }
    
    public Pedido realId(Pedido pedido) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, pedido.getIdPedido());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPedido = rs.getInt("idpedidos");
                int idCliente = rs.getInt("idcliente");
                String fecha = rs.getString("fecha");
                int estado = rs.getInt("estado");
                int carrito = rs.getInt("carrito");
                int subtotal = rs.getInt("subtotal");
                int total = rs.getInt("total");
                int year = rs.getInt("yearf");
                int month = rs.getInt("monthf");
                pedido = new Pedido(idPedido, idCliente, fecha, estado, carrito, subtotal, total, year, month);
            }

        } catch (SQLException ex) {
            System.out.println("Error NULO");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return pedido;
    }

    public boolean estado2() throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean sw = false;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ESTADO2);
            rs = stmt.executeQuery();

            while (rs.next()) {
                sw = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error NULO");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return sw;
    }
    
    public boolean estado3() throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean sw = false;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ESTADO3);
            rs = stmt.executeQuery();

            while (rs.next()) {
                sw = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error NULO");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return sw;
    }
    
    

    public List<Pedido> estado2(int i) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<Pedido>();

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ESTADOX);
            stmt.setInt(1, i);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPedido = rs.getInt("idpedidos");
                int idCliente = rs.getInt("idcliente");
                int estado = rs.getInt("estado");
                int carrito = rs.getInt("carrito");
                String fecha = rs.getString("fecha");
                int subtotal = rs.getInt("subtotal");
                int total = rs.getInt("total");
                int year = rs.getInt("yearf");
                int month = rs.getInt("monthf");
                Pedido pedido = new Pedido(idPedido, idCliente, fecha, estado, carrito, subtotal, total, year, month);
                pedidos.add(pedido);
            }

        } catch (SQLException ex) {
            System.out.println("Error NULO");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return pedidos;
    }
    
    public List<Pedido> estado24(int i, int month, int year) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<Pedido>();

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ESTADOY);
            stmt.setInt(1, i);
            stmt.setInt(2, year);
            stmt.setInt(3, month);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPedido = rs.getInt("idpedidos");
                int idCliente = rs.getInt("idcliente");
                int estado = rs.getInt("estado");
                int carrito = rs.getInt("carrito");
                String fecha = rs.getString("fecha");
                int subtotal = rs.getInt("subtotal");
                int total = rs.getInt("total");
                year = rs.getInt("yearf");
                month = rs.getInt("monthf");
                Pedido pedido = new Pedido(idPedido, idCliente, fecha, estado, carrito, subtotal, total, year, month);
                pedidos.add(pedido);
            }

        } catch (SQLException ex) {
            System.out.println("Error NULO");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return pedidos;
    }

    public boolean estado4(int month, int year) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean sw = false;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ESTADO4);
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            rs = stmt.executeQuery();

            while (rs.next()) {
                sw = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error NULO");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return sw;
    }

    public boolean delete(Pedido pedido) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;//Registros afectados
        boolean sw = false;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, pedido.getIdPedido());

            rows = stmt.executeUpdate();
            sw = true;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return sw;
    }
    
    
}
