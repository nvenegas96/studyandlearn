package com.tesis.studyandlearn.controller;

import com.tesis.studyandlearn.model.UserEntity;
import com.tesis.studyandlearn.model.dto.UserDTO;
import com.tesis.studyandlearn.service.UserService;
import com.tesis.studyandlearn.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String indexl(Model model, Authentication authentication, HttpServletRequest request) {

        if (authentication != null) {
            String username = authentication.getName();

            UserEntity userEntity = null;

            List<UserEntity> usuarioEntities = userService.showAllUser();
            for (int i = 0; i < usuarioEntities.size(); i++) {
                if (usuarioEntities.get(i).getEmail().equals(username)) ;
                userEntity = usuarioEntities.get(i);
                break;
            }
            SecurityContextHolderAwareRequestWrapper securityContextHolderAwareRequestWrapper = new SecurityContextHolderAwareRequestWrapper(request, "");


            if (securityContextHolderAwareRequestWrapper.isUserInRole("ADMIN")) {
                UserDTO userEntity1 = userService.findByUserId(userEntity.getId());
                model.addAttribute("userEntity1", userEntity1);

                return "redirect:/admin/lessons";
            } else if (securityContextHolderAwareRequestWrapper.isUserInRole("TEACHER")) {
                UserDTO userEntity1 = userService.findByUserId(userEntity.getId());
                model.addAttribute("userEntity1", userEntity1);
                return "redirect:/teachers/lessons";
            } else if (securityContextHolderAwareRequestWrapper.isUserInRole("STUDENT")) {
                UserDTO userEntity1 = userService.findByUserId(userEntity.getId());
                model.addAttribute("userEntity1", userEntity1);
                return "redirect:/users/lessons";
            }
        }
        return "";
    }

    @GetMapping("/contact")
    public String contact() {


        return "contact";
    }


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {


        return "about";
    }
}