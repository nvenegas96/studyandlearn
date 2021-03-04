package com.tesis.studyandlearn.controller.user;

import com.tesis.studyandlearn.model.dto.ChangeLessonScheduleAssessmentDTO;
import com.tesis.studyandlearn.model.dto.ChangeScheduleStatusDTO;
import com.tesis.studyandlearn.model.dto.LessonScheduleDTO;
import com.tesis.studyandlearn.model.dto.UserDTO;
import com.tesis.studyandlearn.service.ScheduleService;
import com.tesis.studyandlearn.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users/lessons")
public class UserLessonController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StatusService statusService;



    @GetMapping({"/", ""})
    public String userSchedules(Model model, Authentication authentication) {
        return template(authentication, model, null, null);
    }

    @PostMapping(path = "/actions/changeStatus")
    public String changeStatus(@ModelAttribute ChangeScheduleStatusDTO changeScheduleStatusDTO, Model model, Authentication authentication) {
        try {
            scheduleService.changeScheduleStatus(changeScheduleStatusDTO);
        } catch (Exception e) {
            return template(
                    authentication,
                    model,
                    "Error al cambiar el estado de la reserva",
                    null

            );
        }

        return template(
                authentication,
                model,
                null,
                "Estado de reserva modificado correctamente"

        );
    }

    @PostMapping(path = "/actions/assessment")
    public String changeAssessment(@ModelAttribute ChangeLessonScheduleAssessmentDTO changeLessonScheduleAssessmentDTO, Model model, Authentication authentication) {
        try {
            scheduleService.changeAssessment(changeLessonScheduleAssessmentDTO);
        } catch (Exception e) {
            return template(
                    authentication,
                    model,
                    "Error al modificar la valoración",
                    null

            );
        }

        return template(
                authentication,
                model,
                null,
                "Valoración modificada correctamente"

        );
    }

    private String template(Authentication authentication, Model model, String message, String successMsg) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("lessonScheduleDTOS", scheduleService.showAllLessonScheduleDTOByStudentEmail(user.getUsername()));
        model.addAttribute("lessonScheduleStatus", statusService.showAllStatus());
        if (message != null)
            model.addAttribute("error", message);
        if (successMsg != null)
            model.addAttribute("success", successMsg);
        return "users/userLesson";
    }


}


