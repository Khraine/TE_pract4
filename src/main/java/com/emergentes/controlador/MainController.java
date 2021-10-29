package com.emergentes.controlador;
import com.emergentes.modelo.Producto;
import com.emergentes.utiles.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = (request.getParameter("op")!=null)?request.getParameter("op"):"listar";
        ArrayList<Producto> lista = new ArrayList<Producto>();
        ConexionBD canal = new ConexionBD();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        if (op.equals("listar")) {
            String sql = "select * from productos";
            try{
                ps=conn.prepareStatement(sql);
                rs=ps.executeQuery();
                while(rs.next()){
                    Producto prod = new Producto();
                    prod.setId(rs.getInt("id"));
                    prod.setProducto(rs.getString("producto"));
                    prod.setPrecio(rs.getFloat("precio"));
                    prod.setCantidad(rs.getInt("cantidad"));
                    lista.add(prod);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }catch(SQLException ex){
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }if (op.equals("nuevo")) {
            Producto prodct = new Producto();
            System.out.println(prodct.toString());
            request.setAttribute("prod", prodct);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }if (op.equals("editar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try{
                Producto prod1 = new Producto();
                ps=conn.prepareStatement("select * from productos where id=?");
                ps.setInt(1, id);
                rs=ps.executeQuery();
                if (rs.next()) {
                    prod1.setId(rs.getInt("id"));
                    prod1.setProducto(rs.getString("producto"));
                    prod1.setPrecio(rs.getFloat("precio"));
                    prod1.setCantidad(rs.getInt("cantidad"));
                }
                request.setAttribute("prod",prod1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                
            }catch(SQLException ex){
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }if (op.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try{
                ps=conn.prepareStatement("delete from productos where id=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                response.sendRedirect("MainController");
            }catch(SQLException ex){
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("Valor del ID "+id);
            String producto = request.getParameter("producto");
            float precio = Float.parseFloat(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            Producto prod = new Producto();
            prod.setId(id);
            prod.setProducto(producto);
            prod.setPrecio(precio);
            prod.setCantidad(cantidad);
            ConexionBD canal = new ConexionBD();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if (id==0) {
                String sql = "insert into productos (producto,precio,cantidad) values (?,?,?)";
                ps=conn.prepareStatement(sql);
                ps.setString(1, prod.getProducto());
                ps.setFloat(2, prod.getPrecio());
                ps.setInt(3, prod.getCantidad());
                ps.executeUpdate();
            }else{
                String sql1 = "update productos set producto=?,precio=?,cantidad=? where id=?";
                try{
                    ps=conn.prepareStatement(sql1);
                    ps.setString(1, prod.getProducto());
                    ps.setFloat(2, prod.getPrecio());
                    ps.setInt(3, prod.getCantidad());
                    ps.setInt(4, prod.getId());
                    ps.executeUpdate();
                }catch(SQLException ex){
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            response.sendRedirect("MainController");
        }catch(SQLException ex){
            System.out.println("Error en SQL"+ex.getMessage());
        }
            
    }
}
