<%@page import="com.emergentes.modelo.Producto"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Producto prod = (Producto) request.getAttribute("prod");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${prod.id==0}">
                NUEVO PRODUCTO
            </c:if>
            <c:if test="${prod.id!=0}">
                EDITAR PRODUCTO
            </c:if>
        </h1>
        <form action="MainController" method="post">
            <table>
                <input type="hidden" name="id" value="${prod.id}">
                <tr>
                    <td>PRODUCTO: </td>
                    <td><input type="text" name="producto" value="${prod.producto}"></td>
                </tr>
                <tr>
                    <td>PRECIO: </td>
                    <td><input type="number" name="precio" min=1 step="0.1"value="${prod.precio}"></td>
                </tr>
                <tr>
                    <td>CANTIDAD: </td>
                    <td><input type="number" name="cantidad" min=1 value="${prod.cantidad}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
