package com.ArabaMountainPartnership;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorBD {

    private static Connection conexion;

    //2 métodos, conectar (crear driver, ruta de conexión y ejecutar conexión) y desconectar (terminar conexión)
    //es posible que tengamos que pasarle al método conectar los valores de login y password (usuario y contraseña)
    public static Connection conectar() {

        try {
            if (conexion == null || conexion.isClosed()) {
                //Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                //Creamos los Strings para la conexión
                //ESTOS STRINGS SON LOS DEL EJEMPLO, HAY QUE CAMBIARLOS Y BORRAR ESTE COMENTARIO
                String servidor = "192.168.33.10";
                String puerto = "3306";
                String bd = "videoclub";
                String login = "root";
                String password = "root";
                String opciones = "?verifyServerCertificate=false&useSSL=true&requireSSL=false";
                String url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + bd + opciones;
                //JBDC = Java DataBase Connectivity, es una API que permite la ejecución de operaciones sobre las BBDD

                //Se establece la conexión
                conexion = DriverManager.getConnection(url, login, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conexion;
    }

    public static void desconectar() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
