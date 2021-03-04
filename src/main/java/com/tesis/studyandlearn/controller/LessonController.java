package com.tesis.studyandlearn.controller;

import com.tesis.studyandlearn.model.dto.ReserveDTO;
import com.tesis.studyandlearn.service.CommentService;
import com.tesis.studyandlearn.service.EmailService;
import com.tesis.studyandlearn.service.LessonService;
import com.tesis.studyandlearn.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lessons")
public class LessonController {


    @Autowired
    private LessonService lessonService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/{lessonId}")
    public String showLessons(
            @PathVariable("lessonId") int lessonId,
            @RequestParam(value = "error", required = false) Integer error,
            @RequestParam(value = "success", required = false) Boolean success,
            Model model,
            Authentication authentication
    ) {
        model.addAttribute("success", successTranslation(success));
        model.addAttribute("error", errorTranslation(error));
        model.addAttribute("reserveDTO", new ReserveDTO());
        model.addAttribute("commentDTO", commentService.showAllCommentDTO());;
        model.addAttribute("lessons", lessonService.showLessonById(lessonId));
        model.addAttribute("schedulesAndTeachers", scheduleService.showScheduleByLesson(lessonId));
        return "lessons/showLessons";
    }

    private String successTranslation(Boolean success) {
        if (success == null || !success)
            return null;
        return "Reserva creada exitosamente";
    }

    private String errorTranslation(Integer errorId) {
        if (errorId == null)
            return null;
        switch (errorId) {
            case 0:
                return "Error";
            case 1:
                return "Error";
            case 2:
                return "Debe seleccionar alguna fecha";
            case 3:
                return "Debe asignar una fecha";
        }
        return null;
    }


}
