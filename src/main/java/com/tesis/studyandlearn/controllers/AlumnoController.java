package com.tesis.studyandlearn.controllers;


import com.tesis.studyandlearn.models.AlumnoEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AlumnoController {

    @RequestMapping("/perfil")
    public String perfil(Model model){
        AlumnoEntity alumno = new AlumnoEntity();
        alumno.setNombre("jaime");
        alumno.setEmail("asdasd@asda");
        model.addAttribute("alumno", alumno);
        model.addAttribute("titulo","Perfil del alumno".concat(alumno.getNombre()));
        return "perfil";
    }

    @RequestMapping("/listado")
    public String listado(Model model){
        List<AlumnoEntity> alumnos = new ArrayList<>();
        model.addAttribute("titulo", "Listado de alumnos");
        model.addAttribute("alumnos", alumnos);

        return "listado";
    }
}
