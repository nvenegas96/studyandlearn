package com.tesis.studyandlearn.config;

import com.tesis.studyandlearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private UserService userDetailsService;

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/img**,/assets/**").permitAll()
                //.antMatchers("/administrador/**").hasAnyRole("ADMIN")
                //.antMatchers("/seccionDocente/**").hasAnyRole("DOCENTE")
                .and()
                .formLogin().loginPage("/login")
                .and()
                .cors()
                .and()
                .csrf()
                .disable()
                .logout().permitAll();
    }

//    @Autowired
//    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
//        build.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder);
//
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}