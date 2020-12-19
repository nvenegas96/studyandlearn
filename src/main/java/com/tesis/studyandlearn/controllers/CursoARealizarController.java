package com.tesis.studyandlearn.controllers;

import com.tesis.studyandlearn.models.AlumnoEntity;
import com.tesis.studyandlearn.models.CursoARealizarEntity;
import com.tesis.studyandlearn.services.CursoARealizarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clases-a-realizar")
public class CursoARealizarController {

    @Autowired
    private CursoARealizarService cursoARealizarService;

    @GetMapping("/list")
    public String listaCursoARealizar(Model model){
        model.addAttribute("listaCursoARealizar",cursoARealizarService.findALL() );
        return "inicio";
    }

    @GetMapping("/delete/{id}")
    public String eliminarCursoARealizar(@PathVariable("id") int id, Model model) throws Exception {

        cursoARealizarService.deletedByID(id);

        List<CursoARealizarEntity> list = cursoARealizarService.findALL();

        model.addAttribute("listaCursoARealizar",list);

        return "inicio";
    }
}
