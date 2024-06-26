package Model;

//@author Rodrigo

public class Probarconexion {

    public static void main(String[] args) {
        Conexion cnn = new Conexion();
        System.out.println(cnn.getConexion());
    }
    //No olvides Importar la Libreria
    //  mysql-connector-java-5.1.15-bin
}
