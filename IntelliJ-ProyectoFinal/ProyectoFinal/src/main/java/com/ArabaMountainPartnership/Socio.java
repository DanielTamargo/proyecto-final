package com.ArabaMountainPartnership;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Socio {

    private String codigo;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNac;
    private String dni; //o
    private int telefono;
    private String email;
    private TipoPerfil perfil = TipoPerfil.USUARIO;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja; //o
    private boolean haPagado = false;
    private Cuota cuota;
    private Socio socioResponsable; //o
    private Cargo cargo; //o
    private List<Actividad> actividadesOrganizadas = new ArrayList<>();
    private List<Actividad> actividades = new ArrayList<>();

    public Socio() {
        this.codigo = "error";
        this.nombre = "fallo al cargar";
        this.apellidos = "error";
    }

    //constructor para registrar usuario (siendo mayor)
    public Socio(String codigo, String nombre, String apellidos, LocalDate fechaNac, String dni, int telefono, String email, LocalDate fechaAlta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.cuota = CuotaBD.cuotas().get(CuotaBD.cuotas().size() - 1);
    }

    //constructor para registrar usuario (siendo menor)
    public Socio(String codigo, String nombre, String apellidos, LocalDate fechaNac, String dni, int telefono, String email, LocalDate fechaAlta, Socio socio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.cuota = CuotaBD.cuotas().get(CuotaBD.cuotas().size() - 1);
        this.socioResponsable = socio;
    }

    //constructor desde SocioBD
    public Socio(String codigo, String nombre, String apellidos, Date fechaNac/*String fechaNac*/, String dni, int telefono,
                 String email, String perfil, Date fechaAlta, Date fechaBaja, String haPagado/*, Cuota cuota,
                 Socio socioResponsable, Cargo cargo, List<Actividad> actividadesOrganizadas, List<Actividad> actividades*/) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        String fechaNacStr = String.valueOf(fechaNac);
        this.fechaNac = LocalDate.parse(fechaNacStr);
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        if (perfil.equalsIgnoreCase("ADMINISTRADOR")) {
            this.perfil = TipoPerfil.ADMINISTRADOR;
        }
        String fechaAltaStr = String.valueOf(fechaAlta);
        this.fechaAlta = LocalDate.parse(fechaAltaStr);
        String fechaBajaStr = String.valueOf(fechaBaja);
        if (fechaBaja == null) {
            this.fechaBaja = null;
        } else {
            this.fechaBaja = LocalDate.parse(fechaBajaStr);
        }
        if (haPagado.equalsIgnoreCase("SI")) {
            this.haPagado = true;
        }
        //System.out.println(this.haPagado);
        //System.out.println(this.nombre);
        //System.out.println(this.codigo);
        //System.out.println(this.telefono);

        /*
        this.cuota = cuota;
        this.socioResponsable = socioResponsable;
        this.cargo = cargo;
        this.actividadesOrganizadas = actividadesOrganizadas;
        this.actividades = actividades;*/
    }

    //constructor con los obligatorios
    public Socio(String codigo, String nombre, String apellidos, LocalDate fechaNac, int telefono, String email, LocalDate fechaAlta, Cuota cuota) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.cuota = cuota;
    }

    public void addActividades(Actividad actividad) {
        actividades.add(actividad);
    }

    public void addActividadesOrganizadas(Actividad actividad) {
        actividadesOrganizadas.add(actividad);
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public List<Actividad> getActividadesOrganizadas() {
        return actividadesOrganizadas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(TipoPerfil perfil) {
        this.perfil = perfil;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public boolean isHaPagado() {
        return haPagado;
    }

    public void setHaPagado(boolean haPagado) {
        this.haPagado = haPagado;
    }

    public Cuota getCuota() {
        return cuota;
    }

    public void setCuota(Cuota cuota) {
        this.cuota = cuota;
    }

    public Socio getSocioResponsable() {
        return socioResponsable;
    }

    public void setSocioResponsable(Socio socioResponsable) {
        this.socioResponsable = socioResponsable;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    //toString para mostrar en el JList
    @Override
    public String toString() {
        return apellidos + ", " + nombre;
    }
}
