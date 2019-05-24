package com.ArabaMountainPartnership;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaOrganizarActividad {
    private JPanel panel;
    private JComboBox comboBox1Dificultad;
    private JComboBox comboBox2Tipo;
    private JComboBox comboBox3Fecha;
    private JTextField textField1Descripcion;
    private JButton confirmarButton;
    private JButton volverButton;
    private JTextField textField1Precio;
    private JFrame frame2;
    private Usuario usuario;

    public VentanaOrganizarActividad() {
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoDificultad dificultad = TipoDificultad.BAJA;
                String dificultadSeleccionada = (String)comboBox1Dificultad.getSelectedItem();
                TipoActividad actividad = TipoActividad.OTROS;
                String actividadSeleccionada = (String)comboBox2Tipo.getSelectedItem();
                String codigo = ActividadBD.generarCodigo();
                String descripcion = textField1Descripcion.getText();
                if (dificultadSeleccionada.equalsIgnoreCase("ALTA")) {
                    dificultad = TipoDificultad.ALTA;
                } else if (dificultadSeleccionada.equalsIgnoreCase("MEDIA")) {
                    dificultad = TipoDificultad.MEDIA;
                }

                if (actividadSeleccionada.equalsIgnoreCase("ALBERGUE FIN DE SEMANA")) {
                    actividad = TipoActividad.ALBERGUEFINDESEMANA;
                } else if (actividadSeleccionada.equalsIgnoreCase("SALIDA AL MONTE")) {
                    actividad = TipoActividad.SALIDAALMONTE;
                } else if (actividadSeleccionada.equalsIgnoreCase("REUNIÓN")) {
                    actividad = TipoActividad.REUNION;
                } else if (actividadSeleccionada.equalsIgnoreCase("COMIDA")) {
                    actividad = TipoActividad.COMIDA;
                }
                String fechaTexto1 = (String)comboBox3Fecha.getSelectedItem();
                char letra;
                String fechaTexto = "";
                letra = fechaTexto1.charAt(6);
                fechaTexto = fechaTexto + letra;
                letra = fechaTexto1.charAt(7);
                fechaTexto = fechaTexto + letra;
                letra = fechaTexto1.charAt(8);
                fechaTexto = fechaTexto + letra;
                letra = fechaTexto1.charAt(9);
                fechaTexto = fechaTexto + letra;
                fechaTexto = fechaTexto + "-";
                letra = fechaTexto1.charAt(3);
                fechaTexto = fechaTexto + letra;
                letra = fechaTexto1.charAt(4);
                fechaTexto = fechaTexto + letra;
                fechaTexto = fechaTexto + "-";
                letra = fechaTexto1.charAt(0);
                fechaTexto = fechaTexto + letra;
                letra = fechaTexto1.charAt(1);
                fechaTexto = fechaTexto + letra;

                System.out.println(fechaTexto);
                LocalDate fecha = LocalDate.parse(fechaTexto);
                Socio socio = SocioBD.socio(usuario.getSocio());
                double precio;
                try {
                    precio = Double.parseDouble(textField1Precio.getText());
                    Actividad actividadNueva = new Actividad(codigo, actividad, fecha, dificultad, descripcion, precio, socio);
                    //guardamos la actividad y eliminamos la fecha disponible
                    ActividadBD.guardar(actividadNueva);
                    FechaDisponibleBD.eliminarFecha(fecha);
                    JOptionPane.showMessageDialog(null,
                            "Actividad creada correctamente, si quieres suspenderla accede a tus actividades organizadas desde el menú.",
                            "Actividad creada",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame2.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Se ha producido un error. Comprueba haber escogido una fecha y que el precio esté bien introducido.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
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

    public void actualizarComboBoxFecha() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        List<FechaDisponible> listaFechasDisponibles = FechaDisponibleBD.fechasDisponibles();
        LocalDate now = LocalDate.now();

        //confiando en que las actividades estén ordenadas por fecha ascendente
        for (int i = 0; i < listaFechasDisponibles.size(); i++) {
            if (now.isBefore(listaFechasDisponibles.get(i).getFecha())) {
                modelo.addElement(listaFechasDisponibles.get(i).getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }

        comboBox3Fecha.setModel(modelo);
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaOrganizarActividad");
        frame.setContentPane(new VentanaOrganizarActividad().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
