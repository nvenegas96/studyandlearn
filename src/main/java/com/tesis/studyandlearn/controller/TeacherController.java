package com.tesis.studyandlearn.controller;

import com.tesis.studyandlearn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private UserSpecialtyService userSpecialtyService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String showTeacher( Model model) {
        model.addAttribute("users", userSpecialtyService.showAllUserSpecialtyDTO());
        return "teachers/teachers";
    }

}
