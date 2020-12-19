package com.tesis.studyandlearn.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "alumno")
public class AlumnoEntity implements Serializable {

    private static final long serialVersionUID = 7685595677523662953L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_alumno;

    @OneToMany(mappedBy = "alumnoEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CursoARealizarEntity> cursosARealizar;

    @Column(name = "nombreAl")
    private String nombreAl;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private Integer telefono;


    public Integer getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Integer id_alumno) {
        this.id_alumno = id_alumno;
    }

    public List<CursoARealizarEntity> getCursosARealizar() {
        return cursosARealizar;
    }

    public void setCursosARealizar(List<CursoARealizarEntity> cursosARealizar) {
        this.cursosARealizar = cursosARealizar;
    }

    public String getNombreAl() {
        return nombreAl;
    }

    public void setNombreAl(String nombreAl) {
        this.nombreAl = nombreAl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public AlumnoEntity(Integer id_alumno, String apellido, List<CursoARealizarEntity> cursosARealizar, String nombreAl, String email, String direccion, Integer telefono) {
        this.id_alumno = id_alumno;
        this.cursosARealizar = cursosARealizar;
        this.nombreAl = nombreAl;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
    }


    public AlumnoEntity() {

    }


}
