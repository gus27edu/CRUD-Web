/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nfernandez
 */
public class Conexion 
{
    public String driver="com.mysql.jdbc.Driver";  
    
   public Connection getConnection()
   { 
    Connection conexion=null;
    try
    {
        Class.forName(driver);
//      "jdbc:mysql://localhost:3306/crud1111","root",""
        // Crud1111 es el nombre de la BASE DE DATOS, no de la tabla.
        conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/Crud1111", "root", "");
     
    }
    catch(ClassNotFoundException|SQLException e)
    {
        System.out.println(e.toString());
    }
   return conexion;  
    }
   
   
   public static void main(String [] args) throws SQLException
   {
       Connection conexion=null;
       Conexion con=new Conexion();
       conexion=con.getConnection();
      
       PreparedStatement ps;
       ResultSet rs;
       
       ps=conexion.prepareStatement("SELECT * FROM alumnos");
       rs=ps.executeQuery();
       
       while(rs.next())
       {
           int id=rs.getInt("id");
           String nombre=rs.getString("nombre");
           String apellido=rs.getString("apellido");
           String mail=rs.getString("mail");
           
           System.out.println("id: "+id+" Apellido:"+apellido+" Nombre:"+nombre+" Mail:"+mail);
       }       
   }
   
   
   
   
}
