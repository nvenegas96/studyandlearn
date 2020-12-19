package com.tesis.studyandlearn;

import com.tesis.studyandlearn.services.CoordinadorService;
import com.tesis.studyandlearn.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/css/**","/js/**","/img**,/assets/**").permitAll()
                .antMatchers("/administrador/**").hasAnyRole("ADMIN")
                .antMatchers("/seccionDocente/**").hasAnyRole("DOCENTE")
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout().permitAll();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }

}
