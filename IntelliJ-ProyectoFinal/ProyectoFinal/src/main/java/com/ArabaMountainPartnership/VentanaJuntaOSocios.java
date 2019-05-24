package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaJuntaOSocios {
    private JPanel panel;
    private JButton verJuntaButton;
    private JButton verSociosButton;
    private JFrame frame2;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public VentanaJuntaOSocios() {
        verSociosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListaSocios vls = new VentanaListaSocios();
                JFrame frame = new JFrame("Lista Socios");
                frame.setContentPane(vls.getPanel());
                vls.setFrame2(frame);
                vls.setUsuario(usuario);
                vls.actualizarDatosAMostrar();
                vls.actualizarListaSocios();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                frame2.dispose();
            }
        });
        verJuntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListaJunta vlj = new VentanaListaJunta();
                JFrame frame = new JFrame("Lista Junta");
                frame.setContentPane(vlj.getPanel());
                vlj.setFrame2(frame);
                vlj.actualizarDatos(0);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                frame2.dispose();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setFrame2(JFrame frame2) {
        this.frame2 = frame2;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaJuntaOSocios");
        frame.setContentPane(new VentanaJuntaOSocios().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
