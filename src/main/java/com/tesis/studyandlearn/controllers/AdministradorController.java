package com.tesis.studyandlearn.controllers;

import com.tesis.studyandlearn.models.*;
import com.tesis.studyandlearn.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private CursoARealizarService cursoARealizarService;

    @Autowired
    private CoordinadorService coordinadorService;

    @Autowired
    private AlumnoService alumnoService;


    @GetMapping("")
    public String administrador(){
        return "administrador";
    }









    //------------------SECCION DE GESTIONAR CURSOS------------------------------------------

    @GetMapping("/gestionarCursos")
    public String listaCursos(Model model){
        model.addAttribute("listaCursos",cursoService.findALL() );
        return "gestionarCursos";
    }

    @GetMapping("/listaCoordinadorCrear")
    public String listaCoordinadorCrear(CursoEntity cursoEntity, Model model){
        model.addAttribute("listaCoordinador",coordinadorService.findALL() );
        return "crearCurso";
    }

    @GetMapping("/listaCoordinadorEditar/{id}")
    public String listaCoordinadorEditar(@PathVariable(value = "id") int id, CursoEntity cursoEntity, Model model){

        if(id>0){
            cursoEntity = cursoService.findByID(id);
        }else{
            return "redirect:/administrador/gestionarCursos";
        }
        model.addAttribute("cursoEntity", cursoEntity);
        model.addAttribute("listaCoordinador", coordinadorService.findALL());

        return "editarCurso";
    }

    @GetMapping("/eliminarCurso/{id}")
    public String eliminarCurso(@PathVariable("id") int id, Model model) throws Exception {

        cursoService.deletedByID(id);

        List<CursoEntity> list = cursoService.findALL();

        model.addAttribute("listaCursos",list);

        return "gestionarCursos";
    }

    @RequestMapping(value = "/crearCurso")
    public String crearCurso(Map<String, Object> model){

        CursoEntity cursoEntity = new CursoEntity();
        model.put("cursoEntity", cursoEntity);
        model.put("listaCoordinador", coordinadorService.findALL());

        return "crearCurso";
    }

    @RequestMapping(value = "/guardarCurso" , method = RequestMethod.POST)
    public String guardarCurso(CursoEntity cursoEntity){

        cursoEntity.setImg("defecto.jpg");
        cursoService.save(cursoEntity);



        return "redirect:/administrador/gestionarCursos";
    }


    @RequestMapping(value = "/editarCurso/{id}")
    public String editarCurso(@PathVariable(value = "id") int id, Map<String, Object> model){

        CursoEntity cursoEntity = null;

        if(id>0){
            cursoEntity = cursoService.findByID(id);
        }else{
            return "redirect:/administrador/gestionarCursos";
        }
        model.put("cursoEntity", cursoEntity);
        model.put("listaCoordinador", coordinadorService.findALL());
        return "editarCurso";
    }

    @RequestMapping(value = "/guardarCursoEditado")
    public String guardarCursoEditado(CursoEntity cursoEntity){

        cursoService.save(cursoEntity);


        return "redirect:/administrador/gestionarCursos";
    }














    //------------------SECCION DE GESTIONAR DOCENTES------------------------------------------












    @GetMapping("/gestionarProfesionales")
    public String listaDocente(Model model){
        model.addAttribute("listaDocente",docenteService.findALL() );
        model.addAttribute("listaCursos",cursoService.findALL() );
        return "gestionarProfesionales";
    }

    @GetMapping("/listaCoordinadorDocenteCrear")
    public String listaCoordinadorDocenteCrear(DocenteEntity docenteEntity, Model model, CursoEntity cursoEntity){
        model.addAttribute("listaCoordinador",coordinadorService.findALL() );
        model.addAttribute(("listaCursos"), cursoService.findALL());
        return "crearProfesional";
    }

    @GetMapping("/listaCoordinadorDocenteEditar/{id}")
    public String listaCoordinadorDocenteEditar(@PathVariable(value = "id") int id, DocenteEntity docenteEntity, Model model){

        if(id>0){
            docenteEntity = docenteService.findByID(id);
        }else{
            return "redirect:/administrador/gestionarProfesionales";
        }
        model.addAttribute("docenteEntity", docenteEntity);
        model.addAttribute("listaCoordinador", coordinadorService.findALL());
        model.addAttribute("listaCursos", cursoService.findALL());

        return "editarProfesional";
    }


    @RequestMapping(value = "/crearProfesional")
    public String crearProfesional(Map<String, Object> model){

        DocenteEntity docenteEntity = new DocenteEntity();
        model.put("docenteEntity", docenteEntity);
        model.put("listaCoordinador", coordinadorService.findALL());
        model.put("listaCursos", cursoService.findALL());

        return "crearProfesional";
    }

    @RequestMapping(value = "/guardarProfesional" , method = RequestMethod.POST)
    public String guardarProfesional(DocenteEntity docenteEntity){

        docenteService.save(docenteEntity);


        return "redirect:/administrador/gestionarProfesionales";
    }

    @RequestMapping(value = "/editarProfesional/{id}")
    public String editarProfesional(@PathVariable(value = "id") int id, Map<String, Object> model){

        DocenteEntity docenteEntity = null;

        if(id>0){
            docenteEntity = docenteService.findByID(id);
        }else{
            return "redirect:/administrador/gestionarProfesionales";
        }
        model.put("docenteEntity", docenteEntity);
        model.put("listaCoordinador", coordinadorService.findALL());
        model.put("listaCursos", cursoService.findALL());
        return "editarProfesional";
    }

    @RequestMapping(value = "/guardarProfesionalEditado")
    public String guardarProfesionalEditado(DocenteEntity docenteEntity){

        docenteService.save(docenteEntity);


        return "redirect:/administrador/gestionarProfesionales";
    }

    @GetMapping("/eliminarProfesionales/{id}")
    public String eliminarProfesionales(@PathVariable("id") int id, Model model) throws Exception {

        docenteService.deletedByID(id);

        List<DocenteEntity> list = docenteService.findALL();

        model.addAttribute("listaDocente",list);

        return "gestionarProfesionales";
    }







    //------------------SECCION DE SOLICITUDES------------------------------------------








    @GetMapping("/visualizarSolicitudes")
    public String listaSolicitudes(Model model){
        model.addAttribute("listaSolicitudes",cursoARealizarService.findALL() );
        model.addAttribute("listaCursos",cursoService.findALL() );
        model.addAttribute("listaDocentes",docenteService.findALL() );
        model.addAttribute("listaAlumnos",alumnoService.findALL() );
        return "visualizarSolicitudes";
    }

    @GetMapping("/formSolicitudCrear")
    public String formSolicitudCrear(DocenteEntity docenteEntity, Model model, CursoARealizarEntity cursoARealizarEntity){
        model.addAttribute("listaDocentes",docenteService.findALL() );
        model.addAttribute(("listaCursos"), cursoService.findALL());
        model.addAttribute(("listaAlumnos"), alumnoService.findALL());
        return "crearSolicitud";
    }

    @GetMapping("/formSolicitudEditar/{id}")
    public String formSolicitudEditar(@PathVariable(value = "id") int id, CursoARealizarEntity cursoARealizarEntity, Model model){

        if(id>0){
            cursoARealizarEntity = cursoARealizarService.findByID(id);
        }else{
            return "redirect:/administrador/gestionarSolicitudes";
        }
        model.addAttribute("cursoARealizarEntity", cursoARealizarEntity);
        model.addAttribute("listaDocentes", docenteService.findALL());
        model.addAttribute("listaCursos", cursoService.findALL());
        model.addAttribute("listaAlumnos", alumnoService.findALL());

        return "editarSolicitud";
    }

    @RequestMapping(value = "/crearSolicitud")
    public String crearSolicitud(Map<String, Object> model){

        CursoARealizarEntity cursoARealizarEntity = new CursoARealizarEntity();
        model.put("cursoARealizarEntity", cursoARealizarEntity);
        model.put("listaDocentes", docenteService.findALL());
        model.put("listaCursos", cursoService.findALL());
        model.put("listaAlumnos", alumnoService.findALL());

        return "crearSolicitud";
    }

    @RequestMapping(value = "/guardarSolicitud" , method = RequestMethod.POST)
    public String guardarSolicitud(CursoARealizarEntity cursoARealizarEntity){

        cursoARealizarService.save(cursoARealizarEntity);


        return "redirect:/administrador/visualizarSolicitudes";
    }

    @RequestMapping(value = "/editarSolicitud/{id}")
    public String editarSolicitud(@PathVariable(value = "id") int id, Map<String, Object> model){

        CursoARealizarEntity cursoARealizarEntity = null;

        if(id>0){
            cursoARealizarEntity = cursoARealizarService.findByID(id);
        }else{
            return "redirect:/administrador/visualizarSolicitudes";
        }
        model.put("cursoARealizarEntity", cursoARealizarEntity);
        model.put("listaDocentes", docenteService.findALL());
        model.put("listaCursos", cursoService.findALL());
        model.put("listaAlumnos", alumnoService.findALL());
        return "editarSolicitud";
    }

    @RequestMapping(value = "/guardarSolicitudEditado")
    public String guardarSolicitudEditado(CursoARealizarEntity cursoARealizarEntity){

        cursoARealizarService.save(cursoARealizarEntity);


        return "redirect:/administrador/visualizarSolicitudes";
    }

    @GetMapping("/eliminarSolicitud/{id}")
    public String eliminarSolicitud(@PathVariable("id") int id, Model model) throws Exception {

        cursoARealizarService.deletedByID(id);

        List<CursoARealizarEntity> list = cursoARealizarService.findALL();

        model.addAttribute("listaSolicitudes",list);

        return "visualizarSolicitudes";
    }
}
