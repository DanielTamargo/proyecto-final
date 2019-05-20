package com.ArabaMountainPartnership;

import javax.swing.*;

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


    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana principal");
        frame.setContentPane(new VentanaPrincipal().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
