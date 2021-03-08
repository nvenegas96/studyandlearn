package com.tesis.studyandlearn.controller.admin;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.*;
import com.tesis.studyandlearn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private StudyLevelService studyLevelService;

    @Autowired
    private UserSpecialtyService userSpecialtyService;


    @GetMapping({"/", ""})
    public String manageUsers( Model model) {
        model.addAttribute("userDTOS", userService.showAllUserDTO());
        model.addAttribute("userSpecialtyDTOS", userSpecialtyService.showAllUserSpecialtyDTO());
        model.addAttribute("specialtys", specialtyService.showAllSpecialty());
        model.addAttribute("studys", studyLevelService.showAll());
        return "users/manageUsers";
    }

    @RequestMapping(value = "/actions/create")
    public String createUser(Model model) {
        return template(
                model,
                new UserDTO(),
                null
        );
    }


    @GetMapping("/actions/edit/{userId}")
    public String editUser(Model model, @PathVariable("userId") int userId) {
        return template(
                model,
                userService.findByUserId(userId),
                null
        );
    }

    @PostMapping(path = "")
    public String createOrUpdateUser(@ModelAttribute UserDTO userDTO, Model model) {
        if (!userService.checkCorrectDTO(userDTO)) {
            return template(
                    model,
                    userDTO,
                    "Error al crear o editar un usuario, revisar que todos los campos estén completos"
            );
        }

        try {
            UserEntity newUser = userService.createOrUpdateUser(userDTO);
        } catch (Exception e) {
            return template(
                    model,
                    userDTO,
                    "Error al intentar crear/editar usuario"
            );
        }

       return "redirect:/admin/users";
    }

    private String template(Model model, UserDTO userDTO, String message) {
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("typeUsers", userTypeService.showAll());
        model.addAttribute("studyLevelUsers", studyLevelService.showAll());
        if (message != null)
            model.addAttribute("error", message);
        return "users/manageUser";
    }



    @RequestMapping(value = "/actions/specialty/create")
    public String createSpecialty(Model model) {
        return templateSpecialty(
                model,
                new SpecialtyEntity(),
                null
        );
    }


    @GetMapping("/actions/specialty/edit/{specialtyId}")
    public String editSpecialty(Model model, @PathVariable("specialtyId") int specialtyId) {
        return templateSpecialty(
                model,
                specialtyService.findSpecialtyById(specialtyId),
                null
        );
    }

    @PostMapping(path = "/specialty")
    public String createOrUpdateSpecialty(@ModelAttribute SpecialtyEntity specialtyEntity, Model model) {
        if (!specialtyService.checkCorrectEntity(specialtyEntity)) {
            return templateSpecialty(
                    model,
                    specialtyEntity,
                    "Error al crear o editar una especialidad, revisar que todos los campos estén completos"
            );
        }

        try {
            SpecialtyEntity newSpecialty = specialtyService.createOrUpdateSpecialty(specialtyEntity);
        } catch (Exception e) {
            return templateSpecialty(
                    model,
                    specialtyEntity,
                    "Error al intentar crear/editar especialidad"
            );
        }

        return "redirect:/admin/users";
    }

    private String templateSpecialty(Model model, SpecialtyEntity specialtyEntity, String message){
        model.addAttribute("specialtyEntity", specialtyEntity);
        if (message != null)
            model.addAttribute("error", message);
        return "users/manageSpecialty";
    }



    @RequestMapping(value = "/actions/study/create")
    public String createStudy(Model model) {
        return templateStudy(
                model,
                new StudyEntity(),
                null
        );
    }


    @GetMapping("/actions/study/edit/{studyId}")
    public String editStudy(Model model, @PathVariable("studyId") int studyId) {
        return templateStudy(
                model,
                studyLevelService.findStudyById(studyId),
                null
        );
    }

    @PostMapping(path = "/study")
    public String createOrUpdateStudy(@ModelAttribute StudyEntity studyEntity, Model model) {
        if (!studyLevelService.checkCorrectEntity(studyEntity)) {
            return templateStudy(
                    model,
                    studyEntity,
                    "Error al crear o editar un estudio, revisar que todos los campos estén completos"
            );
        }

        try {
            StudyEntity newStudy = studyLevelService.createOrUpdateStudy(studyEntity);
        } catch (Exception e) {
            return templateStudy(
                    model,
                    studyEntity,
                    "Error al intentar crear/editar estudio"
            );
        }

        return "redirect:/admin/users";
    }

    private String templateStudy(Model model, StudyEntity studyEntity, String message){
        model.addAttribute("studyEntity", studyEntity);
        if (message != null)
            model.addAttribute("error", message);
        return "users/manageStudy";
    }


    @RequestMapping(value = "/actions/userspecialty/create")
    public String createUserSpecialty(Model model) {
        return templateUserSpecialty(
                model,
                new UserSpecialtyDTO(),
                null
        );
    }


    @GetMapping("/actions/userspecialty/edit/{userSpecialtyId}")
    public String editUserSpecialty(Model model, @PathVariable("userSpecialtyId") int userSpecialtyId) {
        return templateUserSpecialty(
                model,
                userSpecialtyService.findByUserSpecialtyId(userSpecialtyId),
                null
        );
    }

    @PostMapping(path = "/userspecialty")
    public String createOrUpdateUserSpecialty(@ModelAttribute UserSpecialtyDTO userSpecialtyDTO, Model model) {
        if (!userSpecialtyService.checkCorrectDTO(userSpecialtyDTO)) {
            return templateUserSpecialty(
                    model,
                    userSpecialtyDTO,
                    "Error al crear o editar una especialidad, revisar que todos los campos estén completos"
            );
        }

        try {
            UserSpecialtyEntity newUserSpecialty = userSpecialtyService.createOrUpdateUserSpecialty(userSpecialtyDTO);
        } catch (Exception e) {
            return templateUserSpecialty(
                    model,
                    userSpecialtyDTO,
                    "Error al intentar crear/editar especialidad"
            );
        }

        return "redirect:/admin/users";
    }

    private String templateUserSpecialty(Model model, UserSpecialtyDTO userSpecialtyDTO, String message){
        model.addAttribute("userSpecialtyDTO", userSpecialtyDTO);
        model.addAttribute("users", userService.showAllUser());
        model.addAttribute("specialtys", specialtyService.showAllSpecialty());
        if (message != null)
            model.addAttribute("error", message);
        return "users/manageUserSpecialty";
    }

    @PostMapping(path = "/actions/changeUserStatus")
    public String changeUserStatus(@ModelAttribute ChangeUserStatusDTO changeUserStatusDTO, Model model) {
        String msg = null;
        try {
            userService.changeUserStatus(changeUserStatusDTO);
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

    @PostMapping(path = "/actions/changeUserSpecialtyStatus")
    public String changeUserSpecialtyStatus(@ModelAttribute ChangeUserSpecialtyStatusDTO changeUserSpecialtyStatusDTO, Model model) {
        String msg = null;
        try {
            userSpecialtyService.changeUserSpecialtyStatus(changeUserSpecialtyStatusDTO);
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

    @PostMapping(path = "/actions/changeSpecialtyStatus")
    public String changeSpecialtyStatus(@ModelAttribute ChangeSpecialtyStatusDTO changeSpecialtyStatusDTO, Model model) {
        String msg = null;
        try {
            specialtyService.changeSpecialtyStatus(changeSpecialtyStatusDTO);
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
    @PostMapping(path = "/actions/changeStudyStatus")
    public String changeStudyStatus(@ModelAttribute ChangeStudyStatusDTO changeStudyStatusDTO, Model model) {
        String msg = null;
        try {
            studyLevelService.changeStudyStatus(changeStudyStatusDTO);
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
        return "redirect:/admin/users";
    }



}
