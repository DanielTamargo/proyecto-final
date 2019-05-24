package com.ArabaMountainPartnership;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class VentanaCrearFD {
    private JPanel panel;
    private DatePicker datePicker1;
    private JButton guardarButton;
    private JButton volverButton;
    private Usuario usuario;
    private JFrame frame2;


    public VentanaCrearFD() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate fecha = datePicker1.getDate();
                if (FechaDisponibleBD.fechaDisponibleLibre(fecha)) {
                    FechaDisponibleBD.guardar(fecha, usuario.getSocio());
                    JOptionPane.showMessageDialog(null,
                            "La fecha ha sido guardada.",
                            "Proceso realizado",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame2.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Esta fecha ya existe ó bien es anterior a la fecha actual.",
                            "Error",
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
        JFrame frame = new JFrame("VentanaCrearFD");
        frame.setContentPane(new VentanaCrearFD().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
