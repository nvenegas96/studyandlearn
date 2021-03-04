package com.tesis.studyandlearn.controller.teacher;


import com.tesis.studyandlearn.model.dto.ChangeScheduleStatusDTO;
import com.tesis.studyandlearn.service.ScheduleService;
import com.tesis.studyandlearn.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teachers/lessons")
public class LessonTeacherController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StatusService statusService;

    @GetMapping({"/", ""})
    public String teacherSchedules(Model model, Authentication authentication) {
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

    private String template(Authentication authentication, Model model, String message, String successMsg) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("lessonScheduleDTOS", scheduleService.showAllLessonScheduleDTOByTeacherEmail(user.getUsername()));
        model.addAttribute("lessonScheduleStatus", statusService.showAllStatus());
        if (message != null)
            model.addAttribute("error", message);
        if (successMsg != null)
            model.addAttribute("success", successMsg);
        return "teachers/myLesson";
    }


}
