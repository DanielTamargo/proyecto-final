package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaInicioSesion {
    private JPanel panel;
    private JTextField textUser;
    private JButton buttonAccept;
    private JButton buttonRegister;
    private JPasswordField passwordField;
    private List<Usuario> usuarios = new ArrayList<>(UsuarioBD.usuarios());
    private JFrame frame2;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public VentanaInicioSesion() {
        buttonAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //creamos un estado que de primeras será false
                Usuario usuario = new Usuario();
                boolean estado = false;
                //comprobamos que los datos introducidos existen y coinciden (usuario y contraseña)
                for (int i = 0; i < usuarios.size(); i++) {
                    if (usuarios.get(i).getNombre().equalsIgnoreCase(textUser.getText()) &&
                            usuarios.get(i).getContrasenya().equalsIgnoreCase(String.valueOf(passwordField.getPassword()))) {
                        usuario = usuarios.get(i);
                        estado = true;
                    }
                }
                if (estado) {
                    //pasamos
                    VentanaPrincipal vp = new VentanaPrincipal();
                    vp.setUsuario(usuario);
                    JFrame frame = new JFrame("Ventana principal");
                    frame.setContentPane(vp.getPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    frame2.dispose();

                } else {
                    //mostramos error
                    JOptionPane.showMessageDialog(null,
                            "Error.",
                            "Usuario y/o contraseña incorrectos.",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaRegistrarse vr = new VentanaRegistrarse();
                JFrame frame = new JFrame("Ventana Registrarse");
                frame.setContentPane(vr.getPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                vr.setFrame5(frame);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public void setFrame2(JFrame frame) {
        this.frame2 = frame;
    }

    public static void main(String[] args) {
        VentanaInicioSesion vis = new VentanaInicioSesion();
        JFrame frame = new JFrame("Inicio de sesión");
        frame.setContentPane(vis.panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vis.setFrame2(frame);
        frame.pack();
        frame.setVisible(true);
    }
}
