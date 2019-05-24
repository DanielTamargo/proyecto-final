package com.ArabaMountainPartnership;

//import jdk.vm.ci.meta.Local;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FechaDisponibleBD {

    //pasando solo la fecha (tipo LocalDate)
    public static void eliminarFecha(LocalDate dato) {

        Connection conexion = GestorBD.conectar();
        //String codigo = dato.getSocio().getCodigo();

        try {
            String sql;
            PreparedStatement st;
            sql = "DELETE FROM FECHASDISPONIBLES WHERE FECHA = ?";
            st = conexion.prepareStatement(sql);
            Date fecha = Date.valueOf(dato);
            st.setDate(1, fecha);
            //st.setString(2, codigo);

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //GestorBD.desconectar();

    }

    public static void guardar(LocalDate fechaLD, String codigo) {
        Connection conexion = GestorBD.conectar();

        try {
            String sql;
            PreparedStatement st;
            sql = "INSERT INTO FECHASDISPONIBLES VALUES (?,?)";
            st = conexion.prepareStatement(sql);
            Date fecha = Date.valueOf(fechaLD);
            st.setDate(1, fecha);
            st.setString(2, codigo);

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //GestorBD.desconectar();
    }

    //pasando el dato en tipo FechaDisponible
    public static void eliminarFecha(FechaDisponible dato) {

        Connection conexion = GestorBD.conectar();
        //String codigo = dato.getSocio().getCodigo();

        try {
            String sql;
            PreparedStatement st;
            sql = "DELETE FROM FECHASDISPONIBLES WHERE FECHA = ?";
            st = conexion.prepareStatement(sql);
            Date fecha = Date.valueOf(dato.getSocio().getFechaAlta());
            st.setDate(1, fecha);
            //st.setString(2, codigo);

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //GestorBD.desconectar();

    }

    public static boolean fechaDisponibleLibre(LocalDate fechaLD) {

        boolean fechaLibre = true;
        //Date fecha = Date.valueOf(fechaLD);
        LocalDate now = LocalDate.now();

        if (fechaLD.isBefore(now)) {
            return false;
        }

        List<FechaDisponible> lista = fechasDisponibles();

        for (int i = 0; i < lista.size(); i++) {
            if (fechaLD.equals(lista.get(i).getFecha())) {
                fechaLibre = false;
                i = lista.size();
            }
        }

        return fechaLibre;
    }

    public static List<FechaDisponible> fechasDisponibles(){

        List<FechaDisponible> lista = new ArrayList<>();
        Connection conexion = GestorBD.conectar();

        try {
            //primer st sql y rs para recoger los datos del socio en sí
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM FECHASDISPONIBLES";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Socio socio = SocioBD.socio(rs.getString("socio"));
                lista.add(new FechaDisponible(
                        rs.getDate("fecha"),
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
