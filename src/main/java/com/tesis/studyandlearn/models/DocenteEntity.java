package com.tesis.studyandlearn.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "docente")
public class DocenteEntity implements Serializable {

    private static final long serialVersionUID = 7685595677523662953L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_docente;

    @OneToMany(mappedBy = "docenteEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CursoARealizarEntity> cursosARealizar;

    @ManyToOne(fetch = FetchType.LAZY)
    private CoordinadorEntity coordinadorEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private CursoEntity cursoEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuarioEntity;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nivelacion")
    private String nivelacion;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name="direccion")
    private String direccion;

    @Column(name = "registro_clases")
    private Integer registro_clases;

    @Column(name = "especialidad")
    private String especialidad;

    @Column(name = "email")
    private String email;

    public DocenteEntity(Integer id_docente, String apellido, UsuarioEntity usuarioEntity, List<CursoARealizarEntity> cursosARealizar, CoordinadorEntity coordinadorEntity, CursoEntity cursoEntity, String nombre, String nivelacion, Integer telefono, String direccion, Integer registro_clases, String especialidad, String email) {
        this.id_docente = id_docente;
        this.cursosARealizar = cursosARealizar;
        this.coordinadorEntity = coordinadorEntity;
        this.cursoEntity = cursoEntity;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nivelacion = nivelacion;
        this.telefono = telefono;
        this.direccion = direccion;
        this.registro_clases = registro_clases;
        this.especialidad = especialidad;
        this.email = email;
        this.usuarioEntity = usuarioEntity;
    }

    public Integer getId_docente() {
        return id_docente;
    }

    public void setId_docente(Integer id_docente) {
        this.id_docente = id_docente;
    }

    public List<CursoARealizarEntity> getCursosARealizar() {
        return cursosARealizar;
    }

    public void setCursosARealizar(List<CursoARealizarEntity> cursosARealizar) {
        this.cursosARealizar = cursosARealizar;
    }

    public CoordinadorEntity getCoordinadorEntity() {
        return coordinadorEntity;
    }

    public void setCoordinadorEntity(CoordinadorEntity coordinadorEntity) {
        this.coordinadorEntity = coordinadorEntity;
    }

    public CursoEntity getCursoEntity() {
        return cursoEntity;
    }

    public void setCursoEntity(CursoEntity cursoEntity) {
        this.cursoEntity = cursoEntity;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivelacion() {
        return nivelacion;
    }

    public void setNivelacion(String nivelacion) {
        this.nivelacion = nivelacion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getRegistro_clases() {
        return registro_clases;
    }

    public void setRegistro_clases(Integer registro_clases) {
        this.registro_clases = registro_clases;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public DocenteEntity() {
    }


}
