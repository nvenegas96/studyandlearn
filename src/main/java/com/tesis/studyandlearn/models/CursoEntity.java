package com.tesis.studyandlearn.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "curso")
public class CursoEntity implements Serializable {

    private static final long serialVersionUID = 7685595677523662953L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_curso;

    @OneToMany(mappedBy = "cursoEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CursoARealizarEntity> cursosARealizar;

    @OneToMany(mappedBy = "cursoEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DocenteEntity> docentes;

    @ManyToOne(fetch = FetchType.LAZY)
    private CoordinadorEntity coordinadorEntity;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "valoracion")
    private Double valoracion;

    @Column(name = "img")
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public List<CursoARealizarEntity> getCursosARealizar() {
        return cursosARealizar;
    }

    public void setCursosARealizar(List<CursoARealizarEntity> cursosARealizar) {
        this.cursosARealizar = cursosARealizar;
    }

    public List<DocenteEntity> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<DocenteEntity> docentes) {
        this.docentes = docentes;
    }

    public CoordinadorEntity getCoordinadorEntity() {
        return coordinadorEntity;
    }

    public void setCoordinadorEntity(CoordinadorEntity coordinadorEntity) {
        this.coordinadorEntity = coordinadorEntity;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValoracion() {
        return valoracion;
    }

    public void setValoracion(Double valoracion) {
        this.valoracion = valoracion;
    }

    public CursoEntity(Integer id_curso, String img, List<CursoARealizarEntity> cursosARealizar, List<DocenteEntity> docentes, CoordinadorEntity coordinadorEntity, String nombre, String categoria, String descripcion, Double valoracion) {
        this.id_curso = id_curso;
        this.cursosARealizar = cursosARealizar;
        this.docentes = docentes;
        this.coordinadorEntity = coordinadorEntity;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.valoracion = valoracion;

    }

    public CursoEntity() {
    }

    @Override
    public String toString() {
        return
                "" + nombre + " "+
                "\n" + categoria + " "+
                "\n" + descripcion + " "+
                "valoracion: " + valoracion;
    }
}
