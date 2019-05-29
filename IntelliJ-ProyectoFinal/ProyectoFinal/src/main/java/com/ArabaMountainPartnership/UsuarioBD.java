package com.ArabaMountainPartnership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioBD {

    public static void guardar(Usuario usuario) {

        Connection conexion = GestorBD.conectar();

        try {
            String sql;
            PreparedStatement st;

            sql = "INSERT INTO USUARIOS(nombre, contrasenya, socio) VALUES(?,?,?)";
            st = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getContrasenya());
            st.setString(3, usuario.getSocio());

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //GestorBD.desconectar();
    }

    public static List<Usuario> usuarios() {
        List<Usuario> lista = new ArrayList<>();
        Connection conexion = GestorBD.conectar();
        int n = 0;
        try {
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM USUARIOS";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                n++;
                lista.add(new Usuario(
                        rs.getString("nombre"),
                        rs.getString("contrasenya"),
                        rs.getString("socio")
                ));
                System.out.println(n);
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //GestorBD.desconectar();

        return lista;
    }


}
