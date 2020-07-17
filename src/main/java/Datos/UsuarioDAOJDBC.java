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
    private static final String SQL_SELECT = "SELECT id_pregunta, pregunta, respuesta1, respuesta2, respuesta3, respuesta4" + " FROM questions" + "WHERE questions.id_ExamenP = ";
    private static final String SQL_SELECT_USUARIO = "SELECT * " + "FROM users"; 
    private static final String SQL_SELECT_BY_ID = "SELECT id_usuario, usuario, clave, email, rol" + " FROM usuario WHERE usuario = ?";
    private static final String SQL_INSERT = "INSERT INTO usuario(nombre, apellido, email, rol) " + " VALUES(?,?,?,?)";
    private static final String SQL_INSERT_USR = " INSERT INTO users(namusr, lasusr, maiusr, pasusr) " + "VALUES (?,?,?,?)";
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
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_USUARIO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt("ideusr");
                String nombre = rs.getString("namusr");
                String apellido = rs.getString("lasusr");
                String mail = rs.getString("maiusr");
                String clave = rs.getString("pasusr");
                usuario = new Usuario(idUsuario, nombre, apellido, clave, mail);
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
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setString(2, usuario.getEmail());
            rs = stmt.executeQuery();

            String usuario1 = "error";
            String clave = "error";
            String email = "error";
            String rol = "error";

            boolean sw = true;

            while (rs.next() && sw) {
                String usuario2 = rs.getString("nombre");
                if (usuario.getEmail().equalsIgnoreCase(usuario2)) {
                    String clave2 = rs.getString("clave");
                    if (usuario.getClave().equals(clave2)) {
                        if (rs.getString("rol").equalsIgnoreCase("Estudiante")) {
                            return 1;
                        }
                        if (rs.getString("rol").equalsIgnoreCase("Profesor")) {
                            return 2;
                        }
                    }
                    sw = false;
                }
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

        return 0;
    }

    //SETENCIA INSERT INTO
    public boolean insertar(Usuario usuario) throws InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows;
        
        try {
            //Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            
            //Postgres
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            stmt = conn.prepareStatement(SQL_INSERT_USR);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getClave());
            
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
}
