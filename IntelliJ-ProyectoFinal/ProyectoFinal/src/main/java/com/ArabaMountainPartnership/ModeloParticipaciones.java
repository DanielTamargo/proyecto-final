package com.ArabaMountainPartnership;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Esta clase se encarga de configurar un modelo, el cual usará un JTable mostrando los datos que le asignamos,
 * en este caso los datos son Nombre, Apellidos, Teléfono, Email.
 * <p>
 * Para ello, necesitaremos proporcionarle un String (codigoActividad) a través de su respectivo Setter,
 * de esta manera el resto de los procesos se ejecutará correctamente.
 * <p>
 * Funciona de la siguiente manera: una vez tiene el código de la actividad accede al método:
 * ParticipacionBD.participaciones(codigoActividad)
 * Este método le devuelve una lista de tipo Participacion con todas las participaciones que se hayan producido en esa actividad.
 *
 * @author Daniel.Tamargo
 * @version 0.9-arabaMoun
 */
public class ModeloParticipaciones extends AbstractTableModel {

    private String[] columnas = {"Nombre", "Apellidos", "Teléfono", "Email"};
    private List<Participacion> participaciones;
    private String codigoActividad;

    public ModeloParticipaciones() {
        participaciones = ParticipacionBD.participaciones(codigoActividad);
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    @Override
    public int getRowCount() {
        return participaciones.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Socio socio = participaciones.get(rowIndex).getSocio();

        switch (columnIndex) {
            case 0:
                return socio.getNombre();
            case 1:
                return socio.getApellidos();
            case 2:
                return socio.getTelefono();
            case 3:
                return socio.getEmail();
            default:
                return socio.getCargo(); //por si acaso se le va la olla
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}

