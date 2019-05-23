package com.ArabaMountainPartnership;

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaCalendario {
    private JPanel panel;
    private CalendarPanel datePicker1;
    private JLabel tipoActividadLabel;
    private JLabel dificultadLabel;
    private JLabel precioLabel;
    private JLabel motivoSuspLabel;
    private JLabel organizadorLabel;
    private JButton volverButton;
    private JLabel actividadAnteriorLabel;
    private JLabel actividadFuturaLabel;
    private JFrame frame2;
    private List<Actividad> actividades = ActividadBD.actividadesOrdeandasFecha();


    public VentanaCalendario() {

        //es muy probable que falte el listener del datepicker (calendar panel en verdad) pero no lo consigo sacar
        actualizarDatosActividad(datePicker1.getSelectedDate());

        datePicker1.addCalendarListener(new CalendarListener() {
            @Override
            public void selectedDateChanged(CalendarSelectionEvent calendarSelectionEvent) {
                actualizarDatosActividad(datePicker1.getSelectedDate());
            }

            @Override
            public void yearMonthChanged(YearMonthChangeEvent yearMonthChangeEvent) {

            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
            }
        });

    }

    public void actualizarDatosActividad(LocalDate fecha) {
        boolean fechaExiste = false;
        Actividad actividad = new Actividad();

        for (int i = 0; i < actividades.size(); i++) {
            if (actividades.get(i).getFecha().equals(fecha)) {
                fechaExiste = true;
                actividad = actividades.get(i);
                i = actividades.size();
            }
        }

        if (fechaExiste) {
            if (actividad.getActividad() == TipoActividad.ALBERGUEFINDESEMANA) {
                tipoActividadLabel.setText("Albergue fin de semana");
            } else if (actividad.getActividad() == TipoActividad.SALIDAALMONTE) {
                tipoActividadLabel.setText("Salida al monte");
            } else if (actividad.getActividad() == TipoActividad.REUNION) {
                tipoActividadLabel.setText("Reunión");
            } else if (actividad.getActividad() == TipoActividad.COMIDA) {
                tipoActividadLabel.setText("Comida");
            } else {
                tipoActividadLabel.setText("Otros");
            }
            if (actividad.getDificultad() == TipoDificultad.ALTA) {
                dificultadLabel.setText("Alta");
            } else if (actividad.getDificultad() == TipoDificultad.MEDIA) {
                dificultadLabel.setText("Media");
            } else {
                dificultadLabel.setText("Baja");
            }
            precioLabel.setText(String.valueOf(actividad.getPrecio()));
            if (actividad.getMotivoSuspension() == null) {
                motivoSuspLabel.setText("Sin suspender");
            } else {
                motivoSuspLabel.setText(actividad.getMotivoSuspension());
            }
            organizadorLabel.setText(actividad.getOrganizador().toString());
        } else {
            tipoActividadLabel.setText("No hay actividad este día");
            dificultadLabel.setText("");
            precioLabel.setText("");
            organizadorLabel.setText("");
            motivoSuspLabel.setText("");
        }

        modificarFechaFuturaYAnterior(fecha, fechaExiste);
    }

    public void modificarFechaFuturaYAnterior(LocalDate fecha, boolean fechaExiste) {
        Actividad actividad = new Actividad();
        int n = -50;
        int anterior;
        int siguiente;
        //confiando en que las actividades estén ordenadas por el select order by asc
        if (fechaExiste) {
            for (int i = 0; i < actividades.size(); i++) {
                if (actividad.getFecha().equals(fecha)) {
                    n = i;
                    i = actividades.size();
                }
            }
            if (n != -50) {
                if (n > 0) {
                    anterior = n -1;
                    actividadAnteriorLabel.setText("Actividad anterior el: " + actividades.get(anterior).getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                } else {
                    actividadAnteriorLabel.setText("No hay actividades anteriores a esta");
                }
                if (n < actividades.size() -1) {
                    siguiente = n + 1;
                    actividadFuturaLabel.setText("Actividad futura el: " + actividades.get(siguiente).getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                } else {
                    actividadFuturaLabel.setText("No hay actividades posteriores a esta");
                }
            } else {
                actividadAnteriorLabel.setText("Esto no debería estar pasando x.x");
                actividadFuturaLabel.setText("Esto no debería estar pasando x.x");
            }
        } else {
            boolean fechaAnteriorExiste = false;
            int lastActividadBefore = - 50;
            for (int i = 0; i < actividades.size(); i++) {
                if (actividades.get(i).getFecha().isBefore(fecha)) {
                    fechaAnteriorExiste = true;
                    lastActividadBefore = i;
                }
            }
            if (!fechaAnteriorExiste) {
                actividadAnteriorLabel.setText("No hay actividades anteriores a la fecha seleccionada");
                actividadFuturaLabel.setText("Actividad futura a la fecha seleccionada el: " + actividades.get(0).getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } else {
                actividadAnteriorLabel.setText("Actividad anterior a la fecha seleccionada el: " + actividades.get(lastActividadBefore).getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                if (lastActividadBefore + 2 < actividades.size()) {
                    actividadFuturaLabel.setText("Actividad futura a la fecha seleccionada el: " + actividades.get((lastActividadBefore + 2)).getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                } else {
                    actividadFuturaLabel.setText("No hay actividades futuras a la fecha seleccionada");
                }
            }
        }


    }

    public JPanel getPanel() {
        return panel;
    }

    public void setFrame2(JFrame frame2) {
        this.frame2 = frame2;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendario");
        frame.setContentPane(new VentanaCalendario().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
