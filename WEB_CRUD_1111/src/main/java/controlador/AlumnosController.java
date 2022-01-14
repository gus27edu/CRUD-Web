/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Alumnos;
import modelo.AlumnosDAO;

/**
 *
 * @author nfernandez
 */
@WebServlet(name = "AlumnosController", urlPatterns = {"/AlumnosController"})
public class AlumnosController extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        AlumnosDAO alumnosDao = new AlumnosDAO();
        String accion;
        RequestDispatcher dispatcher = null;
        accion=request.getParameter("accion");
        
        if(accion==null||accion.isEmpty())
        {
            dispatcher=request.getRequestDispatcher("Vistas/alumnos.jsp");            
        }
        else if(accion.equals("modificar"))
        {
            dispatcher=request.getRequestDispatcher("Vistas/modificar.jsp");
        }
        // Si hacemos clic en la x para modificar un alumno, va a enviar una 
        // direccion que nos va a reedririgir a una pagina que se llama 
        // modifcar.jsp, pero cuando yo haga clic en "Aceptar modificacion" 
        // tiene que enviar de nuevo esta informacion al controlador, y este 
        // tiene que decidir que va a hacer con esa informacion. Para esto 
        // nosotros tenemso que darle otro valor al Sting accion mediante un 
        // else if(accion.equals("actualizar")), dentro del cual (y sabiendo que
        // los atributos de este Alumno ya tiene valores) guardaremos en 
        // variables locales los atributos provenientes de modificar.jsp 
        // mediante la URL ## CHEQUEAR ## previos a parsearlos del String a su 
        // tipo correspondiente.
        else if(accion.equals("actualizar"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String mail = request.getParameter("mail");
            
            // Ahora crearemos un nuevo objeto de tipo Alumno pasandole al cons-
            // tructor las variables locales como parametros.
            // Este objeto alumno lo usamos como parametro para actualizar la BD
            // mediante la funcion de la clase AlumnoDAO actualizarAlumno().
            Alumnos alumno = new Alumnos(id, nombre, apellido, mail);
            alumnosDao.actualizarAlumno(alumno);
            
            // Por ultimo, le decimos al despachador que cargue la vista 
            // alumnos.jsp para mostrar el dato actualizado
            dispatcher = request.getRequestDispatcher("Vistas/alumnos.jsp");
        }
        // Ahora si apretamos el boton de eliminar, AlumnosController.java debe
        // eliminarlo, para ello detectamos el parametro eliminar mediante un
        // else if(accion.equals("eliminar")), y va a ser una operacion mas 
        // sencilla porque no va a requerir una pantalla intermedia que mostrar,
        // solo tiene que parsear el id a un entero, pasarselo a el metodo 
        // eliminarAlumno() de mi objeto AlumnosDAO y recargar alumnos.jsp.
        else if(accion.equals("eliminar"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            alumnosDao.eliminarAlumno(id);
            
            dispatcher = request.getRequestDispatcher("Vistas/alumnos.jsp");
        }
        else if(accion.equals("nuevo"))
        {            
            dispatcher = request.getRequestDispatcher("Vistas/nuevo.jsp");   
        }
        else if(accion.equals("insert"))
        {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String mail = request.getParameter("mail");
            
            // Creamos un nuevo objeto Alumno con un id -dado que es requerido
            // por el constructor- pero a la hora de hacer el INSERT en la BD, 
            // no le pasamos el id, que es autoincremental.
            Alumnos alumno = new Alumnos(0, nombre, apellido, mail);
            alumnosDao.insertarAlumno(alumno);
            // Lo ultimo que hace es recargar la vista alumnos.jsp.
            dispatcher = request.getRequestDispatcher("Vistas/alumnos.jsp");

            
            
        }
        dispatcher.forward(request, response);        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        doGet(request,response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
