package com.tesis.studyandlearn.controller;

import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.dto.LessonDTO;
import com.tesis.studyandlearn.model.dto.ScheduleDTO;
import com.tesis.studyandlearn.model.dto.TeacherSchedule;
import com.tesis.studyandlearn.model.dto.UserDTO;
import com.tesis.studyandlearn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lessons")
public class LessonController {


    @Autowired
    private LessonService lessonService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModalityService modalityService;

    @Autowired
    private TeacherScheduleService teacherScheduleService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{lessonId}")
    public String showLessons(@PathVariable("lessonId") int lessonId, Model model) {
        model.addAttribute("lessons", lessonService.showLessonById(lessonId));
        model.addAttribute("schedulesAndTeachers", scheduleService.showScheduleByLesson(lessonId));
        return "showLessons";
    }


    @GetMapping("/manageLessons")
    public String manageLessons(Model model) {
        model.addAttribute("lessonDTOS", lessonService.showAllLessonDTO());
        return "manageLessons";
    }


    @RequestMapping(value = "/actions/create")
    public String createLesson(Model model) {
        return template(
                model,
                new LessonDTO(),
                null
        );
    }


    @GetMapping("/actions/edit/{lessonId}")
    public String editLesson(Model model, @PathVariable("lessonId") int lessonId) {
        return template(
                model,
                lessonService.findByLessonId(lessonId),
                null
        );
    }

    @PostMapping(path = "")
    public String createOrUpdateLesson(@ModelAttribute LessonDTO lessonDTO, Model model) {
        if (!lessonService.checkCorrectDTO(lessonDTO)) {
            return template(
                    model,
                    lessonDTO,
                    "Error al crear o editar un curso, revisar que todos los campos estén completos"
            );
        }

        try {
            LessonEntity newLesson = lessonService.createOrUpdateLesson(lessonDTO);
        } catch (Exception e) {
            return template(
                    model,
                    lessonDTO,
                    "Error al intentar crear/editar curso"
            );
        }

        return "manageLessons";
    }

    private String template(Model model, LessonDTO lessonDTO, String message) {
        model.addAttribute("lessonDTO", lessonDTO);
        model.addAttribute("categorys", categoryService.showAllCategory());
        model.addAttribute("modalitys", modalityService.showAll());
        if (message != null)
            model.addAttribute("error", message);
        return "lessons/manageLesson";
    }


}
