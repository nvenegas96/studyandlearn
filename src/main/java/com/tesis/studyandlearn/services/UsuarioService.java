package com.tesis.studyandlearn.services;

import com.tesis.studyandlearn.models.CoordinadorEntity;
import com.tesis.studyandlearn.models.RoleEntity;
import com.tesis.studyandlearn.models.UsuarioEntity;
import com.tesis.studyandlearn.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Logger logger = LoggerFactory.getLogger(CoordinadorService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findByUsername(username);

        if (usuarioEntity == null){
            logger.error("Error login: no existe el usuario '" + username+" '");
            throw new UsernameNotFoundException("Usuario "+username+" no existe en el sistema");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (RoleEntity roleEntity: usuarioEntity.getRole()){
            logger.info("Role ".concat(roleEntity.getAutoridad()));
            authorities.add(new SimpleGrantedAuthority(roleEntity.getAutoridad()));
        }
        if (authorities.isEmpty()){
            logger.error("Error login: no existe el usuario '" + username+" ' no tiene roles asignado");
            throw new UsernameNotFoundException("Error login: no existe el usuario '" + username+" ' no tiene roles asignado");
        }
        return new User(usuarioEntity.getUsername(), usuarioEntity.getPassword(), usuarioEntity.getEnabled(), true, true ,true, authorities);
    }


    @javax.transaction.Transactional
    public void save(UsuarioEntity usuarioEntity) {
        if (usuarioEntity.getId_usuario() != null && usuarioEntity.getId_usuario() > 0){

        }
        usuarioRepository.save(usuarioEntity);
    }


    public UsuarioEntity findByID(int id_usuario) {
        return usuarioRepository.findById(id_usuario).orElse(null);
    }


    public void deletedByID(int id_usuario) {
        usuarioRepository.deleteById(id_usuario);
    }


    public List<UsuarioEntity> findALL() {
        return (List<UsuarioEntity>) usuarioRepository.findAll();
    }

}
