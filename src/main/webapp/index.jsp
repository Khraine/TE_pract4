<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Producto"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List <Producto> lista = (List<Producto>)request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LISTADO DE PRODUCTOS</h1>
        <p><a href="MainController?op=nuevo">NUEVO REGISTRO</a></p>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>PRODUCTO</th>
                <th>PRECIO</th>
                <th>CANTIDAD</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${lista}"> 
                <tr>
                    <td>${item.id}</td>
                    <td>${item.producto}</td>
                    <td>${item.precio}</td>
                    <td>${item.cantidad}</td>
                    <td><a href="MainController?op=editar&id=${item.id}">MODIFICAR</a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}"
                           onclick="return(confirm('Está seguro de eliminar el registro???'))">ELIMINAR</a></td>
                </tr>
            </c:forEach> 
            
        </table>
    </body>
</html>
