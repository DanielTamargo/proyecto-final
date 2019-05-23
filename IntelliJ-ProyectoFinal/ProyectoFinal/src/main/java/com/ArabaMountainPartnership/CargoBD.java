package com.ArabaMountainPartnership;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CargoBD {

    public static List<Cargo> cargosBasic(){

        List<Cargo> lista = new ArrayList<>();
        Connection conexion = GestorBD.conectar();

        try {
            //primer st sql y rs para recoger los datos del socio en sí
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM CARGOS";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                //añadimos todos los datos del socio correctamente creados a la lista
                lista.add(new Cargo(
                        rs.getString("tipo"),
                        rs.getString("codSoc")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        GestorBD.desconectar();
        return lista;
    }

}
