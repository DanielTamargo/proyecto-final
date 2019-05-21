package com.ArabaMountainPartnership;

import javax.swing.*;

public class VentanaCalendario {
    private JPanel panel;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendario");
        frame.setContentPane(new VentanaCalendario().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
