/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.cj.xdevapi.PreparableStatement;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nfernandez
 */
public class AlumnosDAO 
{
    Connection conexion;
    
    public AlumnosDAO()
    {
      Conexion con=new Conexion();
      conexion =con.getConnection();
    }
    
    
    public List<Alumnos> listarAlumnos()
    {
        PreparedStatement ps;
        ResultSet rs;
        List<Alumnos> lista=new ArrayList<>();
        
        try
        {
            ps=conexion.prepareStatement("SELECT * FROM alumnos");
            rs=ps.executeQuery();
            
            while(rs.next())
            {
             int id=rs.getInt("id");
             String nombre=rs.getString("nombre");
             String apellido=rs.getString("apellido");
             String mail=rs.getString("mail");
             Alumnos alumnos=new Alumnos(id,nombre,apellido,mail);
             lista.add(alumnos);            
            }
            return lista;     
            
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            return null;
        }       
    }
    
    public Alumnos mostrarAlumno(int _id)
    {
        PreparedStatement ps;
        ResultSet rs;
        Alumnos alumno=null;
        
        try
        {
            ps=conexion.prepareStatement("SELECT * FROM alumnos WHERE id=?");
            ps.setInt(1, _id);
            rs=ps.executeQuery();
            
            while(rs.next())
            {
             int id=rs.getInt("id");
             String nombre=rs.getString("nombre");
             String apellido=rs.getString("apellido");
             String mail=rs.getString("mail");
             alumno =new Alumnos(id,nombre,apellido,mail);                        
            }
            return alumno;               
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            return null;
        }
        
    }  
     
    public boolean insertarAlumno(Alumnos alumno)
    {
        PreparedStatement ps;
        try
        {
           ps=conexion.prepareStatement("INSERT INTO alumnos(nombre,apellido,mail) VALUES (?,?,?)");
           // NO INSERTAMOS NINGUN ID DADO QUE ES AUTOINCREMENTAL.
           ps.setString(1, alumno.getNombre());
           ps.setString(2, alumno.getApellido());
           ps.setString(3, alumno.getMail());
           ps.execute();          
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            return false;
        }
        
    }
    
    public boolean actualizarAlumno(Alumnos alumno)
    {
        PreparedStatement ps;
        try
        {
           ps=conexion.prepareStatement("UPDATE alumnos SET nombre=?,apellido=?,mail=? WHERE id=?");
           ps.setString(1, alumno.getNombre());
           ps.setString(2, alumno.getApellido());
           ps.setString(3, alumno.getMail());
           ps.setInt(4, alumno.getId());
           ps.execute();          
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            return false;
        }
        
    }
    
    public boolean eliminarAlumno(int _id)
    {
        PreparedStatement ps;
        try
        {
           ps=conexion.prepareStatement("DELETE FROM alumnos WHERE id=?");
           ps.setInt(1, _id);
           ps.execute();          
           return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            return false;
        }
        
    }
    
    
    
    
    
    
}
