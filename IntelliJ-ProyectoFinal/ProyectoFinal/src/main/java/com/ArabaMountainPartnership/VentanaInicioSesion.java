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
    private int n = 0;

    public VentanaInicioSesion() {
        buttonAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cargamos de nuevo la lista si hemos registrado a alguien
                if (n > 0) {
                    usuarios = new ArrayList<>(UsuarioBD.usuarios());
                }
                //creamos un estado que de primeras será false
                boolean usuarioCorrecto = false;
                //comprobamos que los datos introducidos existen y coinciden (usuario y contraseña)
                String usuarioIntroducido = textUser.getText();
                String contrasenyaIntroducida = String.valueOf(passwordField.getPassword());
                System.out.println(usuarioIntroducido);
                System.out.println(contrasenyaIntroducida);
                for (int i = 0; i < usuarios.size(); i++) {
                    System.out.println(usuarios.get(i).getNombre());
                    System.out.println(usuarios.get(i).getContrasenya());
                    System.out.println(usuarios.get(i).getSocio());
                    if (usuarios.get(i).getNombre().equalsIgnoreCase(usuarioIntroducido) &&
                            usuarios.get(i).getContrasenya().equalsIgnoreCase(contrasenyaIntroducida)) {
                        usuario = usuarios.get(i);
                        usuarioCorrecto = true;
                    }
                }
                if (usuarioCorrecto) {
                    //pasamos
                    VentanaPrincipal vp = new VentanaPrincipal();
                    vp.setUsuario(usuario);
                    JFrame frame = new JFrame("Ventana principal");
                    frame.setContentPane(vp.getPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    String codigo = usuario.getSocio();
                    Socio socio = SocioBD.socio(codigo);
                    if (socio.getPerfil() == TipoPerfil.USUARIO) {
                        vp.getPanelEspecialAdministrador().setVisible(false);
                    }
                    vp.getNombreUsuarioLabel().setText(usuario.getNombre());
                    if (socio.isHaPagado()) {
                        vp.getNoHaPagadoPrimLabel().setText("Cuota no pagada.");
                        vp.getNoHaPagadoSegLabel().setText("Pague su cuota antes del");
                        vp.getNoHaPagadoTercLabel().setText("31 de Diciembre");
                    }
                    frame.setVisible(true);
                    frame2.dispose();
                } else {
                    //mostramos error
                    JOptionPane.showMessageDialog(null,
                            "Usuario y/o contraseña incorrectos.",
                            "Error",
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
                vr.setUsuarios(usuarios);
                frame.pack();
                frame.setVisible(true);
                n++;
            }
        });
    }

    public void setFrame2(JFrame frame) {
        this.frame2 = frame;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
