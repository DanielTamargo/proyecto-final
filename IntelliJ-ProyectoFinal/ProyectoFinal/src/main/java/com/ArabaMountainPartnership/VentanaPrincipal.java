package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private JPanel panel;
    private JLabel nombreUsuarioLabel;
    private JButton verFichaPersonalButton;
    private JButton verListaDeSociosButton;
    private JButton verCalendarioActividadesButton;
    private JButton organizarActividadButton;
    private JButton verActividadesOrganizadasButton;
    private JLabel noHaPagadoPrimLabel;
    private JLabel noHaPagadoSegLabel;
    private JLabel noHaPagadoTercLabel;
    private JButton crearFechaDisponibleButton;
    private JPanel panelEspecialAdministrador;
    private JButton anyadirSocioAJuntaButton;
    private Usuario usuario;

    public VentanaPrincipal() {
        //Ver Ficha Personal
        verFichaPersonalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaFichaPersonal vfp = new VentanaFichaPersonal();
                JFrame frame = new JFrame("Ficha personal");
                frame.setContentPane(vfp.getPanel());
                if (SocioBD.socio(usuario.getSocio()).isHaPagado()) {
                    vfp.getPagarCuotaButton().setVisible(false);
                }
                vfp.setUsuario(usuario);
                vfp.setFrame(frame);
                vfp.actualizarDatos(usuario.getSocio());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        //Ver Lista de Socios ó Junta
        verListaDeSociosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaJuntaOSocios vjos = new VentanaJuntaOSocios();
                JFrame frame = new JFrame("Socios O Junta");
                frame.setContentPane(vjos.getPanel());
                vjos.setFrame2(frame);
                vjos.setUsuario(usuario);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        //Ver Calendario de Actividades
        verCalendarioActividadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCalendario vc = new VentanaCalendario();
                JFrame frame = new JFrame("Calendario");
                frame.setContentPane(vc.getPanel());
                vc.setFrame2(frame);
                vc.getApuntarseButton().setVisible(false);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        //Organizar Actividad
        organizarActividadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaOrganizarActividad voa = new VentanaOrganizarActividad();
                JFrame frame = new JFrame("Organizar Actividad");
                frame.setContentPane(voa.getPanel());
                voa.setFrame2(frame);
                voa.setUsuario(usuario);
                voa.actualizarComboBoxFecha();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        //Ver Actividades Organizadas
        verActividadesOrganizadasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaActividadesOrganizadas vao = new VentanaActividadesOrganizadas();
                JFrame frame = new JFrame("Actividades Organizadas");
                frame.setContentPane(vao.getPanel());
                if (SocioBD.socio(usuario.getSocio()).getPerfil() != TipoPerfil.ADMINISTRADOR) {
                    vao.getVerComoAdministradorButton().setVisible(false);
                }
                vao.setFrame2(frame);
                vao.setUsuario(usuario);
                vao.setActividadesOrganizadas(ActividadBD.actividadesOrganizadasPorUnSocio(usuario.getSocio()));
                vao.actualizarListaActividades();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        //(ADMIN) Crear Fecha Disponible
        crearFechaDisponibleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCrearFD vcfd = new VentanaCrearFD();
                JFrame frame = new JFrame("Crear Fecha Disponible");
                frame.setContentPane(vcfd.getPanel());
                vcfd.setFrame2(frame);
                vcfd.setUsuario(usuario);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        //(ADMIN) Añadir socio a la junta
        anyadirSocioAJuntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAnyadirSocioAJunta vasa = new VentanaAnyadirSocioAJunta();
                JFrame frame = new JFrame("Añadir Socio a Junta");
                frame.setContentPane(vasa.getPanel());
                vasa.actualizarListaSocios();
                vasa.setFrame2(frame);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public JLabel getNombreUsuarioLabel() {
        return nombreUsuarioLabel;
    }

    public JLabel getNoHaPagadoPrimLabel() {
        return noHaPagadoPrimLabel;
    }

    public JLabel getNoHaPagadoSegLabel() {
        return noHaPagadoSegLabel;
    }

    public JLabel getNoHaPagadoTercLabel() {
        return noHaPagadoTercLabel;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public JPanel getPanelEspecialAdministrador() {
        return panelEspecialAdministrador;
    }

    public JPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana principal");
        frame.setContentPane(new VentanaPrincipal().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
