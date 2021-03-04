package com.tesis.studyandlearn.controller.user;

import com.tesis.studyandlearn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/postulation")
public class UserPostulationController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String formPostulation (Model model){
        model.addAttribute("category", categoryService.showAllCategory());
        return "users/postulation";
    }

}
