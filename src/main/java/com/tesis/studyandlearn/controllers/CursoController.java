package com.tesis.studyandlearn.controllers;

import com.tesis.studyandlearn.models.CursoEntity;
import com.tesis.studyandlearn.services.CoordinadorService;
import com.tesis.studyandlearn.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CoordinadorService coordinadorService;

    @GetMapping("")
    public String curso(Model model){

        return "curso";
    }

    @GetMapping("/detalle")
    public String detalleCurso(Model model){
        model.addAttribute("detalleCurso",cursoService.findALL() );
        return "detalleCurso";
    }

    @GetMapping("/mostrarCurso/{id}")
    public String mostrarCurso(@PathVariable(value = "id") int id, CursoEntity cursoEntity, Model model){

        if(id>0){
            cursoEntity = cursoService.findByID(id);
        }else{
            return "redirect:/curso/detalleCurso";
        }
        model.addAttribute("cursoEntity", cursoEntity);

        return "mostrarCurso";
    }


}
