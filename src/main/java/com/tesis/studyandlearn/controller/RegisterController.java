package com.tesis.studyandlearn.controller;

import com.tesis.studyandlearn.model.dto.UserDTO;
import com.tesis.studyandlearn.service.EmailService;
import com.tesis.studyandlearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping()
    private String register(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping()
    private String registerUser(UserDTO userDTO, Model model) {
        if (!userService.validNewUser(userDTO)){
            model.addAttribute("error", "Error al crear Usuario, por favor intente nuevamente");
            model.addAttribute("userDTO", userDTO);
            return "register";
        }
        try {
            userService.createNewUser(userDTO);
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear Usuario, por favor intente nuevamente");
            model.addAttribute("userDTO", userDTO);
            return "register";
        }
        return "redirect:/login?register=true"; // TRUE redirect

    }
}
