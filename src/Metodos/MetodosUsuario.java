package Metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetodosUsuario extends Conexion{
    
    public String usuarioExiste(String usuario) {
        String sql = "SELECT nombre FROM users where (nombre) = (?)";
        String nombreU = "0";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            ResultSet rs  = pstmt.executeQuery();
            nombreU = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nombreU;
    }
    
    public String contraseñaExiste(String contraseña) {
        String sql = "SELECT password FROM users where (password) = (?)";
        String password = "0";
         try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contraseña);
            ResultSet rs  = pstmt.executeQuery();
            password = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return password;
    }
    
    public int sacarUsuarioId(String usuario) {
        String sql = "SELECT id FROM users where (nombre) = (?)";
        int id = 0;
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            ResultSet rs  = pstmt.executeQuery();
            id = rs.getInt(1);
        } catch (SQLException e) {
        }
        return id; 
    }
    
     public String sacarUsuarioNombre(int id) {
        String sql = "SELECT nombre FROM users where id = (?)";
        String nombreU = "0";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();
            nombreU = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nombreU;
    }
    
    public String tipoUsuario(String nombre) {
        String sql = "SELECT tipo FROM users where (nombre) = (?)";
        String password = "0";
         try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs  = pstmt.executeQuery();
            password = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return password;
    }
    
    public void registrarse(String nombre, String contraseña, String tipo){
        String sql = "INSERT INTO users(nombre,password,tipo) values(?,?,?)";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, contraseña);
            pstmt.setString(3, tipo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void modificarUsuario(String nombreV, String contraseñaN, String nombreN){
        String sql = "UPDATE users SET nombre = (?), password = (?) WHERE nombre = (?)";
        try (Connection conn = super.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreN);
            pstmt.setString(2, contraseñaN);
            pstmt.setString(3, nombreV);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
