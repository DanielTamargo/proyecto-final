package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCambiarDatosFP {
    private JPanel panel;
    private JButton volverButton;
    private JButton guardarButton;
    private JTextField textField1email;
    private JTextField textField2telefono;
    private JFrame frame2;
    private Usuario usuario;

    public VentanaCambiarDatosFP() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String email = textField1email.getText();
                    int telefono = Integer.parseInt(textField2telefono.getText());
                    SocioBD.cambiarEmailTelefono(usuario.getSocio(), email, telefono);
                    JOptionPane.showMessageDialog(null,
                            "Datos actualizados.",
                            "Datos actualizados correctamente.",
                            JOptionPane.ERROR_MESSAGE);
                    frame2.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error.",
                            "Introduce un teléfono (solo números, sin letras ni caracteres especiales).",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setFrame2(JFrame frame2) {
        this.frame2 = frame2;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaCambiarDatosFP");
        frame.setContentPane(new VentanaCambiarDatosFP().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
