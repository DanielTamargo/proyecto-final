package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private JPanel panel;
    private JLabel nombreUsuarioLabel;
    private JButton verFichaPersonalButton;
    private JButton verListaDeSociosButton;
    private JButton verCalendarioActividadesButton;
    private JButton organizarActividadButton;
    private JButton verActividadesOrganizadasButton;
    private JLabel noHaPagadoPrimLabel;
    private JLabel noHaPagadoSegLabel;
    private JLabel noHaPagadoTercLabel;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public VentanaPrincipal() {
        //Ver Ficha Personal
        verFichaPersonalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaFichaPersonal vfp = new VentanaFichaPersonal();
                JFrame frame = new JFrame("Ficha personal");
                frame.setContentPane(vfp.getPanel());
                vfp.setUsuario(usuario);
                vfp.setFrame(frame);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });


        //Ver Calendario de Actividades
        verCalendarioActividadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana principal");
        frame.setContentPane(new VentanaPrincipal().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
