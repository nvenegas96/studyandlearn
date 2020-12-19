package com.tesis.studyandlearn.services;

import com.tesis.studyandlearn.models.AlumnoEntity;
import com.tesis.studyandlearn.models.CoordinadorEntity;
import com.tesis.studyandlearn.models.CursoEntity;
import com.tesis.studyandlearn.models.RoleEntity;
import com.tesis.studyandlearn.repository.CoordinadorRepository;
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
public interface CoordinadorService {

    public CoordinadorEntity findByID(int id_coordinador);
    public void deletedByID(int id_coordinador);
    public void save(CoordinadorEntity coordinadorEntity);
    public List<CoordinadorEntity> findALL();

}