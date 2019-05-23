package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPago {
    private JPanel panel1;
    private JButton pagarConPayPalButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JLabel estadoPagoLabel;
    private JLabel mensaje1Label;
    private JLabel mensaje2Label;
    private JButton pagarButton;
    private JButton volverButton;
    private JLabel precioCuota;
    private JLabel mensajePrecioCuota;
    private JFrame frame2;
    private Usuario usuario;
    private boolean pagarActividad = false;
    private Actividad actividad;

    public VentanaPago() {
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    estadoPagoLabel.setText("Procesando datos");
                    Thread.sleep(250);
                    estadoPagoLabel.setText("Procesando datos.");
                    Thread.sleep(250);
                    estadoPagoLabel.setText("Procesando datos..");
                    Thread.sleep(250);
                    estadoPagoLabel.setText("Procesando datos...");
                    Thread.sleep(250);
                    estadoPagoLabel.setText("Procesando datos.");
                    Thread.sleep(300);
                    estadoPagoLabel.setText("Procesando datos..");
                    Thread.sleep(300);
                    estadoPagoLabel.setText("Cotejando datos");
                    Thread.sleep(500);
                    estadoPagoLabel.setText("Cobrando el pago");
                    Thread.sleep(300);
                    mensaje1Label.setText("Es muuuy seguro no tener que introducir ningún dato más");
                    Thread.sleep(300);
                    mensaje2Label.setText("No te preocupes " + SocioBD.socio(usuario.getSocio()).getNombre() + ", en ArabaMountainPartnership no robamos");
                    Thread.sleep(500);
                    mensaje1Label.setText("");
                    mensaje2Label.setText("");
                    if (pagarActividad) {
                        ParticipacionBD.guardarParticipacion(usuario.getSocio(), actividad.getCodigo());
                    }
                    estadoPagoLabel.setText("Pago realizado");
                    mensaje1Label.setText("Cerrando ventana");
                    Thread.sleep(300);
                    SocioBD.pagarCuota(usuario.getSocio());
                    frame2.dispose();

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
            }
        });
    }

    public JLabel getPrecioCuota() {
        return precioCuota;
    }

    public JLabel getMensajePrecioCuota() {
        return mensajePrecioCuota;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setFrame2(JFrame frame2) {
        this.frame2 = frame2;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public void setPagarActividad(boolean pagarActividad) {
        this.pagarActividad = pagarActividad;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaPago");
        frame.setContentPane(new VentanaPago().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
