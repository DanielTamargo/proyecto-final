package com.ArabaMountainPartnership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActividadBD {

    public static Actividad actividad(String codigo) {
        Actividad actividad = null;

        Connection conexion = GestorBD.conectar();
        try {
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM ACTIVIDADES WHERE CODIGO = '" + codigo + "'";
            ResultSet rs = st.executeQuery(sql);


            if (rs.next()) {
                Socio socio = SocioBD.socio(rs.getString("organizador"));
                actividad = new Actividad(
                        rs.getString("codigo"),
                        rs.getString("tipo"),
                        rs.getDate("fecha"),
                        rs.getString("descripcion"),
                        rs.getString("dificultad"),
                        rs.getDouble("precio"),
                        rs.getString("motivoSuspension"),
                        socio
                );
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        GestorBD.desconectar();
        return actividad;
    }


    public static void guardar(Actividad actividad) {

        Connection conexion = GestorBD.conectar();

        try {
            String sql;
            PreparedStatement st;
            sql = "INSERT INTO ACTIVIDADES (codigo, tipo, fecha, descripcion, dificultad, precio, motivoSuspension, organizador) " +
                    "VALUES(?,?,?,?,?,?,?,?)";
            st = conexion.prepareStatement(sql);
            String codigo = ActividadBD.generarCodigo();
            String tipo;
            String dificultad;

            if (actividad.getActividad() == TipoActividad.ALBERGUEFINDESEMANA) {
                tipo = "ALBERGUE FIN DE SEMANA";
            } else if (actividad.getActividad() == TipoActividad.SALIDAALMONTE) {
                tipo = "SALIDA AL MONTE";
            } else if (actividad.getActividad() == TipoActividad.REUNION) {
                tipo = "REUNIÓN";
            } else if (actividad.getActividad() == TipoActividad.COMIDA) {
                tipo = "COMIDA";
            } else {
                tipo = "OTROS";
            }
            if (actividad.getDificultad() == TipoDificultad.ALTA) {
                dificultad = "ALTA";
            } else if (actividad.getDificultad() == TipoDificultad.MEDIA) {
                dificultad = "MEDIA";
            } else {
                dificultad = "BAJA";
            }
            
            st.setString(1, codigo);
            st.setString(2, tipo);
            st.setDate(3, Date.valueOf(actividad.getFecha()));
            st.setString(4, actividad.getDescripcion());
            st.setString(5, dificultad);
            st.setDouble(6, actividad.getPrecio());
            if (actividad.getMotivoSuspension() == null) {
                st.setNull(7, java.sql.Types.NULL);
            } else {
                st.setString(7, actividad.getMotivoSuspension());
            }
            st.setString(8, actividad.getOrganizador().getCodigo());

            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        GestorBD.desconectar();
    }

    public static String generarCodigo() {
        String codigo;
        List<Actividad> actividads = ActividadBD.actividadesOrdeandasFecha();

        boolean estado;
        Random r = new Random();
        int numero;
        int numLetra;
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //26
        char letra;
        do {
            estado = true;
            numero = r.nextInt(9999);
            numLetra = r.nextInt(26);
            letra = abc.charAt(numLetra);
            codigo = String.valueOf(numero) + letra;
            for (int i = 0; i < actividads.size(); i++) {
                if (codigo.equalsIgnoreCase(actividads.get(i).getCodigo())) {
                    estado = false;
                }
            }
        } while (!estado);
        return codigo;
    }

    public static void suspenderActividad(String codigo, String motivoSuspension) {
        Connection conexion = GestorBD.conectar();

        try {
            String sql;
            PreparedStatement st;
            sql = "UPDATE ACTIVIDADES SET MOTIVOSUSPENSION = ? WHERE CODIGO = ?";
            st = conexion.prepareStatement(sql);

            st.setString(1, motivoSuspension);
            st.setString(2, codigo);

            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        GestorBD.desconectar();
    }

    public static List<Actividad> actividadesOrganizadasPorUnSocio(String codigoSocio) {

        List<Actividad> lista = new ArrayList<>();

        Connection conexion = GestorBD.conectar();

        try {
            //primer st sql y rs para recoger los datos del socio en sí
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM ACTIVIDADES WHERE CODIGO = '" + codigoSocio + "' ORDER BY FECHA DESC";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Socio socio = SocioBD.socio(rs.getString("organizador"));
                //añadimos todos los datos del socio correctamente creados a la lista
                lista.add(new Actividad(
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

        GestorBD.desconectar();

        return lista;
    }

    public static List<Actividad> actividadesOrdeandasFechaDesc() {

        List<Actividad> lista = new ArrayList<>();

        Connection conexion = GestorBD.conectar();

        try {
            //primer st sql y rs para recoger los datos del socio en sí
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM ACTIVIDADES ORDER BY FECHA DESC";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Socio socio = SocioBD.socio(rs.getString("organizador"));
                //añadimos todos los datos del socio correctamente creados a la lista
                lista.add(new Actividad(
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

        GestorBD.desconectar();

        return lista;
    }

    public static List<Actividad> actividadesOrdeandasFecha() {

        List<Actividad> lista = new ArrayList<>();

        Connection conexion = GestorBD.conectar();

        try {
            //primer st sql y rs para recoger los datos del socio en sí
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM ACTIVIDADES ORDER BY FECHA ASC";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Socio socio = SocioBD.socio(rs.getString("organizador"));
                //añadimos todos los datos del socio correctamente creados a la lista
                lista.add(new Actividad(
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

        GestorBD.desconectar();

        return lista;
    }


}
