package com.tesis.studyandlearn.controller;

import com.tesis.studyandlearn.model.UserEntity;
import com.tesis.studyandlearn.model.dto.ReserveDTO;
import com.tesis.studyandlearn.service.ScheduleService;
import com.tesis.studyandlearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("")
    public String createSchedule(@ModelAttribute ReserveDTO reserveDTO, Authentication authentication) {
        if (authentication == null)
            return redirectUrl(0, reserveDTO.getLessonId());
        User user = (User) authentication.getPrincipal();
        if (user == null)
            return redirectUrl(1, reserveDTO.getLessonId());
        boolean emptyHours = true;
        for(String hour : reserveDTO.getHours()){
            if(!hour.isEmpty())
                emptyHours = false;
        }
        if(emptyHours)
            return redirectUrl(3, reserveDTO.getLessonId());
        UserEntity userEntity = userService.findByEmailId(user.getUsername());
        boolean response = scheduleService.createReserve(
                reserveDTO.getLessonId(),
                reserveDTO.getTeacherId(),
                reserveDTO.getHours(),
                userEntity.getId()
        );
        return response ?
                redirectUrl(null, reserveDTO.getLessonId())
                : redirectUrl(2, reserveDTO.getLessonId())
                ;
    }

    private String redirectUrl(Integer error, int lessonId) {
        if (error == null)
            return "redirect:/lessons/" + lessonId + "?success=true";
        return "redirect:/lessons/" + lessonId + "?error=" + error;
    }


}
