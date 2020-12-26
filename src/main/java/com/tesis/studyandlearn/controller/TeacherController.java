package com.tesis.studyandlearn.controller;

import com.tesis.studyandlearn.service.ScheduleService;
import com.tesis.studyandlearn.service.TeacherScheduleService;
import com.tesis.studyandlearn.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TeacherScheduleService teacherScheduleService;

    @GetMapping("/mylessons")
    public String showMyLessons(Model model) {
       // model.addAttribute("LessonScheduleDTO", scheduleService.());
        return "myLessons";
    }

    @GetMapping("/myschedule")
    public String showMySchedule( Model model) {
        model.addAttribute("myschedule", teacherScheduleService.showAll());
        return "teacherSchedule";
    }

}
