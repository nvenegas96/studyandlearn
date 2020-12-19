package com.tesis.studyandlearn.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "coordinador")
public class CoordinadorEntity implements Serializable {

    private static final long serialVersionUID = 7685595677523662953L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_coordinador;

    @OneToMany(mappedBy = "coordinadorEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CursoEntity> cursos;

    @OneToMany(mappedBy = "coordinadorEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DocenteEntity> docentes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuarioEntity;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private Integer telefono;


    public CoordinadorEntity(Integer id_coordinador,UsuarioEntity usuarioEntity, List<CursoEntity> cursos, List<DocenteEntity> docentes, String nombre, String email, String direccion, Integer telefono) {
        this.id_coordinador = id_coordinador;
        this.cursos = cursos;
        this.docentes = docentes;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.usuarioEntity = usuarioEntity;
    }

    public Integer getId_coordinador() {
        return id_coordinador;
    }

    public void setId_coordinador(Integer id_coordinador) {
        this.id_coordinador = id_coordinador;
    }

    public List<CursoEntity> getCursos() {
        return cursos;
    }

    public void setCursos(List<CursoEntity> cursos) {
        this.cursos = cursos;
    }

    public List<DocenteEntity> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<DocenteEntity> docentes) {
        this.docentes = docentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }



    public CoordinadorEntity() {
    }

}
