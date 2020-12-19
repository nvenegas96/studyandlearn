package com.tesis.studyandlearn.controllers;


import com.tesis.studyandlearn.models.AlumnoEntity;
import com.tesis.studyandlearn.services.AlumnoService;
import com.tesis.studyandlearn.services.impl.AlumnoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;


}
