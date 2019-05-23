package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaListaJunta {
    private JPanel panel;
    private JButton button1siguiente;
    private JButton button2anterior;
    private JLabel cargoLabel;
    private JLabel nombreLabel;
    private JLabel apellidosLabel;
    private JLabel fechaNacLabel;
    private JButton volverButton;
    private JFrame frame2;
    private List<Cargo> cargos = CargoBD.cargosBasic(); //solo tiene 2 datos, cargo y socio
    private int n = 0;

    public VentanaListaJunta() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
            }
        });
        button2anterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n == 0) {
                    n = cargos.size() - 1;
                } else {
                    n--;
                }
                actualizarDatos(n);
            }
        });
        button1siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((n + 1) == cargos.size()) {
                    n = 0;
                } else {
                    n++;
                }
                actualizarDatos(n);
            }
        });
    }

    public void actualizarDatos(int n) {
        Socio socio = cargos.get(n).getSocio();
        //el switch me da un error así que lo hago con ifs para evitarlo
        if (cargos.get(n).getTipo() == TipoCargo.PRESIDENTE) {
            cargoLabel.setText("Presidente");
        } else if (cargos.get(n).getTipo() == TipoCargo.VICEPRESIDENTE) {
            cargoLabel.setText("Vicepresidente");
        } else if (cargos.get(n).getTipo() == TipoCargo.SECRETARIO) {
            cargoLabel.setText("Secretario");
        } else if (cargos.get(n).getTipo() == TipoCargo.TESORERO) {
            cargoLabel.setText("Tesorero");
        } else {
            cargoLabel.setText("Vocal");
        }
        nombreLabel.setText(socio.getNombre());
        apellidosLabel.setText(socio.getApellidos());
        fechaNacLabel.setText(socio.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setFrame2(JFrame frame2) {
        this.frame2 = frame2;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaListaJunta");
        frame.setContentPane(new VentanaListaJunta().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
