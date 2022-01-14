<%-- 
    Document   : alumnos
    Created on : 19/11/2021, 17:51:35
    Author     : nfernandez
--%>

<%@page import="modelo.AlumnosDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Alumnos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    
    </head>
    
    <body>
        <h1>Listado de Alumnos</h1>
        
        <div class="container" >
            <div class="row" >
                <a class="btn btn-primary col-4 m-4" href="AlumnosController?accion=nuevo">Agregar Alumno</a>
                
                <table class="table table-primary" >
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>E-mail</th>
                            <th>Modificar</th>
                            <th>Eliminar</th>
                        </tr>                      
                    </thead> 
                    <%  
                    List<Alumnos> resultado=null;
                    AlumnosDAO alumno=new AlumnosDAO();
                    resultado=alumno.listarAlumnos();
                    

                    for(int i=0;i<resultado.size();i++)
                    {
                        // IMPORTANTE &id
                        String ruta="AlumnosController?accion=modificar&id="+resultado.get(i).getId();
                        String rutaE="AlumnosController?accion=eliminar&id="+resultado.get(i).getId();
                    %>
                    <tr>
                        <td><%=resultado.get(i).getId()%></td>
                        <td><%=resultado.get(i).getNombre()%></td>
                        <td><%=resultado.get(i).getApellido()%></td>
                        <td><%=resultado.get(i).getMail()%></td>
                        <td><a class="text-success" href=<%=ruta%>>X</a> </td>
                        <td><a class="text-success" href=<%=rutaE%>>X</a> </td>                     
                        
                    </tr>
                    
                    
                    <%
                        }
                    %>
                    
                </table>
            </div>
        </div>
       
           
        
        
        
    </body>
</html>
