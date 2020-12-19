package com.tesis.studyandlearn.controllers;

import com.tesis.studyandlearn.models.CoordinadorEntity;
import com.tesis.studyandlearn.models.DocenteEntity;
import com.tesis.studyandlearn.models.UsuarioEntity;
import com.tesis.studyandlearn.services.CoordinadorService;
import com.tesis.studyandlearn.services.CursoService;
import com.tesis.studyandlearn.services.DocenteService;
import com.tesis.studyandlearn.services.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private CoordinadorService coordinadorService;

    @Autowired
    private UsuarioService usuarioService;

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
    }


    @GetMapping("/contactanos")
    public String contactanos(){


        return "contactanos";
    }

    @GetMapping("/index")
    public String index(){


        return "index";
    }

    @GetMapping("/enviar")
    public String enviar(){


        return "enviar";
    }

    @GetMapping("/nosotros")
    public String nosotros(){


        return "nosotros";
    }

    @GetMapping("/postular")
    public String areaPostular(Model model){
        model.addAttribute("listaCurso",cursoService.findALL() );
        return "postular";
    }


}
