/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import Interfaces.InicioU;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angel
 */
public class MetodosLibros extends Conexion {
    
    public void prestamo(String disponible, String nombre, int isbn){
        String sql = "UPDATE libros SET disponible = (?), usuario = (SELECT id FROM users WHERE nombre = (?))  WHERE isbn = (?) ";
        try (Connection conn = super.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, disponible);
            pstmt.setString(2, nombre);
            pstmt.setInt(3, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
    } 

   public void devolver(String disponible, int isbn){
        String sql = "UPDATE libros SET disponible = (?), usuario = 0  WHERE isbn = (?) ";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, disponible);
            pstmt.setInt(2, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
    } 

   public void añadirLibro(String titulo, String autor, String disponible, String user){
       String sql = "INSERT INTO libros(titulo,autorid,disponible,usuario) values(?,(SELECT id FROM autores WHERE nombre = (?)),?, (SELECT id FROM users WHERE nombre = (?))) ";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, autor);
            pstmt.setString(3, disponible);
            pstmt.setString(4, user);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
    }
   
   public void borrarLibros(int isbn){
       String sql = "DELETE FROM libros where isbn = (?)";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
   }
   
   public void modificarTitulo(String titulo, int isbn){
        String sql = "UPDATE libros SET titulo = (?) WHERE isbn = (?)";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setInt(2, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
    }
   
   public void modificarAutor(String autor, int isbn){
        String sql = "UPDATE libros SET autorid = (SELECT id FROM autores WHERE nombre = (?)) WHERE isbn = (?)";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, autor);
            pstmt.setInt(2, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
    }
   
   public void modificarDisponible(String disponible, int isbn){
        String sql = "UPDATE libros SET disponible = (?) WHERE isbn = (?)";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, disponible);
            pstmt.setInt(2, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
    }
   
   public void modificarUsuario(String user, int isbn){
        String sql = "UPDATE libros SET usuario = (SELECT id FROM users WHERE nombre = (?)) WHERE isbn = (?)";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setInt(2, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
    }
   
   public int existeIsbn(int isbn) {
        String sql = "SELECT isbn FROM libros where (isbn) = (?)";
        int valor = 0;
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, isbn);
            ResultSet rs  = pstmt.executeQuery();
            valor = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return valor;
    }
   
   public String existeTitulo(String titulo) {
        String sql = "SELECT titulo FROM libros where titulo = (?)";
        String retorno = "0";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            ResultSet rs  = pstmt.executeQuery();
            retorno = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }

    public int existeAutor(String autor) {
        String sql = "SELECT autorid FROM libros where autorid = (SELECT id FROM autores WHERE nombre =(?))";
        int retorno = 0;
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, autor);
            ResultSet rs  = pstmt.executeQuery();
            retorno = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }
   
    public String existeDisponible(String disponible) {
        String sql = "SELECT disponible FROM libros where disponible = (?)";
        String retorno = "0";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, disponible);
            ResultSet rs  = pstmt.executeQuery();
            retorno = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }
    
    public int existeUsuario(String usuario) {
        String sql = "SELECT usuario FROM libros where usuario = (SELECT id FROM users WHERE nombre =(?))";
        int retorno = 0;
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            ResultSet rs  = pstmt.executeQuery();
            retorno = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }
    
}

    

