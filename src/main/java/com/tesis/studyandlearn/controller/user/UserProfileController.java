package com.tesis.studyandlearn.controller.user;

import com.tesis.studyandlearn.model.UserEntity;
import com.tesis.studyandlearn.model.dto.UserDTO;
import com.tesis.studyandlearn.service.ScheduleService;
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
@RequestMapping("/users/profile")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping({"/", ""})
    public String myProfile(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserDTO userDTO = userService.findDTOByEmailId(user.getUsername());
        model.addAttribute("scheduleDTO", scheduleService.showAllSchedule());
        model.addAttribute("userDTO", userDTO);
        return "users/userEdit";
    }

    @GetMapping("/actions/updateProfile")
    public String redirectUpdateProfile() {
        return "redirect:/users/profile";
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
            return "users/userEdit";
        }
        model.addAttribute("success", "Información actualizada correctamente");
        model.addAttribute("userDTO", userDTO);
        return "users/userEdit";
    }

}
