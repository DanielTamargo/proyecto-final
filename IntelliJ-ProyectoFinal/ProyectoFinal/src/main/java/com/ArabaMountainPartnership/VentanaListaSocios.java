package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaListaSocios {
    private JPanel panel;
    private JList list1;
    private JButton verJuntaButton;
    private JButton volverButton;
    private JLabel nombreLabel;
    private JLabel apellidosLabel;
    private JLabel adminTelefonoLabel;
    private JLabel telefonoLabel;
    private JLabel adminFechaNacLabel;
    private JLabel fechaNacLabel;
    private JLabel adminDniLabel;
    private JLabel dniLabel;
    private JLabel adminEmailLabel;
    private JLabel emailLabel;
    private JLabel adminPerfilLabel;
    private JLabel perfilLabel;
    private JLabel adminFechaAltaLabel;
    private JLabel fechaAltaLabel;
    private JLabel adminHaPagadoLabel;
    private JLabel haPagadoLabel;
    private List<Socio> socios = SocioBD.socios();
    private JFrame frame2;
    private Usuario usuario;
    private boolean admin = false;

    public VentanaListaSocios() {
        //listener de la lista, cada vez que clicas en un objeto de la lista actualiza los datos
        list1.addListSelectionListener(e -> {
            Socio socio = (Socio) list1.getSelectedValue();
            nombreLabel.setText(socio.getNombre());
            apellidosLabel.setText(socio.getApellidos());
            if (this.admin) {
                telefonoLabel.setText(String.valueOf(socio.getTelefono()));
                dniLabel.setText(socio.getDni());
                emailLabel.setText(socio.getEmail());
                fechaAltaLabel.setText(socio.getFechaAlta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                fechaNacLabel.setText(socio.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                if (socio.getPerfil() == TipoPerfil.ADMINISTRADOR) {
                    perfilLabel.setText("Adminstrador");
                } else {
                    perfilLabel.setText("Usuario normal");
                }
                if (socio.isHaPagado()) {
                    haPagadoLabel.setText("Sí");
                } else {
                    haPagadoLabel.setText("No");
                }
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setFrame2(JFrame frame2) {
        this.frame2 = frame2;
    }

    public void actualizarListaSocios() {
        DefaultListModel<Socio> modelo = new DefaultListModel<>();
        for (Socio s: socios) {
            modelo.addElement(s);
        }
        list1.setModel(modelo);
    }



    public void actualizarDatosAMostrar() {
        Socio socio = SocioBD.socio(usuario.getSocio());
        if (socio.getPerfil() == TipoPerfil.ADMINISTRADOR) {
            adminDniLabel.setText("DNI:");
            adminEmailLabel.setText("Email:");
            adminFechaAltaLabel.setText("Fecha alta:");
            adminHaPagadoLabel.setText("Cuota pagada:");
            adminPerfilLabel.setText("Perfil:");
            adminTelefonoLabel.setText("Teléfono:");
            adminFechaNacLabel.setText("Fecha nacimiento:");
            this.admin = true;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lista Socios");
        frame.setContentPane(new VentanaListaSocios().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
