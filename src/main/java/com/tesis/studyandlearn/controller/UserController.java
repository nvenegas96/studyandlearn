package com.tesis.studyandlearn.controller;

import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.UserEntity;
import com.tesis.studyandlearn.model.dto.LessonDTO;
import com.tesis.studyandlearn.model.dto.UserDTO;
import com.tesis.studyandlearn.service.StudyLevelService;
import com.tesis.studyandlearn.service.UserService;
import com.tesis.studyandlearn.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private StudyLevelService studyLevelService;

    @GetMapping("/teachers")
    public String showTeachers( Model model) {
        model.addAttribute("users", userService.showAllUser());
        return "teachers";
    }

    @GetMapping("/manageUsers")
    public String manageUsers( Model model) {
        model.addAttribute("userDTOS", userService.showAllUserDTO());
        return "manageUsers";
    }

    @RequestMapping(value = "/actions/create")
    public String createUser(Model model) {
        return template(
                model,
                new UserDTO(),
                null
        );
    }


    @GetMapping("/actions/edit/{userId}")
    public String editUser(Model model, @PathVariable("userId") int userId) {
        return template(
                model,
                userService.findByUserId(userId),
                null
        );
    }

    @PostMapping(path = "")
    public String createOrUpdateUser(@ModelAttribute UserDTO userDTO, Model model) {
        if (!userService.checkCorrectDTO(userDTO)) {
            return template(
                    model,
                    userDTO,
                    "Error al crear o editar un usuario, revisar que todos los campos estén completos"
            );
        }

        try {
            UserEntity newUser = userService.createOrUpdateUser(userDTO);
        } catch (Exception e) {
            return template(
                    model,
                    userDTO,
                    "Error al intentar crear/editar usuario"
            );
        }

        return "manageUsers";
    }

    private String template(Model model, UserDTO userDTO, String message) {
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("typeUsers", userTypeService.showAll());
        model.addAttribute("studyLevelUsers", studyLevelService.showAll());
        if (message != null)
            model.addAttribute("error", message);
        return "users/manageUser";
    }

}
