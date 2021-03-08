package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.UserEntity;
import com.tesis.studyandlearn.model.UserTypeEntity;
import com.tesis.studyandlearn.repository.UserRepository;
import com.tesis.studyandlearn.repository.UserTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoginServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            String errorMsg = "User " + email + " not found in database!.";
            log.error(errorMsg);
            throw new UsernameNotFoundException(errorMsg);
        }

        UserTypeEntity userTypeEntity = userTypeRepository.findById(userEntity.getUserTypeId().intValue());

        if (userTypeEntity == null) {
            String errorMsg = "Role (userType) " + userEntity.getUserTypeId() + " not found in database!.";
            log.error(errorMsg);
            throw new UsernameNotFoundException(errorMsg);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(
                new SimpleGrantedAuthority(userTypeEntity.getTypeName())
        );

        log.info("User " + email + " logged successfully");

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                true,
                true,
                true,
                grantedAuthorities
        );
    }

}
