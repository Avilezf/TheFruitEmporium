package Datos;

import Dominio.Pedido;
import Dominio.PedidosProductos;
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
public class PedidosProductosDAO {

    private static final String SQL_SELECT_EXAMEN = "SELECT id_Examen, nombreExamen, asignatura, calificacion" + " FROM examen";
    private static final String SQL_SELECT = "SELECT *" + " FROM productos ORDER BY nombre";
    private static final String SQL_SELECT_IDS = "SELECT *" + " FROM pedidosproductos where idpedido = ?";
    private static final String SQL_SELECT2 = "SELECT *" + " FROM productos WHERE categoria = ?";
    private static final String SQL_SELECT21 = "SELECT *" + " FROM productos WHERE categoria = ? LIMIT 4";
    private static final String SQL_SELECT_USUARIO = "SELECT * " + "FROM users";
    private static final String SQL_SELECT_BY_IDPP = "SELECT * FROM pedidosproductos WHERE idpedido = ? AND idproducto= ?";
    private static final String SQL_SELECT_BY_PROD = "SELECT * FROM pedidosproductos WHERE idproducto = ? AND idpedido = ?";
    private static final String SQL_DELETE_BY_PROD = "DELETE FROM pedidosproductos WHERE idproducto = ? AND idpedido = ?";
    private static final String SQL_INSERT = "INSERT INTO pedidosproductos(idpedido, idproducto, cantproducto) " + " VALUES(?,?,?)";
    private static final String SQL_INSERT_USR = " INSERT INTO users(namusr, lasusr, maiusr, pasusr) " + "VALUES (?,?,?,?)";
    private static final String SQL_INSERT_PEDIDO = " INSERT INTO pedido(idcliente, fecha, estado) " + "VALUES (?,?,?)";
        private static final String SQL_UPDATE_CANT = "UPDATE pedidosproductos" + " SET cantproducto=? WHERE idpxp=?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

//    ORACLE    
//    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
//    private static final String JDBC_USER = "prometeus";
//    private static final String JDBC_PASSWORD = "adminprometeus";
    //POSGRESQL
    //private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/prometodb";
    //private static final String JDBC_USER = "postgres";
    // private static final String JDBC_PASSWORD = "J450n450AC";
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
            Logger.getLogger(PedidosProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return productos;
    }

    //SETENCIA SELECT BY ID
    //SETENCIA INSERT INTO
    public boolean nuevo(PedidosProductos PP) throws InstantiationException, IllegalAccessException {
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
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, PP.getIdpedido());
            stmt.setInt(2, PP.getIdproducto());
            stmt.setInt(3, PP.getCantproducto());

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
//    public int actualizar(Usuario usuario) throws InstantiationException, IllegalAccessException {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        int rows = 0;//Registros modificados
//
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
//            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//            stmt = conn.prepareStatement(SQL_UPDATE);
//            stmt.setString(1, cliente.getNombre());
//            stmt.setString(2, cliente.getApellido());
//            stmt.setString(3, cliente.getEmail());
//            stmt.setString(4, cliente.getTelefono());
//            stmt.setInt(5, cliente.getSaldo());
//            stmt.setInt(6, cliente.getIdCliente());
//
//            rows = stmt.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            Conexion.close(stmt);
//            Conexion.close(conn);
//        }
//
//        return rows;
//    }
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
    public PedidosProductos buscar(PedidosProductos pedidosproductos) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_IDPP);
            stmt.setInt(1, pedidosproductos.getIdpedido());
            stmt.setInt(2, pedidosproductos.getIdproducto());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idpxp = rs.getInt("idpxp");
                int idpedido = rs.getInt("idpedido");
                int idproducto = rs.getInt("idproducto");
                int cantproducto = rs.getInt("cantproducto");
                pedidosproductos = new PedidosProductos(idpxp, idpedido, idproducto, cantproducto + pedidosproductos.getCantproducto());
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidosProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return pedidosproductos;
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
            Logger.getLogger(PedidosProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return productos;
    }

    public List<Integer> listarIds(Pedido pedido) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Integer> ids = new ArrayList<Integer>();

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            String query = SQL_SELECT_IDS;

            stmt = conn.prepareStatement(query);
            stmt.setInt(1, pedido.getIdPedido());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int ide = rs.getInt("idproducto");
                ids.add(ide);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (InstantiationException ex) {
            Logger.getLogger(PedidosProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return ids;
    }
    
    public List<Integer> listarCants(Pedido pedido) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Integer> cants = new ArrayList<Integer>();

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            String query = SQL_SELECT_IDS;

            stmt = conn.prepareStatement(query);
            stmt.setInt(1, pedido.getIdPedido());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int cant = rs.getInt("cantproducto");
                cants.add(cant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (InstantiationException ex) {
            Logger.getLogger(PedidosProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return cants;
    }

    public boolean verificar2(PedidosProductos PedidosProductos) throws InstantiationException, IllegalAccessException {
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
            stmt = conn.prepareStatement(SQL_SELECT_BY_PROD);
            stmt.setInt(1, PedidosProductos.getIdproducto());
            stmt.setInt(2, PedidosProductos.getIdpedido());
            rs = stmt.executeQuery();

            while (rs.next()) {
                sw = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidosProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return sw;
    }

    public boolean update(PedidosProductos PP) throws InstantiationException, IllegalAccessException {
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
            stmt = conn.prepareStatement(SQL_UPDATE_CANT);
            stmt.setInt(1, PP.getCantproducto());
            stmt.setInt(2, PP.getIdpxp());

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
    
    public boolean delete(PedidosProductos PedidosProductos) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean sw = false;
        int rows = 0;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_BY_PROD);
            stmt.setInt(1, PedidosProductos.getIdproducto());
            stmt.setInt(2, PedidosProductos.getIdpedido());
            rows = stmt.executeUpdate();

            while (rows != 0) {
                sw = true;
                rows = rows -1;
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidosProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return sw;
    }

}
