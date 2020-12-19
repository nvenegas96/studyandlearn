package com.tesis.studyandlearn.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 7685595677523662953L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column(length = 45, unique = true, name = "username")
    private String username;

    @Column(length = 60, name = "password")
    private String password;

    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private List<RoleEntity> role;

    public UsuarioEntity(Integer id_usuario, List<RoleEntity> role, String username, String password, Boolean enabled) {
        this.id_usuario = id_usuario;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }


    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public List<RoleEntity> getRole() {
        return role;
    }

    public void setRole(List<RoleEntity> role) {
        this.role = role;
    }

    public UsuarioEntity() {
    }


}
