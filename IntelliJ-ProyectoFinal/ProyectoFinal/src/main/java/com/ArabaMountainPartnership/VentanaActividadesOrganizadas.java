package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaActividadesOrganizadas {
    private JPanel panel;
    private JLabel codigoLabel;
    private JLabel tipoLabel;
    private JLabel dificultadLabel;
    private JLabel precioLabel;
    private JLabel fechaLabel;
    private JLabel descripcionLabel;
    private JLabel motivoSuspLabel;
    private JList list1;
    private JButton suspenderActividadButton;
    private JButton volverButton;
    private JTextField textField1MotivoSusp;
    private JFrame frame2;
    private Usuario usuario;
    private List<Actividad> actividadesOrganizadas;

    public VentanaActividadesOrganizadas() {
        list1.addListSelectionListener(e -> {
            Actividad actividad = (Actividad) list1.getSelectedValue();
            codigoLabel.setText(actividad.getCodigo());
            if (actividad.getActividad() == TipoActividad.ALBERGUEFINDESEMANA) {
                tipoLabel.setText("Albergue fin de semana");
            } else if (actividad.getActividad() == TipoActividad.SALIDAALMONTE) {
                tipoLabel.setText("Salida al monte");
            } else if (actividad.getActividad() == TipoActividad.REUNION) {
                tipoLabel.setText("Reunión");
            } else if (actividad.getActividad() == TipoActividad.COMIDA) {
                tipoLabel.setText("Comida");
            } else {
                tipoLabel.setText("Otros");
            }
            if (actividad.getDificultad() == TipoDificultad.ALTA) {
                dificultadLabel.setText("Alta");
            } else if (actividad.getDificultad() == TipoDificultad.MEDIA) {
                dificultadLabel.setText("Media");
            } else {
                dificultadLabel.setText("Baja");
            }
            precioLabel.setText(String.valueOf(actividad.getPrecio()));
            fechaLabel.setText(actividad.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            descripcionLabel.setText(actividad.getDescripcion());
            if (actividad.getMotivoSuspension() == null || actividad.getMotivoSuspension().equalsIgnoreCase("")) {
                motivoSuspLabel.setText("No ha sido suspendida");
            } else {
                motivoSuspLabel.setText(actividad.getMotivoSuspension());
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
            }
        });
        suspenderActividadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Actividad actividad = (Actividad) list1.getSelectedValue();
                if (actividad.getMotivoSuspension().equalsIgnoreCase("") || actividad.getMotivoSuspension() == null) {
                    if (textField1MotivoSusp.getText().length() >= 20) {
                        ActividadBD.suspenderActividad(actividad.getCodigo(), textField1MotivoSusp.getText());
                        actividadesOrganizadas = ActividadBD.actividadesOrganizadasPorUnSocio(usuario.getSocio());
                        actualizarListaActividades();
                        JOptionPane.showMessageDialog(null,
                                "Actividad suspendida.",
                                "Actividad suspendida correctamente.",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error.",
                                "Introduce al menos 20 caracteres en el motivo de suspensión.",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Error.",
                            "No puedes suspender una actividad que ya ha sido suspendida.",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    public void actualizarListaActividades() {
        DefaultListModel<Actividad> modelo = new DefaultListModel<>();
        for (Actividad a: actividadesOrganizadas) {
            modelo.addElement(a);
        }
        list1.setModel(modelo);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setFrame2(JFrame frame2) {
        this.frame2 = frame2;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setActividadesOrganizadas(List<Actividad> actividadesOrganizadas) {
        this.actividadesOrganizadas = actividadesOrganizadas;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaActividadesOrganizadas");
        frame.setContentPane(new VentanaActividadesOrganizadas().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
