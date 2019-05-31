package com.ArabaMountainPartnership;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class VentanaRegistrarse {
    private JPanel panel;
    private JButton aceptarButton;
    private JButton volverButton;
    private JTextField textField1nombre;
    private JTextField textField2apellidos;
    private JTextField textField3email;
    private JTextField textField5dni;
    private JTextField textField7codSocResp;
    private JPasswordField passwordField1Contrasenya;
    private JTextField textField8Usuario;
    private DatePicker datePickerFechaNac;
    private JTextField textField1telefono;
    private List<Socio> socios = SocioBD.socios();
    private List<Usuario> usuarios;
    private JFrame frame5;

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setFrame5(JFrame frame5) {
        this.frame5 = frame5;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaRegistrarse");
        frame.setContentPane(new VentanaRegistrarse().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public VentanaRegistrarse() {
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = textField1nombre.getText();
                    String apellidos = textField2apellidos.getText();
                    String email = textField3email.getText();
                    //Date fechaNac = Date.valueOf(datePickerFechaNac.getDate());
                    String dni = textField5dni.getText();
                    String codigoRespons = textField7codSocResp.getText();
                    String usuario = textField8Usuario.getText();
                    String contrasenya = String.valueOf(passwordField1Contrasenya.getPassword());
                    int telefono = Integer.parseInt(textField1telefono.getText());

                    //comprobamos que el usuario no esté repetido
                    boolean usuarioUnico = true;

                    for (int i = 0; i < usuarios.size(); i++) {
                        if (usuarios.get(i).getNombre().equalsIgnoreCase(usuario)) {
                            usuarioUnico = false;
                        }
                    }

                    if (usuarioUnico) {
                        Socio socioResponsable = SocioBD.socios().get(0); //le cargamos un valor "padre" por si acaso para evitar fallos
                        LocalDate now = LocalDate.now();
                        //cuidado con que las fechas no estén al revés en el Period.between
                        int anyosEntreFechas = Period.between(datePickerFechaNac.getDate(), now).getYears();
                        if (anyosEntreFechas < 18) {
                            if (anyosEntreFechas < 5) {
                                //mostramos error
                                JOptionPane.showMessageDialog(null,
                                        "La edad no puede ser inferior a 4 años.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                if (codigoRespons.equalsIgnoreCase("")) {
                                    //mostramos error
                                    JOptionPane.showMessageDialog(null,
                                            "Si eres menor, tienes que tener un socio responsable.",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    boolean estado = false;
                                    LocalDate fechaNacSoc;
                                    for (int i = 0; i < socios.size(); i++) {
                                        if (socios.get(i).getCodigo().equalsIgnoreCase(codigoRespons)) {
                                            fechaNacSoc = socios.get(i).getFechaNac();
                                            anyosEntreFechas = Period.between(fechaNacSoc, now).getYears();
                                            if (anyosEntreFechas > 17) {
                                                socioResponsable = socios.get(i);
                                                i = socios.size();
                                                estado = true;
                                            }
                                        }
                                    }
                                    if (estado) {
                                        //añadimos el nuevo socio a la base
                                        Socio nuevoSocio = new Socio(
                                                SocioBD.generarCodigo(nombre, apellidos),
                                                nombre,
                                                apellidos,
                                                datePickerFechaNac.getDate(),
                                                dni,
                                                telefono,
                                                email,
                                                now,
                                                socioResponsable);
                                        SocioBD.guardar(nuevoSocio);
                                        //añadimos el usuario y la contraseña también
                                        Usuario nuevoUsuario = new Usuario(usuario, contrasenya, nuevoSocio.getCodigo());
                                        UsuarioBD.guardar(nuevoUsuario);
                                        JOptionPane.showMessageDialog(null,
                                                "Se ha registrado el socio correctamente, apunta tu nombre de usuario y contraseña.",
                                                "Proceso realizado",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                "No se ha encontrado el socio con el código \" + codigoRespons + \".",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                        frame5.dispose();
                                    }
                                }
                            }
                        } else {
                            if (!codigoRespons.equalsIgnoreCase("")) {
                                JOptionPane.showMessageDialog(null,
                                        "Eres mayor de edad, elimina el código del socio responsable.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                //faltaría comprobar que los datos no estén vacíos
                                //añadimos el nuevo socio a la base
                                Socio nuevoSocio = new Socio(
                                        SocioBD.generarCodigo(nombre, apellidos),
                                        nombre,
                                        apellidos,
                                        datePickerFechaNac.getDate(),
                                        dni,
                                        telefono,
                                        email,
                                        now);
                                SocioBD.guardar(nuevoSocio);
                                //añadimos el usuario y la contraseña también
                                Usuario nuevoUsuario = new Usuario(usuario, contrasenya, nuevoSocio.getCodigo());
                                UsuarioBD.guardar(nuevoUsuario);
                                JOptionPane.showMessageDialog(null,
                                        "Se ha registrado el socio correctamente, recuerde su usuario y contraseña",
                                        "Proceso realizado",
                                        JOptionPane.INFORMATION_MESSAGE);
                                frame5.dispose();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "El usuario debe ser único.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Introduce un número de teléfono, sin caracteres.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame5.dispose();
            }
        });
    }
}
