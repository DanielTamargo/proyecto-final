package com.ArabaMountainPartnership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParticipacionBD {

    public static void guardarParticipacion(String codigoSocio, String codigoActividad) {

        Connection conexion = GestorBD.conectar();

        try {
            String sql;
            PreparedStatement st;
            sql = "INSERT INTO PARTICIPACIONES (socio, actividad) VALUES(?,?)";
            st = conexion.prepareStatement(sql);
            st.setString(1, codigoSocio);
            st.setString(2, codigoActividad);

            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        GestorBD.desconectar();

    }

}
