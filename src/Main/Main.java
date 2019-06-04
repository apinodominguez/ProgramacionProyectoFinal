package Main;

import Metodos.Conexion;
import Interfaces.Registro;

public class Main {

    public static void main(String[] args) {
        Conexion.conexionInicial();
        Registro objR = new Registro();
        objR.setVisible(true);
    }
    
}
