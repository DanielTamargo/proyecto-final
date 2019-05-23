package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
