package com.ArabaMountainPartnership;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
//import java.util.Date;
import java.sql.Date;
import java.util.List;
import java.util.Random;

public class SocioBD {

    public static void pagarCuota(String codigo) {

        Connection conexion = GestorBD.conectar();
        //String haPagado = "SI";
        try {
            String sql;
            PreparedStatement st;
            sql = "UPDATE SOCIOS SET HAPAGADO = 'SI' WHERE CODIGO = ?";
            st = conexion.prepareStatement(sql);

            st.setString(1, codigo);

            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ////GestorBD.desconectar();
    }

    public static String generarCodigo(String nombre, String apellidos) {
        String codigo;
        List<Socio> socios = SocioBD.socios();
        char n = nombre.charAt(0);
        char a = apellidos.charAt(0);
        boolean estado = false;
        Random r = new Random();
        int numero;
        do {
            estado = true;
            numero = r.nextInt(999);
            codigo = n + a + String.valueOf(numero);
            for (int i = 0; i < socios.size(); i++) {
                if (codigo.equalsIgnoreCase(socios.get(i).getCodigo())) {
                    estado = false;
                }
            }
        } while (!estado);
        return codigo;
    }

    public static void cambiarEmailTelefono(String codigo, String email, int telefono) {
        Connection conexion = GestorBD.conectar();
        try {
            String sql;
            PreparedStatement st;

            sql = "UPDATE SOCIOS SET EMAIL = ?, TELEFONO = ? WHERE CODIGO = ?";
            st = conexion.prepareStatement(sql);

            st.setString(1, email);
            st.setInt(2, telefono);
            st.setString(3, codigo);

            st.executeUpdate();
            st.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
        ////GestorBD.desconectar();
    }

    public static void guardar(Socio socio) {
        Connection conexion = GestorBD.conectar();

        try {
            String sql;
            PreparedStatement st;
            //comprobar este insert
            sql = "INSERT INTO SOCIOS (codigo, nombre, apellidos, fechaNac, " +
                    "dni, telefono, email, perfil, fechaAlta, fechaBaja, haPagado, importeCuota, " +
                    "anyoValidezCuota, socioResponsable VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            st = conexion.prepareStatement(sql);
            st.setString(1, socio.getCodigo());
            st.setString(2, socio.getNombre());
            st.setString(3, socio.getApellidos());
            Date fecha = Date.valueOf(socio.getFechaAlta());
            st.setDate(4, fecha);
            if (socio.getDni() != null && !socio.getDni().equalsIgnoreCase("")) {
                st.setString(5, socio.getDni());
            } else {
                st.setNull(5, java.sql.Types.NULL);
            }
            st.setInt(6, socio.getTelefono());
            st.setString(7, socio.getEmail());
            if (socio.getPerfil() == TipoPerfil.ADMINISTRADOR) {
                st.setString(8, "ADMINISTRADOR");
            } else {
                st.setString(8, "USUARIO");
            }
            //LocalDate fechaNow = LocalDate.now();
            //Date fechaAlta = Date.valueOf(fechaNow);
            Date fechaAlta = Date.valueOf(socio.getFechaAlta());
            st.setDate(9, fechaAlta);
            st.setNull(10, java.sql.Types.NULL);
            if (socio.isHaPagado()) {
                st.setString(11, "SI");
            } else {
                st.setString(11, "NO");
            }
            if (socio.getCuota() == null) { //si es null le ponemos la última cuota creada
                socio.setCuota(CuotaBD.cuotas().get(CuotaBD.cuotas().size() - 1));
            }
            Date anyoValidezCuota = Date.valueOf(socio.getCuota().getAnyoValidez());
            st.setDouble(12, socio.getCuota().getImporte());
            st.setDate(13, anyoValidezCuota);
            if (socio.getSocioResponsable().getCodigo() == null ||
                    socio.getSocioResponsable().getCodigo().equalsIgnoreCase("")) {
                st.setNull(14, java.sql.Types.NULL);
            } else {
                LocalDate now = LocalDate.now();
                int anyosEntreFechas = Period.between(socio.getSocioResponsable().getFechaNac(), now).getYears();
                //si el socio responsable es mayor de edad en efecto
                if (anyosEntreFechas > 17) {
                    st.setString(14, socio.getSocioResponsable().getCodigo());
                } else { //en caso contrario se le asigna un socio adulto al azar
                    // (para evitar posibles fallos, pero este caso no debería llegar a pasar
                    List<Socio> socios = SocioBD.socios();
                    LocalDate fechaNacSoc;
                    for (int i = 0; i < socios.size(); i++) {
                        fechaNacSoc = socios.get(i).getFechaNac();
                        now = LocalDate.now();
                        anyosEntreFechas = Period.between(fechaNacSoc, now).getYears();
                        if (anyosEntreFechas > 17) {
                            st.setString(14, socios.get(i).getCodigo());
                            i = socios.size();
                        }
                    }
                }
            }
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ////GestorBD.desconectar();
    }

    public static Socio socio(String codigo) {
        Socio socio = null;

        Connection conexion = GestorBD.conectar();
        try {
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM SOCIOS WHERE CODIGO = '" + codigo + "'";
            ResultSet rs = st.executeQuery(sql);
            double importe;
            Date anyoValidez;
            if (rs.next()) {
                importe = rs.getDouble("importeCuota");
                anyoValidez = rs.getDate("anyoValidezCuota");
                Cuota cuota = new Cuota(importe, anyoValidez);
                socio = new Socio(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fechaNac"),
                        //rs.getString("fechaNac"),
                        rs.getString("dni"),
                        rs.getInt("telefono"),
                        rs.getString("email"),
                        rs.getString("perfil"),
                        rs.getDate("fechaAlta"),
                        rs.getDate("fechaBaja"),
                        rs.getString("haPagado"),
                        cuota
                );
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //////GestorBD.desconectar();
        return socio;
    }

    public static void recogerActividadesOrganizadas(String codigo) {
        Connection conexion = GestorBD.conectar();

        try {
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM ACTIVIDADES WHERE ORGANIZADOR = '" + codigo + "'";
            ResultSet rs = st.executeQuery(sql);

            Socio socio = new Socio();
            for (int i = 0; i < socios().size(); i++) {
                if (socios().get(i).getCodigo().equalsIgnoreCase(codigo)) {
                    socio = socios().get(i);
                }
            }

            while (rs.next()) {
                socio.addActividades(new Actividad(
                        rs.getString("codigo"),
                        rs.getString("tipo"),
                        rs.getDate("fecha"),
                        rs.getString("descripcion"),
                        rs.getString("dificultad"),
                        rs.getDouble("precio"),
                        rs.getString("motivoSuspension"),
                        socio
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ////GestorBD.desconectar();
    }

    //enviamos código de socio, recogemos importe y año validez
    public static void recogerDatosCuotaSocio(String codigo) {
        Connection conexion = GestorBD.conectar();
        try {
            Statement stcuota = conexion.createStatement();
            String sqlcuota;
            ResultSet rscuota;

            sqlcuota = "SELECT IMPORTECUOTA, ANYOVALIDEZCUOTA FROM SOCIOS WHERE CODIGO = '" + codigo + "'";
            rscuota = stcuota.executeQuery(sqlcuota);

            if (rscuota.next()) {
                for (int i = 0; i < socios().size(); i++) {
                    if (socios().get(i).getCodigo().equalsIgnoreCase(codigo)) {
                        socios().get(i).setCuota(new Cuota(
                                rscuota.getDouble("importeCuota"),
                                rscuota.getDate("anyoValidezCuota")));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ////GestorBD.desconectar();
    }

    public static void recogerDatosCargo(String codigo) {

        Connection conexion = GestorBD.conectar();

        try {
            Statement stcargo = conexion.createStatement();
            String sqlcargo;
            ResultSet rscargo;

            sqlcargo = "SELECT * FROM CARGOS WHERE CODSOC = '" + codigo + "'";
            rscargo = stcargo.executeQuery(sqlcargo);

            if (rscargo.next()) {
                for (int i = 0; i < socios().size(); i++) {
                    if (socios().get(i).getCodigo().equalsIgnoreCase(codigo)) {
                        socios().get(i).setCargo(new Cargo(
                                rscargo.getString("tipo"),
                                rscargo.getDate("fechaInicio"),
                                rscargo.getDate("fechaFin")));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ////GestorBD.desconectar();
    }

    //pasar dos Strings, el código del socio y el código del socio responsable
    public static void recogerDatosSocioResponsable(String codigo, String codigoSocioResponsable) {

        Connection conexion = GestorBD.conectar();

        try {
            Statement stsocioresp = conexion.createStatement();
            String sqlsocioresp;
            ResultSet rssocioresp;

            sqlsocioresp = "SELECT * FROM SOCIOS WHERE CODIGO = '" + codigoSocioResponsable + "'";
            rssocioresp = stsocioresp.executeQuery(sqlsocioresp);
            if (rssocioresp.next()) {
                for (int i = 0; i < socios().size(); i++) {
                    if (socios().get(i).getCodigo().equalsIgnoreCase(codigo)) {
                        socios().get(i).setSocioResponsable(new Socio(
                                rssocioresp.getString("codigo"),
                                rssocioresp.getString("nombre"),
                                rssocioresp.getString("apellidos"),
                                rssocioresp.getDate("fechaNac"),
                                //rssocioresp.getString("fechaNac"),
                                rssocioresp.getString("dni"),
                                rssocioresp.getInt("telefono"),
                                rssocioresp.getString("email"),
                                rssocioresp.getString("perfil"),
                                rssocioresp.getDate("fechaAlta"),
                                rssocioresp.getDate("fechaBaja"),
                                rssocioresp.getString("haPagado")));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ////GestorBD.desconectar();
    }

    //recoge una lista de los datos base y más principales de los socios
    public static List<Socio> socios() {

        List<Socio> lista = new ArrayList<>();
        Connection conexion = GestorBD.conectar();

        try {
            //primer st sql y rs para recoger los datos del socio en sí
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM SOCIOS";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                //añadimos todos los datos del socio correctamente creados a la lista
                lista.add(new Socio(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fechaNac"),
                        //rs.getString("fechaNac"),
                        rs.getString("dni"),
                        rs.getInt("telefono"),
                        rs.getString("email"),
                        rs.getString("perfil"),
                        rs.getDate("fechaAlta"),
                        rs.getDate("fechaBaja"),
                        rs.getString("haPagado")));
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ////GestorBD.desconectar();

        return lista;
    }

}


