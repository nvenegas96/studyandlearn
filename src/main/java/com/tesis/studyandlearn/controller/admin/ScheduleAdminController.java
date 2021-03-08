package com.tesis.studyandlearn.controller.admin;

import com.tesis.studyandlearn.model.dto.ChangeScheduleStatusDTO;
import com.tesis.studyandlearn.service.ScheduleService;
import com.tesis.studyandlearn.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/schedules")
public class ScheduleAdminController {

    @Autowired
    private StatusService statusService;

    @Autowired
    private ScheduleService scheduleService;


    @GetMapping({"/", ""})
    public String manageSchedules(Model model) {
        return template(model, null, null);
    }

    @PostMapping(path = "/actions/changeStatus")
    public String changeStatus(@ModelAttribute ChangeScheduleStatusDTO changeScheduleStatusDTO, Model model) {
        try {
            scheduleService.changeScheduleStatus(changeScheduleStatusDTO);
        } catch (Exception e) {
            return template(
                    model,
                    "Error al cambiar el estado de la reserva",
                    null
            );
        }

        return template(
                model,
                null,
                "Estado de reserva modificado correctamente"
        );
    }

    private String template(Model model, String message, String successMsg) {
        model.addAttribute("lessonScheduleDTOS", scheduleService.showAllLessonScheduleDTOByTeacherEmail());
        model.addAttribute("lessonScheduleStatus", statusService.showAllStatus());
        if (message != null)
            model.addAttribute("error", message);
        if (successMsg != null)
            model.addAttribute("success", successMsg);
        return "schedules/manageSchedules";
    }


}
