package proyectofinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Metodos {
    
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:/home/local/DANIELCASTELAO/apinodominguez/Descargas/SQLiteStudio/BasesDeDatos/ProyectoFinal.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public String usuarioExiste(String usuario) {
        String sql = "SELECT nombre FROM users where (nombre) = (?)";
        String nombreU = "0";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            ResultSet rs  = pstmt.executeQuery();
            nombreU = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
        return nombreU;
    }
    
    public String contraseñaExiste(String contraseña) {
        String sql = "SELECT password FROM users where (password) = (?)";
        String password = "0";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contraseña);
            ResultSet rs  = pstmt.executeQuery();
            password = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
        return password;
    }
    
    public String tipoUsuario(String nombre) {
        String sql = "SELECT tipo FROM users where (nombre) = (?)";
        String password = "0";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs  = pstmt.executeQuery();
            password = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
        return password;
    }
    
    public void registrarse(String nombre, String contraseña, String tipo){
        String sql = "INSERT INTO users(nombre,password,tipo) values(?,?,?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, contraseña);
            pstmt.setString(3, tipo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"patatas");
        }
    }
    
}
