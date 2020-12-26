package com.tesis.studyandlearn.controller;

import com.tesis.studyandlearn.model.LessonScheduleEntity;
import com.tesis.studyandlearn.model.dto.LessonScheduleDTO;
import com.tesis.studyandlearn.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lessonSchedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/manageSchedules")
    public String manageSchedules(Model model) {
        model.addAttribute("schedules", scheduleService.showAllSchedule());
        return "manageSchedules";
    }

    @RequestMapping(value = "/actions/create")
    public String createSchedule(Model model) {
        return template(
                model,
                new LessonScheduleDTO(),
                null
        );
    }


    @GetMapping("/actions/edit/{lessonScheduleId}")
    public String editSchedule(Model model, @PathVariable("lessonScheduleId") int lessonScheduleId) {
        return template(
                model,
                scheduleService.findByScheduleId(lessonScheduleId),
                null
        );
    }

    @PostMapping(path = "")
    public String createOrUpdateSchedule(@ModelAttribute LessonScheduleDTO lessonScheduleDTO, Model model) {
        if (!scheduleService.checkCorrectDTO(lessonScheduleDTO)) {
            return template(
                    model,
                    lessonScheduleDTO,
                    "Error al crear o editar una solicitud de clases, revisar que todos los campos estén completos"
            );
        }

        try {
            LessonScheduleEntity newLessonSchedule = scheduleService.createOrUpdateSchedule(lessonScheduleDTO);
        } catch (Exception e) {
            return template(
                    model,
                    lessonScheduleDTO,
                    "Error al intentar crear/editar una solicitud"
            );
        }

        return "manageSchedules";
    }

    private String template(Model model, LessonScheduleDTO lessonScheduleDTO, String message) {
        model.addAttribute("lessonScheduleDTO", lessonScheduleDTO);
        if (message != null)
            model.addAttribute("error", message);
        return "schedules/manageSchedule";
    }

}
