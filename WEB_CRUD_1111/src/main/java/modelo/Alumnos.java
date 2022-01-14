/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author nfernandez
 */
public class Alumnos 
{
    private int id;
    private String nombre;
    private String apellido;
    private String mail;
    
    
    public Alumnos(int id,String nombre,String apellido,String mail)
    {
        this.id=id;
        this.nombre=nombre;
        this.apellido=apellido;
        this.mail=mail;       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMail() 
    {
        return mail;
    }

    public void setMail(String mail) 
    {
        this.mail = mail;
    }
    
    
    
    
    
    
    
}
