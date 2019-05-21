package com.ArabaMountainPartnership;

import javax.swing.*;
import java.util.List;

public class VentanaListaSocios {
    private JPanel panel;
    private JList list1;
    private JButton verJuntaButton;
    private JButton volverButton;
    private JLabel nombreLabel;
    private JLabel apellidosLabel;
    private JLabel cargoLabel;
    private JLabel adminTelefonoLabel;
    private JLabel telefonoLabel;
    private JLabel adminFechaNacLabel;
    private JLabel fechaNacLabel;
    private JLabel adminDniLabel;
    private JLabel dniLabel;
    private JLabel adminEmailLabel;
    private JLabel emailLabel;
    private JLabel adminPerfilLabel;
    private JLabel perfilLabel;
    private JLabel adminFechaAltaLabel;
    private JLabel fechaAltaLabel;
    private JLabel adminHaPagadoLabel;
    private JLabel haPagadoLabel;
    private List<Socio> socios;


    public VentanaListaSocios() {

    }


    public void actualizarListaSocios() {
        DefaultListModel<Socio> modelo = new DefaultListModel<>();

        for (Socio s: socios) {
            modelo.addElement(s);
        }


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaListaSocios");
        frame.setContentPane(new VentanaListaSocios().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
