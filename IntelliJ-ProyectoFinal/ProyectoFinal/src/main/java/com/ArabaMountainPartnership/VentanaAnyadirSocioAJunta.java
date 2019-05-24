package com.ArabaMountainPartnership;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class VentanaAnyadirSocioAJunta {
    private JPanel panel;
    private JList list1;
    private JButton guardarButton;
    private JComboBox comboBox1;
    private JButton volverButton;
    private DatePicker datePicker1;
    private JFrame frame2;
    private List<Socio> socios = SocioBD.socios();


    public VentanaAnyadirSocioAJunta() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate fecha = datePicker1.getDate();
                LocalDate now = LocalDate.now();
                if (fecha.isAfter(now)) {
                    Junta junta = JuntaBD.juntaActiva();
                    List<Cargo> cargos = CargoBD.cargosJuntaActiva(junta);
                    String tipoCargoSeleccionado = (String) comboBox1.getSelectedItem();
                    TipoCargo tipoCargo;
                    int limite = 1;
                    if (tipoCargoSeleccionado.equalsIgnoreCase("Presidente")) {
                        tipoCargo = TipoCargo.PRESIDENTE;
                    } else if (tipoCargoSeleccionado.equalsIgnoreCase("Viceresidente")) {
                        tipoCargo = TipoCargo.VICEPRESIDENTE;
                    } else if (tipoCargoSeleccionado.equalsIgnoreCase("Tesorero")) {
                        tipoCargo = TipoCargo.TESORERO;
                    } else if (tipoCargoSeleccionado.equalsIgnoreCase("Secretario")) {
                        tipoCargo = TipoCargo.SECRETARIO;
                    } else {
                        tipoCargo = TipoCargo.VOCAL;
                        limite = 5;
                    }

                    int n = 0;
                    for (int i = 0; i < cargos.size(); i++) {
                        if (tipoCargo == cargos.get(i).getTipo()) {
                            n++;
                        }
                    }
                    if (n < limite) {
                        //TipoCargo tipo, LocalDate fechaInicio, LocalDate fechaFin, Junta junta, Socio socio
                        Socio socio = (Socio) list1.getSelectedValue();
                        Cargo cargo = new Cargo(tipoCargo, now, fecha, junta, socio);
                        CargoBD.guardar(cargo);
                        JOptionPane.showMessageDialog(null,
                                "¡Cargo guardado correctamente!",
                                "Cargo guardado",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame2.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ya existe el cupo para ese cargo actualmente.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Elige una fecha posterior a la actual.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void actualizarListaSocios() {
        DefaultListModel<Socio> modelo = new DefaultListModel<>();
        Junta junta = JuntaBD.juntaActiva();
        List<Cargo> cargos = CargoBD.cargosJuntaActiva(junta);
        LocalDate now = LocalDate.now();
        boolean estado;
        for (Socio s: socios) {
            estado = true;
            String codigo = s.getCodigo();
            for (int i = 0; i < cargos.size(); i++) {
                if (codigo.equalsIgnoreCase(cargos.get(i).getSocio().getCodigo()) &&
                        cargos.get(i).getFechaFin().isAfter(now)) {
                    estado = false;
                }
            }

            if (estado) {
                modelo.addElement(s);
            }
        }
        list1.setModel(modelo);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setFrame2(JFrame frame2) {
        this.frame2 = frame2;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaAnyadirSocioAJunta");
        frame.setContentPane(new VentanaAnyadirSocioAJunta().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
