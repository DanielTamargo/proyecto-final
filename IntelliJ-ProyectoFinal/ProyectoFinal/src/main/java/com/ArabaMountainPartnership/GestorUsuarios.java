/*package com.ArabaMountainPartnership;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {

    private Map<String, String> usuarios() {

        Map<String, String> listaUsuarios = new HashMap<>();
        Connection conexion = GestorBD.conectar();

        try {
            Statement st = conexion.createStatement();
            String sql = "SELECT nombre, contrasenya FROM USUARIOS";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                listaUsuarios.put();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaUsuarios;
    }



}
*/