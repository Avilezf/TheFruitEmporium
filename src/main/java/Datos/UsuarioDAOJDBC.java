package Datos;

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
public class UsuarioDAOJDBC {

    private static final String SQL_SELECT_EXAMEN = "SELECT id_Examen, nombreExamen, asignatura, calificacion" + " FROM examen";
    private static final String SQL_SELECT_IP = "SELECT * " + " FROM usuarios WHERE ip = ?";
    private static final String SQL_SELECT_USUARIO = "SELECT * " + "FROM users";
    private static final String SQL_SELECT_BY_ID = "SELECT *" + " FROM usuarios WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO usuario(nombre, apellido, email, rol) " + " VALUES(?,?,?,?)";
    private static final String SQL_INSERT_USR = " INSERT INTO usuarios(nombre, apellido, direccion, celular, pais, ciudad, email, ip) " + "VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE usuario" + " SET usuario=?, clave=?, email=?, rol=? WHERE id_usuario=?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

//    ORACLE    
//    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
//    private static final String JDBC_USER = "prometeus";
//    private static final String JDBC_PASSWORD = "adminprometeus";
    //POSGRESQL
    // private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/prometodb";
    // private static final String JDBC_USER = "postgres";
    //private static final String JDBC_PASSWORD = "J450n450AC";
    //SENTECIA SELECT USUARIOS
    public List<Usuario> listaUsuario() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_USUARIO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt("ideusr");
                String nombre = rs.getString("namusr");
                String apellido = rs.getString("lasusr");
                String mail = rs.getString("maiusr");
                String clave = rs.getString("pasusr");
                //usuario = new Usuario(idUsuario, nombre, apellido, clave, mail);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (InstantiationException ex) {
            Logger.getLogger(UsuarioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuarios;
    }

    public Usuario id(Usuario usuario) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_IP);
            stmt.setString(1, usuario.getIp());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String celular = rs.getString("celular");
                String pais = rs.getString("pais");
                String ciudad = rs.getString("ciudad");
                String email = rs.getString("email");
                String ip = rs.getString("ip");
                String add = rs.getString("add");
                usuario = new Usuario(id, nombre, apellido, direccion, celular, pais, ciudad, email, ip, add);
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

        return usuario;
    }

    public Usuario realId(Usuario usuario) throws InstantiationException, IllegalAccessException {
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
            stmt.setString(1, usuario.getIp());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String celular = rs.getString("celular");
                String pais = rs.getString("pais");
                String ciudad = rs.getString("ciudad");
                String email = rs.getString("email");
                String ip = rs.getString("ip");
                String add = rs.getString("add");
                usuario = new Usuario(id, nombre, apellido, direccion, celular, pais, ciudad, email, ip, add);
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

        return usuario;
    }

    //SETENCIA SELECT BY ID
    public int verificar(Usuario usuario) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_IP);
            stmt.setString(2, usuario.getEmail());
            rs = stmt.executeQuery();

            String usuario1 = "error";
            String clave = "error";
            String email = "error";
            String rol = "error";

            boolean sw = true;

//            while (rs.next() && sw) {
//                String usuario2 = rs.getString("nombre");
//                if (usuario.getEmail().equalsIgnoreCase(usuario2)) {
//                    String clave2 = rs.getString("clave");
//                    if (usuario.getClave().equals(clave2)) {
//                        if (rs.getString("rol").equalsIgnoreCase("Estudiante")) {
//                            return 1;
//                        }
//                        if (rs.getString("rol").equalsIgnoreCase("Profesor")) {
//                            return 2;
//                        }
//                    }
//                    sw = false;
//                }
//            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return 0;
    }

    //SETENCIA INSERT INTO
    public int insertar(Usuario usuario) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_USR);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getDireccion());
            stmt.setString(4, usuario.getCelular());
            stmt.setString(5, usuario.getPais());
            stmt.setString(6, usuario.getCiudad());
            stmt.setString(7, usuario.getEmail());
            stmt.setString(8, usuario.getIp());

            rows = stmt.executeUpdate();
            System.out.println(" --------------------------------------------------------------------------- Rows " + rows);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
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
    public boolean ip(String remoteUser) throws InstantiationException, IllegalAccessException {
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
            stmt = conn.prepareStatement(SQL_SELECT_IP);
            stmt.setString(1, remoteUser);
            rs = stmt.executeQuery();

            while (rs.next()) {
                sw = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return sw;
    }
}
