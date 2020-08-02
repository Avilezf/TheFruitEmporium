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
    private String direccion;
    private String pais;
    private String ciudad;
    private String celular;
    private String email;
    private String ip;
    private String add;
    private String cedula;
    //private String rol;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellido, String direccion, String pais, String ciudad, String celular, String email, String ip, String add, String cedula) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.pais = pais;
        this.ciudad = ciudad;
        this.celular = celular;
        this.email = email;
        this.add = add;
        this.ip = ip;
    }

    public Usuario(String nombre, String apellido, String direccion, String pais, String ciudad, String celular, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.pais = pais;
        this.ciudad = ciudad;
        this.celular = celular;
        this.email = email;
        this.add = add;
    }

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
        this.email = "";
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.pais = "";
        this.ciudad = "";
        this.celular = "";
        this.add = "";
        this.cedula = "";
    }
    
    

    public Usuario(String ip) {
        this.cedula = "";
        this.email = "";
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.pais = "";
        this.ciudad = "";
        this.celular = "";
        this.add = "";
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    
    
}
    
    