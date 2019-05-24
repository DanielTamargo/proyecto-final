package com.ArabaMountainPartnership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        //GestorBD.desconectar();

    }

    public static List<Participacion> participaciones(String codActividad){

        List<Participacion> lista = new ArrayList<>();

        Connection conexion = GestorBD.conectar();

        try {
            //primer st sql y rs para recoger los datos del socio en sí
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM PARTICIPACIONES WHERE ACTIVIDAD = '" + codActividad + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Socio socio = SocioBD.socio(rs.getString("socio"));
                //añadimos todos los datos del socio correctamente creados a la lista
                lista.add(new Participacion(
                        socio
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //GestorBD.desconectar();

        return lista;


    }

}
