package com.tesis.studyandlearn.controllers;

import com.tesis.studyandlearn.models.AlumnoEntity;
import com.tesis.studyandlearn.models.CursoARealizarEntity;
import com.tesis.studyandlearn.models.CursoEntity;
import com.tesis.studyandlearn.models.DocenteEntity;
import com.tesis.studyandlearn.services.AlumnoService;
import com.tesis.studyandlearn.services.CursoARealizarService;
import com.tesis.studyandlearn.services.CursoService;
import com.tesis.studyandlearn.services.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("")
public class ReservaController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private CursoARealizarService cursoARealizarService;

    @Autowired
    private DocenteService docenteService;


    @RequestMapping(value = "/reserva")
    public String reserva(Map<String, Object> model){
        CursoARealizarEntity cursoARealizarEntity = new CursoARealizarEntity();
        model.put("cursoARealizarEntity", cursoARealizarEntity);
        model.put("listaCursos", cursoService.findALL());
        model.put("listaDocentes", docenteService.findALL());

        return "reserva";
    }

    @RequestMapping(value = "/guardarReservaAlumno" , method = RequestMethod.POST)
    public String guardarReservaAlumno(CursoARealizarEntity cursoARealizarEntity, @RequestParam("nombre") String nombre,
                                       @RequestParam("apellido") String apellido, @RequestParam("email") String email,
                                       @RequestParam("telefono") Integer telefono, @RequestParam("direccion") String direccion){

        AlumnoEntity alumno = new AlumnoEntity();

        alumno.setNombreAl(nombre);
        alumno.setApellido(apellido);
        alumno.setEmail(email);
        alumno.setTelefono(telefono);
        alumno.setDireccion(direccion);

        alumnoService.save(alumno);

        cursoARealizarEntity.setAlumnoEntity(alumno);

        cursoARealizarService.save(cursoARealizarEntity);

        return "redirect:/reserva";
    }


}

