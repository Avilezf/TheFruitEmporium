/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import Datos.UsuarioDAOJDBC;
import Dominio.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author CL SMA
 */
@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    private static final String SMTP_SERVER = "smtp server ";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static final String EMAIL_FROM = "From@gmail.com";
    private static final String EMAIL_TO = "email_1@yahoo.com, email_2@gmail.com";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        String path; //Dirección para llegar al jsp
        if (accion != null) {

            path = "/" + accion + ".jsp";
            request.getRequestDispatcher(path).forward(request, response);
//            switch (accion) {
//                case "FPASS":
//                    this.ForgotPass(request, response);
//                    break;
//
//                case "CL":
////                    this.editarCliente(request, response);
//                    break;
////
//                case "wishlist":
//                    //this.listarExamenes(request, response);
//                    break;
//
//                case "SP":
//                    String name0 = "SP";
//                    this.redirect(request, response, name0);
//                    break;
//                    
//                case "home":
//                    String name = "home";
//                    this.redirect(request, response, name);
//                    break;
//
//                case "cart":
//                    String name2 = "cart";
//                    this.redirect(request, response, name2);
//                    break;
//
//                case "checkout":
//                    String name3 = "checkout";
//                    this.redirect(request, response, name3);
//                    break;
//
//                case "stadistics":
//                    String name4 = "stadistics";
//                    this.redirect(request, response, name4);
//                    break;
//
//                case "shop":
//                    String name5 = "shop";
//                    this.redirect(request, response, name5);
//                    break;
//
//                case "start":
//                    String name6 = "start";
//                    this.redirect(request, response, name6);
//                    break;
//
//                case "insert":
//                    this.insertUsuario(request, response);
//                    break;
////                case "eliminar":
////                    this.eliminarCliente(request, response);
////                    break;
////                    
////                default:
////                    this.accionDefault(request, response);
//            }
        } else {
            this.accionDefault(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insert":
                    // this.insertUsuario(request, response);
                    break;

                case "login":
                    String path;
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

//
//                case "eliminar":
//                    this.eliminarCliente(request, response);
//                    break;
//                    
//                default:
//                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String jspdir = "/home.jsp";
        request.getRequestDispatcher(jspdir).forward(request, response);
    }

    private void ForgotPass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//        Properties prop = System.getProperties();
//        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.port", "25"); // default port 25
//
//        Session session = Session.getInstance(prop, null);
//        Message msg = new MimeMessage(session);
//
//        try {
//
//            // from
//            msg.setFrom(new InternetAddress(EMAIL_FROM));
//
//            // to 
//            msg.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(EMAIL_TO, false));
//
//            // cc
//            msg.setRecipients(Message.RecipientType.CC,
//                    InternetAddress.parse(EMAIL_TO_CC, false));
//
//            // subject
//            msg.setSubject(EMAIL_SUBJECT);
//
//            // content 
//            msg.setText(EMAIL_TEXT);
//
//            msg.setSentDate(new Date());
//
//            // Get SMTPTransport
//            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
//
//            // connect
//            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
//
//            // send
//            t.sendMessage(msg, msg.getAllRecipients());
//
//            System.out.println("Response: " + t.getLastServerResponse());
//
//            t.close();
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }

//    private void listarExamenes(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        List<Examen> examenes;
//        try {
//            examenes = new UsuarioDAOJDBC().listar();
//            System.out.println("Examen = " + examenes);
//            HttpSession sesion = request.getSession();
//            sesion.setAttribute("Examen", examenes);
//            sesion.setAttribute("totalExamenes", examenes.size());
//            response.sendRedirect("questions.jsp");
//            //request.getRequestDispatcher("clientes.jsp").forward(request, response);//Recupera la información del cliente y la envia al jsp de clientes
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    private void redirect(HttpServletRequest request, HttpServletResponse response, String name) throws IOException, ServletException {
        switch (name) {
            case "profile":
                String jspdir0 = "/profile.jsp";
                request.getRequestDispatcher(jspdir0).forward(request, response);
                break;

            case "home":
                String jspdir = "/WEB-INF/Pages/StudentPages/Home/student.jsp";
                request.getRequestDispatcher(jspdir).forward(request, response);
                break;

            case "lessons":
                String jspdir2 = "/WEB-INF/Pages/StudentPages/Lessons/lessons.jsp";
                request.getRequestDispatcher(jspdir2).forward(request, response);
                break;

            case "test":
                String jspdir3 = "/WEB-INF/Pages/StudentPages/Test/test.jsp";
                request.getRequestDispatcher(jspdir3).forward(request, response);
                break;

            case "stadistics":
                String jspdir4 = "/WEB-INF/Pages/StudentPages/Stadistics/stadistics.jsp";
                request.getRequestDispatcher(jspdir4).forward(request, response);
                break;

            case "help":
                String jspdir5 = "/WEB-INF/Pages/StudentPages/Help/help.jsp";
                request.getRequestDispatcher(jspdir5).forward(request, response);
                break;

            case "start":
                String jspdir6 = "/home.jsp";
                request.getRequestDispatcher(jspdir6).forward(request, response);
                break;
        }
    }

//    private void insertUsuario(HttpServletRequest request, HttpServletResponse response) {
//        String email = request.getParameter("emailregister");
//        String password = request.getParameter("passregister");
//
//        Usuario usuario = new Usuario(password, email);
//        try {
//            boolean insert = new UsuarioDAOJDBC().insertar(usuario);
//            System.out.println("Resultado " + insert);
//
//            String jspdir = "/WEB-INF/Pages/StudentPages/Home/student.jsp";
//            request.getRequestDispatcher(jspdir).forward(request, response);
//
//        } catch (IllegalAccessException | InstantiationException | ServletException | IOException ex) {
//            Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void loginUsuario(HttpServletRequest request, HttpServletResponse response) {
//        List<Usuario> usr = null;
//        try {
//            try {
//                usr = new UsuarioDAOJDBC().listaUsuario();
//
//                String msg = "CONTRASEÑA INCORRECTA O MAIL NO EXISTE";
//                String email = request.getParameter("mail");
//                String password = request.getParameter("pass");
//                boolean sw = false;
//
//                for (Usuario usuario : usr) {
//                    if (usuario.getEmail().equals(email)) {
//                        if (usuario.getClave().equals(password)) {
//                            String jspdir = "/WEB-INF/Pages/StudentPages/Home/student.jsp";
//                            request.getRequestDispatcher(jspdir).forward(request, response);
//                            sw = true;
//                        }
//                    }
//                }
//
//                if (!sw) {
//                    String jspdir2 = "/WEB-INF/Pages/Login/login.jsp";
//                    request.setAttribute("msg", msg);
//                    request.getRequestDispatcher(jspdir2).forward(request, response);
//                }
//
//            } catch (ClassNotFoundException | InstantiationException | ServletException | IOException ex) {
//                Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
}
