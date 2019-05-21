package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicioSesion {
    private JPanel panel;
    private JTextField textUser;
    private JButton buttonAccept;
    private JButton buttonRegister;
    private JPasswordField passwordField;

    public VentanaInicioSesion() {
        buttonAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inicio de sesión");
        frame.setContentPane(new VentanaInicioSesion().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
