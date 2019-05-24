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
    private JButton verComoAdministradorButton;
    private JButton verParticipantesButton;
    private JFrame frame2;
    private Usuario usuario;
    private List<Actividad> actividadesOrganizadas;
    private int n = 1;

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
                                "Actividad suspendida correctamente.",
                                "Actividad suspendida",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Introduce al menos 20 caracteres en el motivo de suspensión.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No puedes suspender una actividad que ya ha sido suspendida.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        verComoAdministradorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socio socio = SocioBD.socio(usuario.getSocio());
                if (socio.getPerfil() == TipoPerfil.ADMINISTRADOR) {
                    if (n % 2 != 0) {
                        actividadesOrganizadas = ActividadBD.actividadesOrdeandasFechaDesc();
                    } else {
                        actividadesOrganizadas = ActividadBD.actividadesOrganizadasPorUnSocio(usuario.getSocio());
                    }
                    n++;
                    actualizarListaActividades();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No puedes acceder a esta lista si no eres administrador.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        verParticipantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerListaParticipantes vlp = new VerListaParticipantes();
                JFrame frame = new JFrame("Lista Participantes");
                frame.setContentPane(vlp.getPanel());
                Actividad actividad = (Actividad) list1.getSelectedValue();
                vlp.actualizarDatos();
                vlp.setCodigoActividad(actividad.getCodigo());
                vlp.setFrame2(frame);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
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

    public JButton getVerComoAdministradorButton() {
        return verComoAdministradorButton;
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
