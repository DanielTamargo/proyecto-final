package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;


public class VentanaFichaPersonal {
    private JPanel panel;
    private JButton volverButton;
    private JButton cambiarDatosButton;
    private JLabel nombreLabel;
    private JLabel apellidosLabel;
    private JLabel codigoLabel;
    private JLabel emailLabel;
    private JLabel telefonoLabel;
    private JLabel fechaAltaLabel;
    private JLabel fechaBajaLabel;
    private JLabel dniLabel;
    private JLabel perfilLabel;
    private JLabel cuotaPagadaLabel;
    private JButton pagarCuotaButton;
    private JFrame frame;
    private Usuario usuario;

    //este método lo ejecutaremos en el momento que abrimos la ventana desde ficha personal y actualizará los datos de la misma ficha
    public void actualizarDatos(String codigo) {
        Socio socio = SocioBD.socio(usuario.getSocio());

        nombreLabel.setText(socio.getNombre());
        apellidosLabel.setText(socio.getApellidos());
        codigoLabel.setText(socio.getCodigo());
        emailLabel.setText(socio.getEmail());
        telefonoLabel.setText(String.valueOf(socio.getTelefono()));
        fechaAltaLabel.setText(socio.getFechaAlta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if (socio.getFechaBaja() != null) {
            fechaBajaLabel.setText(socio.getFechaBaja().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } else {
            fechaBajaLabel.setText("No se ha dado de baja aún");
        }
        if (socio.getDni() != null) {
            dniLabel.setText(socio.getDni());
        } else {
            dniLabel.setText("No tiene un dni guardado");
        }
        if (socio.getPerfil() == TipoPerfil.ADMINISTRADOR) {
            perfilLabel.setText("Adminstrador");
        } else {
            perfilLabel.setText("Usuario normal");
        }
        if (socio.isHaPagado()) {
            cuotaPagadaLabel.setText("Sí");
        } else {
            cuotaPagadaLabel.setText("No");
        }
    }

    public VentanaFichaPersonal() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        cambiarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCambiarDatosFP vcdfp = new VentanaCambiarDatosFP();
                JFrame frame = new JFrame("Cambiar Datos");
                frame.setContentPane(vcdfp.getPanel());
                vcdfp.setFrame2(frame);
                vcdfp.setUsuario(usuario);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        pagarCuotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!SocioBD.socio(usuario.getSocio()).isHaPagado()) {
                    VentanaPago vp = new VentanaPago();
                    JFrame frame = new JFrame("Ventana Pago Cuota");
                    vp.setFrame2(frame);
                    vp.setUsuario(usuario);
                    LocalDate now = LocalDate.now();
                    LocalDate fechaSocio = SocioBD.socio(usuario.getSocio()).getFechaNac();
                    int dif = Period.between(fechaSocio, now).getYears();
                    double precio = 15;
                    if (dif < 18) {
                        vp.getMensajePrecioCuota().setText("Precio total (cuota infantil): ");
                        try {
                            String sql = "{ call gest_depart.insert_depart(?,?) }";
                            sql = "{ call PAQUETE1.CUOTAINFANTIL(?) }";
                            Connection conn = GestorBD.conectar();
                            CallableStatement cs = conn.prepareCall(sql);
                            cs.setDouble(1, SocioBD.socio(usuario.getSocio()).getCuota().getImporte());
                            cs.execute();
                            //ResultSet rs = (ResultSet) cs.getObject(1);
                            try {
                                precio = cs.getDouble(1);
                            } catch (SQLException ex) {
                                precio = 11.25;
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        precio = SocioBD.socio(usuario.getSocio()).getCuota().getImporte();
                    }
                    vp.getPrecioCuota().setText(String.valueOf(precio));
                    frame.setContentPane(vp.getPanel1());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
    }

    public JButton getPagarCuotaButton() {
        return pagarCuotaButton;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void main(String[] args) {
        JFrame frame = new JFrame("Ficha personal");
        frame.setContentPane(new VentanaFichaPersonal().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
