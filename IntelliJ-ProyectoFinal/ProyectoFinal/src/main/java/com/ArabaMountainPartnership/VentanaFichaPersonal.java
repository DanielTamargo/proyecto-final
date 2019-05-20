package com.ArabaMountainPartnership;

import javax.swing.*;

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


    public static void main(String[] args) {
        JFrame frame = new JFrame("Ficha personal");
        frame.setContentPane(new VentanaFichaPersonal().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
