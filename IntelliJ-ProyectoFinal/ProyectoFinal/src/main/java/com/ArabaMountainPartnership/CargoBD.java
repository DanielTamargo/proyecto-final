package com.ArabaMountainPartnership;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CargoBD {

    public static void guardar(Cargo cargo) {
        //TipoCargo tipo, LocalDate fechaInicio, LocalDate fechaFin, Junta junta, Socio socio
        Date fechaInicio = Date.valueOf(cargo.getFechaInicio());
        Date fechaFin = Date.valueOf(cargo.getFechaFin());
        String tipoCargoStr;
        if (cargo.getTipo() == TipoCargo.PRESIDENTE) {
            tipoCargoStr = "PRESIDENTE";
        } else if (cargo.getTipo() == TipoCargo.VICEPRESIDENTE) {
            tipoCargoStr = "VICEPRESIDENTE";
        } else if(cargo.getTipo() == TipoCargo.TESORERO) {
            tipoCargoStr = "TESORERO";
        } else if(cargo.getTipo() == TipoCargo.SECRETARIO) {
            tipoCargoStr = "SECRETARIO";
        } else {
            tipoCargoStr = "VOCAL";
        }
        Date fechaIniJun = Date.valueOf(cargo.getJunta().getFechaInicio());
        String codSoc = cargo.getSocio().getCodigo();
        Connection conexion = GestorBD.conectar();

        try {
            String sql;
            PreparedStatement st;
            //comprobar este insert
            sql = "INSERT INTO CARGOS VALUES(?,?,?,?,?)";
            st = conexion.prepareStatement(sql);

            st.setString(1, tipoCargoStr);
            st.setDate(2, fechaInicio);
            st.setDate(3, fechaFin);
            st.setString(4, codSoc);
            st.setDate(5, fechaIniJun);

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        GestorBD.desconectar();
    }

    public static List<Cargo> cargosJuntaActiva(Junta junta){

        List<Cargo> lista = new ArrayList<>();
        Connection conexion = GestorBD.conectar();

        try {
            //primer st sql y rs para recoger los datos del socio en sí
            Statement st = conexion.createStatement();
            String localDateString = junta.getFechaInicio().format(DateTimeFormatter.ISO_DATE);

            String sql = "SELECT * FROM CARGOS WHERE FECHAINICIOJUNTA = '" + localDateString + "'";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                //añadimos todos los datos del socio correctamente creados a la lista
                lista.add(new Cargo(
                        rs.getString("tipo"),
                        rs.getDate("fechaFin"),
                        rs.getString("codSoc")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        GestorBD.desconectar();
        return lista;
    }

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
