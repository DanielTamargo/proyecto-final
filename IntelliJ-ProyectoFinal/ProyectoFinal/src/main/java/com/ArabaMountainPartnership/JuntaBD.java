package com.ArabaMountainPartnership;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class JuntaBD {

    public static void guardar(Junta junta) {

        Date fechaInicio = Date.valueOf(junta.getFechaInicio());
        Date fechaFin = Date.valueOf(junta.getFechaFin());

        Connection conexion = GestorBD.conectar();

        try {
            String sql;
            PreparedStatement st;
            //comprobar este insert
            sql = "INSERT INTO JUNTAS VALUES(?,?)";
            st = conexion.prepareStatement(sql);

            st.setDate(1, fechaInicio);
            st.setDate(2, fechaFin);

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ////GestorBD.desconectar();

    }

    public static Junta juntaActiva() {

        Junta junta = null;
        List<Junta> juntas = new ArrayList<>();
        Connection conexion = GestorBD.conectar();
        try {
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM JUNTAS";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                juntas.add(new Junta(
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin")
                        ));
            }

            junta = juntas.get(juntas.size() - 1);
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ////GestorBD.desconectar();

        return junta;
    }

}
