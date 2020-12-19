package com.tesis.studyandlearn.controllers;

import com.tesis.studyandlearn.models.CursoARealizarEntity;
import com.tesis.studyandlearn.models.DocenteEntity;
import com.tesis.studyandlearn.services.AlumnoService;
import com.tesis.studyandlearn.services.CursoARealizarService;
import com.tesis.studyandlearn.services.CursoService;
import com.tesis.studyandlearn.services.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private CursoARealizarService cursoARealizarService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/profesionales")
    public String listaDocente(Model model){
        model.addAttribute("listaDocente",docenteService.findALL() );
        return "profesionales";
    }

    @GetMapping("/seccionDocente")
    public String docente(Model model){
        model.addAttribute("listaSolicitudes",cursoARealizarService.findALL() );
        model.addAttribute("listaCursos",cursoService.findALL() );
        model.addAttribute("listaDocentes",docenteService.findALL() );
        model.addAttribute("listaAlumnos",alumnoService.findALL() );
        return "seccionDocente";
    }

    @GetMapping("/cambiarEstado/{id}")
    public String cambiarEstado(@PathVariable(value = "id") int id, CursoARealizarEntity cursoARealizarEntity, Model model){

        if(id>0){
            cursoARealizarEntity = cursoARealizarService.findByID(id);
            DocenteEntity docenteEntity = docenteService.findByID(cursoARealizarEntity.getDocenteEntity().getId_docente());

            cursoARealizarService.actualizarEstado("Realizada", cursoARealizarEntity.getId_clase());
            docenteService.actualizarRegistro_Clases(docenteEntity.getRegistro_clases() + 1, docenteEntity.getId_docente());

        }else{
            return "redirect:/seccionDocente";
        }

        return "redirect:/seccionDocente";
    }


}
