package com.tesis.studyandlearn.controller.teacher;

import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.LessonStatusEntity;
import com.tesis.studyandlearn.model.TeacherScheduleEntity;
import com.tesis.studyandlearn.model.UserEntity;
import com.tesis.studyandlearn.model.constans.DayOfTheWeek;
import com.tesis.studyandlearn.model.dto.*;
import com.tesis.studyandlearn.service.ScheduleService;
import com.tesis.studyandlearn.service.TeacherScheduleService;
import com.tesis.studyandlearn.service.TeacherService;
import com.tesis.studyandlearn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/teachers/profile")
public class TeacherProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherScheduleService teacherScheduleService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping({"/", ""})
    public String myProfile(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserDTO userDTO = userService.findDTOByEmailId(user.getUsername());
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("scheduleDTO", teacherScheduleService.showAllDTO(userDTO.getId()));
        model.addAttribute("lessonTeacherDTO", teacherService.showAllLessonTeacherDTO());
        return "teachers/teacherEdit";
    }

    @GetMapping("/actions/updateProfile")
    public String redirectUpdateProfile() {
        return "redirect:/teachers/profile";
    }

    @PostMapping("/actions/updateProfile")
    public String updateProfile(@ModelAttribute UserDTO userDTO, Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        try {
            userService.updateUser(userDTO, user.getUsername());
        } catch (Exception e) {
            log.error("Error when save new user information", e);
            model.addAttribute("error", "Error al guardar información");
            model.addAttribute("userDTO", userDTO);
            return "teachers/teacherEdit";
        }
        model.addAttribute("success", "Información actualizada correctamente");
        model.addAttribute("userDTO", userDTO);
        return "teachers/teacherEdit";
    }


    @RequestMapping(value = "/actions/create")
    public String createTeacherSchedule(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserEntity userEntity = userService.findByEmailId(user.getUsername());
        return template(
                model,
                new TeacherScheduleDTO(),
                null,
                null,
                userEntity.getId()
        );
    }


    @GetMapping("/actions/edit/{scheduleId}")
    public String editTeacherSchedule(Model model, @PathVariable("scheduleId") int scheduleId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserEntity userEntity = userService.findByEmailId(user.getUsername());
        return template(
                model,
                teacherScheduleService.findTeacherDTOById(scheduleId),
                null,
                null,
                userEntity.getId()
        );
    }

    @PostMapping(path = "/createSchedule")
    public String createOrUpdateTeacherSchedule(@ModelAttribute TeacherScheduleDTO teacherScheduleDTO, Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserEntity userEntity = userService.findByEmailId(user.getUsername());
        if (!teacherScheduleService.checkCorrectEntity(teacherScheduleDTO)) {
            return template(
                    model,
                    teacherScheduleDTO,
                    "Error al crear o editar un horario, revisar que todos los campos estén completos",
                    null,
                    userEntity.getId()
            );
        }

        try {
            TeacherScheduleEntity newTeacherSchedule = teacherScheduleService.createOrUpdateTeacherSchedule(teacherScheduleDTO);
        } catch (Exception e) {
            return template(
                    model,
                    teacherScheduleDTO,
                    "Error al intentar crear/editar horario",
                    null,
                    userEntity.getId()
            );
        }

        return "redirect:/teachers/profile";
    }


    private String template(Model model, TeacherScheduleDTO teacherScheduleDTO, String message, String successMsg, Integer userId) {
        model.addAttribute("teacherScheduleEntity", teacherScheduleDTO);
        model.addAttribute("lessonTeacherDTOS", teacherService.showAllByTeacherId(userId));
        if (message != null)
            model.addAttribute("Error al crear/editar curso", message);
        if (successMsg != null)
            model.addAttribute("Curso creado/editado con éxito", successMsg);
        return "teachers/teacherScheduleEdit";
    }

    @PostMapping(path = "/actions/changeTeacherScheduleStatus")
    public String changeTeacherScheduleStatus(@ModelAttribute ChangeTeacherScheduleStatusDTO changeTeacherScheduleStatusDTO, Model model) {
        String msg = null;
        try {
            teacherScheduleService.changeTeacherScheduleStatusDTO(changeTeacherScheduleStatusDTO);
        } catch (Exception e) {
            msg = "Error al cambiar el estado";
            return templateStatus(
                    model,
                    msg,
                    null
            );
        }

        return templateStatus(
                model,
                msg,
                "Estado modificado correctamente"
        );
    }

    private String templateStatus(Model model, String message, String successMsg) {
        if (message != null)
            model.addAttribute("error", message);
        if (successMsg != null)
            model.addAttribute("success", successMsg);
        return "redirect:/teachers/profile";
    }
}

