package com.tesis.studyandlearn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping("/index")
    public String index(){


        return "index";
    }

    @GetMapping("/contactanos")
    public String contactanos(){


        return "contactanos";
    }

}
