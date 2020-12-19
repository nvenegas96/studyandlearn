package com.tesis.studyandlearn.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "curso_a_realizar")
public class CursoARealizarEntity implements Serializable {

    private static final long serialVersionUID = 7685595677523662953L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_clase;

    @ManyToOne(fetch = FetchType.LAZY)
    private DocenteEntity docenteEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private CursoEntity cursoEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private AlumnoEntity alumnoEntity;

    @Column(name = "modalidad")
    private String modalidad;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "hora_inicio")
    private Time hora_inicio;

    @Column(name = "cantidad_de_horas")
    private Integer cantidad_de_horas;

    public CursoARealizarEntity(Integer id_clase, String estado, Time hora_inicio, Integer cantidad_de_horas, DocenteEntity docenteEntity, CursoEntity cursoEntity, AlumnoEntity alumnoEntity, String modalidad, Date fecha) {
        this.id_clase = id_clase;
        this.docenteEntity = docenteEntity;
        this.cursoEntity = cursoEntity;
        this.alumnoEntity = alumnoEntity;
        this.modalidad = modalidad;
        this.estado = estado;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.cantidad_de_horas = cantidad_de_horas;
    }

    public Integer getId_clase() {
        return id_clase;
    }

    public void setId_clase(Integer id_clase) {
        this.id_clase = id_clase;
    }

    public DocenteEntity getDocenteEntity() {
        return docenteEntity;
    }

    public void setDocenteEntity(DocenteEntity docenteEntity) {
        this.docenteEntity = docenteEntity;
    }

    public CursoEntity getCursoEntity() {
        return cursoEntity;
    }

    public void setCursoEntity(CursoEntity cursoEntity) {
        this.cursoEntity = cursoEntity;
    }

    public AlumnoEntity getAlumnoEntity() {
        return alumnoEntity;
    }

    public void setAlumnoEntity(AlumnoEntity alumnoEntity) {
        this.alumnoEntity = alumnoEntity;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

        public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date horario) {
        this.fecha = horario;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Integer getCantidad_de_horas() {
        return cantidad_de_horas;
    }

    public void setCantidad_de_horas(Integer cantidad_de_horas) {
        this.cantidad_de_horas = cantidad_de_horas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CursoARealizarEntity() {
    }


}
