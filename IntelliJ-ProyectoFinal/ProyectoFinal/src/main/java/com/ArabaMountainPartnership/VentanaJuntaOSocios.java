package com.ArabaMountainPartnership;

import javax.swing.*;

public class VentanaJuntaOSocios {
    private JPanel panel;
    private JButton verJuntaButton;
    private JButton verSociosButton;


    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaJuntaOSocios");
        frame.setContentPane(new VentanaJuntaOSocios().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}