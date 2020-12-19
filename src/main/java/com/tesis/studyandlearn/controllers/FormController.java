/*package com.tesis.studyandlearn.controllers;

import com.tesis.studyandlearn.models.CursoARealizarEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form (Model model){
        return "form";
    }


    @PostMapping("/form")
    public String procesar(Model model, @RequestParam String nombre, @RequestParam String apellido,
                           @RequestParam String email, @RequestParam Integer telefono, @RequestParam String area,
                           @RequestParam String clase, @RequestParam String modalidad, @RequestParam DateTimeFormatter horario){
        CursoARealizarEntity cursoARealizarEntity = new CursoARealizarEntity();
      cursoARealizarEntity.setNombre("nombre");
      cursoARealizarEntity.setModalidad("modalidad");
      model.addAttribute("nombre", nombre);
      model.addAttribute("apellido", apellido);
      model.addAttribute("email", email);
      model.addAttribute("telefono", telefono);
      model.addAttribute("area", area);
      model.addAttribute("clase", clase);
      model.addAttribute("modalidad", modalidad);
      model.addAttribute("horario", horario);

        return "resultado";

    }

}
*/