package com.tesis.studyandlearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("")
public class HomeController {
/*
    @GetMapping("")
    public String indexl(Model model, Authentication authentication, HttpServletRequest request) {

        if (authentication != null) {
            String username = authentication.getName();

            UsuarioEntity usuarioEntity = null;

            List<UsuarioEntity> usuarioEntities = usuarioService.findALL();
            for (int i = 0; i < usuarioEntities.size(); i++) {
                if (usuarioEntities.get(i).getUsername().equals(username)) ;
                usuarioEntity = usuarioEntities.get(i);
                break;
            }
            SecurityContextHolderAwareRequestWrapper securityContextHolderAwareRequestWrapper = new SecurityContextHolderAwareRequestWrapper(request, "");


            if (securityContextHolderAwareRequestWrapper.isUserInRole("ROLE_ADMIN")) {
                CoordinadorEntity coordinadorEntity = coordinadorService.findByID(usuarioEntity.getId_usuario());
                model.addAttribute("coordinadorEntity", coordinadorEntity);

                return "redirect:/administrador";
            } else if (securityContextHolderAwareRequestWrapper.isUserInRole("ROLE_DOCENTE")) {
                DocenteEntity docenteEntity = docenteService.findByID(usuarioEntity.getId_usuario());
                model.addAttribute("docenteEntity", docenteEntity);
                return "redirect:/seccionDocente";
            }
        }
        return "";
    }*/

    @GetMapping("/contact")
    public String contact() {


        return "contact";
    }


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/reservation")
    public String reservation() {
        return "reservation";
    }


    @GetMapping("/about")
    public String about() {


        return "about";
    }

    @GetMapping("/administrator")
    public String administrator() {


        return "administrator";
    }
}