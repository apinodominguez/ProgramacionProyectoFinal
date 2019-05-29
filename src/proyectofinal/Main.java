package proyectofinal;

public class Main {

    public static void main(String[] args) {
        Conexion.connect();
        Registro objR = new Registro();
        objR.setVisible(true);
    }
    
}
