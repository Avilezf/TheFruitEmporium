/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author CL SMA
 */
public class Usuario {
    
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String clave;
    private String email;
    //private String rol;

    public Usuario() {
    }
    
    public Usuario(String clave, String email) {
        this.nombre = "";
        this.apellido = "";
        this.clave = clave;
        this.email = email;
        //this.rol = rol;
    }

    public Usuario(String nombre, String apellido, String clave, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.email = email;
        //this.rol = rol;
    }

    public Usuario(int idUsuario, String nombre, String apellido, String clave, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.email = email;
        //this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getRol() {
//        return rol;
//    }
//
//    public void setRol(String rol) {
//        this.rol = rol;
//    }
    
    
}
