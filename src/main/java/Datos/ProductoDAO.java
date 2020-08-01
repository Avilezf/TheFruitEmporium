package Datos;

import Dominio.Producto;
import Dominio.Usuario;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CL SMA
 */
public class ProductoDAO {

    private static final String SQL_SELECT_EXAMEN = "SELECT id_Examen, nombreExamen, asignatura, calificacion" + " FROM examen";
    private static final String SQL_SELECT = "SELECT *" + " FROM productos ORDER BY nombre";
    private static final String SQL_SELECT2 = "SELECT *" + " FROM productos WHERE categoria = ?";
    private static final String SQL_SELECT21 = "SELECT *" + " FROM productos WHERE categoria = ? LIMIT 4";
    private static final String SQL_SELECT_USUARIO = "SELECT * " + "FROM users";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM productos WHERE ide = ?";
    private static final String SQL_INSERT = "INSERT INTO usuario(nombre, apellido, email, rol) " + " VALUES(?,?,?,?)";
    private static final String SQL_INSERT_USR = " INSERT INTO users(namusr, lasusr, maiusr, pasusr) " + "VALUES (?,?,?,?)";
    private static final String SQL_INSERT_PEDIDO = " INSERT INTO users(namusr, lasusr, maiusr, pasusr) " + "VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE usuario" + " SET usuario=?, clave=?, email=?, rol=? WHERE id_usuario=?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

//    ORACLE    
//    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
//    private static final String JDBC_USER = "prometeus";
//    private static final String JDBC_PASSWORD = "adminprometeus";
    //POSGRESQL
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/prometodb";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "J450n450AC";

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
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return productos;
    }

    //SETENCIA SELECT BY ID
    

    //SETENCIA INSERT INTO
    public boolean insertar(Producto producto) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_PEDIDO);
            //stmt.setString(1, producto.getNombre());
            //stmt.setString(2, producto.getApellido());
            //stmt.setString(3, producto.getEmail());
            //stmt.setString(4, producto.getClave());

            rows = stmt.executeUpdate();
            System.out.println(" --------------------------------------------------------------------------- Rows " + rows);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return true;
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
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return productos;
    }
    
    public Producto buscarId(int num) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Producto producto = null;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, num);
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
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return producto;
    }
}
