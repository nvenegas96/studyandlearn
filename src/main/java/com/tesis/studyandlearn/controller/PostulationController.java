package com.tesis.studyandlearn.controller;

import com.tesis.studyandlearn.service.CategoryService;
import com.tesis.studyandlearn.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/postulation")
public class PostulationController {


    @Autowired
    private CategoryService categoryService;


    @GetMapping("")
    public String formPostulation (Model model){
        model.addAttribute("category", categoryService.showAllCategory());
        return "postulation";
    }

}
