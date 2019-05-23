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
            /*
            * eqdam07@//SrvOracle:1521/orcl
            * Nombre de conexión: ProyectoFinal
            * Usuario: eqdam07
            * Contraseña: eqdam07
            * Nombre del Host: SrvOracle
            * Puerto: 1521
            * SID: orcl
            * */

            if (conexion == null || conexion.isClosed()) {
                //Driver
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

                //Creamos los Strings para la conexión
                String usuario = "eqdam07";
                String contrasenya = "eqdam07";
                String url = "jdbc:oracle:thin:@10.10.10.9:1521:orcl";

                //Se establece la conexión
                conexion = DriverManager.getConnection(url, usuario, contrasenya);

                //JBDC = Java DataBase Connectivity, es una API que permite la ejecución de operaciones sobre las BBDD
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
