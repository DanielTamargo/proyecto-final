package com.ArabaMountainPartnership;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CuotaBD {

    public static List<Cuota> cuotas() {

        List<Cuota> lista = new ArrayList<>();

        Connection conexion = GestorBD.conectar();

        try {
            //primer st sql y rs para recoger los datos de la cuota en sí
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM CUOTAS";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                //añadimos todos los datos de la cuota correctamente a la lista
                lista.add(new Cuota(
                        rs.getDouble("importe"),
                        rs.getDate("anyoValidez")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        GestorBD.desconectar();



        return lista;
    }


}
