package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





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

    public VentanaFichaPersonal() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //JFrame frame = new JFrame("Ficha personal");
                //frame.setContentPane(panel);
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //frame.pack();
                //frame.setVisible(false);

                //setVisible(false); //you can't see me!
                /*try {
                    dispose(); //Destroy the JFrame object
                } catch (Exception ex) {
                    ex.printStackTrace();
                }*/
            }
        });
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
