package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerListaParticipantes {
    private JPanel panel;
    private JButton volverButton;
    private JScrollPane panelParticipantes;
    private String codigoActividad;
    private JFrame frame2;

    //private List<Socio> participantes;
    private JTable tabla;

    public VerListaParticipantes() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
            }
        });
    }

    public void actualizarDatos() {
        ModeloParticipaciones mp = new ModeloParticipaciones(codigoActividad);
        mp.setCodigoActividad(codigoActividad);
        tabla = new JTable();
        tabla.setModel(mp);
        panelParticipantes.setViewportView(tabla);

    }

    public JScrollPane getPanelParticipantes() {
        return panelParticipantes;
    }

    public JTable getTabla() {
        return tabla;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public void setFrame2(JFrame frame2) {
        this.frame2 = frame2;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VerListaParticipantes");
        frame.setContentPane(new VerListaParticipantes().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
