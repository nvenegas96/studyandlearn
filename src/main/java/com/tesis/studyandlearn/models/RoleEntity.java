package com.tesis.studyandlearn.models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="authorities", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_usuario", "autoridad"})})
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 7685595677523662953L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String autoridad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

}
