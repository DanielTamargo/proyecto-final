/*package com.ArabaMountainPartnership;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SocioBD {

    public static List<Socio> socios() {

        List<Socio> lista = new ArrayList<>();
        Connection conexion = GestorBD.conectar();

        try {
            //primer st sql y rs para recoger los datos del socio en sí
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM SOCIOS";
            ResultSet rs = st.executeQuery(sql);

            //cuota
            Statement stcuota = conexion.createStatement();
            String sqlcuota;
            ResultSet rscuota;

            //socio responsable
            Statement stsocioresp = conexion.createStatement();
            String sqlsocioresp;
            ResultSet rssocioresp;

            //cargo
            Statement stcargo = conexion.createStatement();
            String sqlcargo;
            ResultSet rscargo;

            while (rs.next()) {
                //buscamos la cuota y la creamos
                Double importe = rs.getDouble("importeCuota");
                Date anyoValidez = rs.getDate("anyoValidezCuota");
                sqlcuota = "SELECT * FROM CUOTAS WHERE IMPORTE =" + importe + " AND ANYOVALIDEZ =" + anyoValidez;
                rscuota = stcuota.executeQuery(sqlcuota);
                Cuota cuota = (new Cuota(rs.getDouble("importe"),
                        rs.getDate("anyoValidez")));

                //buscamos el socio responsable y lo creamos
                Socio socioResponsable
                String codigoSocioResponsable = rs.getString("socioResponsable");
                sqlsocioresp = "SELECT * FROM SOCIOS WHERE CODIGO=" + codigoSocioResponsable;
                rssocioresp = stsocioresp.executeQuery(sqlsocioresp);
                Socio socioResponsable = (new Socio(
                        rssocioresp.getString()
                ));

                //buscamos el cargo y lo creamos
                String codigo = rs.getString("codigo");
                sqlcargo = "SELECT * FROM CARGOS WHERE CODSOC=" + codigo;
                rscargo = stcargo.executeQuery(sqlcargo);
                Cargo cargo = null;
                if (rscargo.next()){
                    cargo = new Cargo();
                }

                //añadimos todos los datos del socio correctamente creados a la lista
                lista.add(new Socio(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fechaNac"),
                        rs.getString("dni"),
                        rs.getInt("telefono"),
                        rs.getString("email"),
                        rs.getString("perfil"),
                        rs.getDate("fechaAlta"),
                        rs.getDate("fechaBaja"),
                        rs.getString("haPagado"),
                        cuota,
                        socioResponsable,
                        cargo,

                        //faltan las actividades organizadas y las actividades en las que participa
                        //¿voy bien encaminado y tengo que hacer las listas del socio y añadirlas o
                        //tengo que borrar esto y replantearlo?

                this.actividadesOrganizadas = actividadesOrganizadas;
                this.actividades = actividades;


                ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        GestorBD.desconectar();

        return lista;
    }

}

 */
