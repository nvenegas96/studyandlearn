package com.tesis.studyandlearn.controller.admin;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.*;
import com.tesis.studyandlearn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/lessons")
public class LessonAdminController {


    @Autowired
    private LessonService lessonService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModalityService modalityService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @GetMapping({"/", ""})
    public String manageLessons(Model model) {
        model.addAttribute("lessonDTOS", lessonService.showAllLessonDTO());
        model.addAttribute("lessonTeacherDTOS", teacherService.showAllLessonTeacherDTO());
        model.addAttribute("status", statusService.showAllStatus());
        model.addAttribute("categorys", categoryService.showAllCategory());
        model.addAttribute("modalitys", modalityService.showAll());
        return "lessons/manageLessons";
    }


    @RequestMapping(value = "/actions/create")
    public String createLesson(Model model) {
        return template(
                model,
                new LessonDTO(),
                null,
                null
        );
    }


    @GetMapping("/actions/edit/{lessonId}")
    public String editLesson(Model model, @PathVariable("lessonId") int lessonId) {
        return template(
                model,
                lessonService.findByLessonId(lessonId),
                null,
                null
        );
    }

    @PostMapping(path = "")
    public String createOrUpdateLesson(@ModelAttribute LessonDTO lessonDTO, Model model) {
        if (!lessonService.checkCorrectDTO(lessonDTO)) {
            return template(
                    model,
                    lessonDTO,
                    "Error al crear o editar un curso, revisar que todos los campos estén completos",
                    null
            );
        }

        try {
            LessonEntity newLesson = lessonService.createOrUpdateLesson(lessonDTO);
        } catch (Exception e) {
            return template(
                    model,
                    lessonDTO,
                    "Error al intentar crear/editar curso",
                    null
            );
        }

        return "redirect:/admin/lessons";
    }

    private String template(Model model, LessonDTO lessonDTO, String message, String successMsg) {
        model.addAttribute("lessonDTO", lessonDTO);
        model.addAttribute("status", statusService.showAllStatus());
        model.addAttribute("categorys", categoryService.showAllCategory());
        model.addAttribute("modalitys", modalityService.showAll());
        if (message != null)
            model.addAttribute("Error al crear/editar curso", message);
        if (successMsg != null)
            model.addAttribute("Curso creado/editado con éxito", successMsg);
        return "lessons/manageLesson";
    }


    @RequestMapping(value = "/actions/lessonteacher/create")
    public String createLessonTeacher(Model model) {
        return templateLessonTeacher(
                model,
                new LessonTeacherDTO(),
                null
        );
    }


    @GetMapping("/actions/lessonteacher/edit/{lessonTeacherId}")
    public String editLessonTeacher(Model model, @PathVariable("lessonTeacherId") int lessonTeacherId) {
        return templateLessonTeacher(
                model,
                teacherService.findByLessonTeacherId(lessonTeacherId),
                null
        );
    }

    @PostMapping(path = "/lessonteacher")
    public String createOrUpdateLessonTeacher(@ModelAttribute LessonTeacherDTO lessonTeacherDTO, Model model) {
        if (!teacherService.checkCorrectDTO(lessonTeacherDTO)) {
            return templateLessonTeacher(
                    model,
                    lessonTeacherDTO,
                    "Error al crear o editar una asignación, revisar que todos los campos estén completos"
            );
        }

        try {
            LessonTeacherEntity newLessonTeacher = teacherService.createOrUpdateLessonTeacher(lessonTeacherDTO);
        } catch (Exception e) {
            return templateLessonTeacher(
                    model,
                    lessonTeacherDTO,
                    "Error al intentar crear/editar asignación"
            );
        }

        return "redirect:/admin/lessons";
    }

    private String templateLessonTeacher(Model model, LessonTeacherDTO lessonTeacherDTO, String message) {
        model.addAttribute("lessonTeacherDTO", lessonTeacherDTO);
        model.addAttribute("lessons", lessonService.showAllLessons());
        model.addAttribute("users", userService.showAllUser());

        if (message != null)
            model.addAttribute("error", message);
        return "lessons/manageLessonTeacher";
    }


    @RequestMapping(value = "/actions/category/create")
    public String createCategory(Model model) {
        return templateCategory(
                model,
                new LessonCategoryEntity(),
                null
        );
    }


    @GetMapping("/actions/category/edit/{lessonCategoryId}")
    public String editCategory(Model model, @PathVariable("lessonCategoryId") int lessonCategoryId) {
        return templateCategory(
                model,
                categoryService.findCategoryById(lessonCategoryId),
                null
        );
    }

    @PostMapping(path = "/category")
    public String createOrUpdateCategory(@ModelAttribute LessonCategoryEntity lessonCategoryEntity, Model model) {
        if (!categoryService.checkCorrectEntity(lessonCategoryEntity)) {
            return templateCategory(
                    model,
                    lessonCategoryEntity,
                    "Error al crear o editar una categoria, revisar que todos los campos estén completos"
            );
        }

        try {
            LessonCategoryEntity newCategory = categoryService.createOrUpdateCategory(lessonCategoryEntity);
        } catch (Exception e) {
            return templateCategory(
                    model,
                    lessonCategoryEntity,
                    "Error al intentar crear/editar categoria"
            );
        }

        return "redirect:/admin/lessons";
    }

    private String templateCategory(Model model, LessonCategoryEntity lessonCategoryEntity, String message) {
        model.addAttribute("lessonCategoryEntity", lessonCategoryEntity);
        if (message != null)
            model.addAttribute("error", message);
        return "lessons/manageCategory";
    }


    @RequestMapping(value = "/actions/modality/create")
    public String createModality(Model model) {
        return templateModality(
                model,
                new LessonModalityEntity(),
                null
        );
    }


    @GetMapping("/actions/modality/edit/{lessonModalityId}")
    public String editModality(Model model, @PathVariable("lessonModalityId") int lessonModalityId) {
        return templateModality(
                model,
                modalityService.findModalityById(lessonModalityId),
                null
        );
    }

    @PostMapping(path = "/modality")
    public String createOrUpdateModality(@ModelAttribute LessonModalityEntity lessonModalityEntity, Model model) {
        if (!modalityService.checkCorrectEntity(lessonModalityEntity)) {
            return templateModality(
                    model,
                    lessonModalityEntity,
                    "Error al crear o editar una modalidad, revisar que todos los campos estén completos"
            );
        }

        try {
            LessonModalityEntity newModality = modalityService.createOrUpdateModality(lessonModalityEntity);
        } catch (Exception e) {
            return templateModality(
                    model,
                    lessonModalityEntity,
                    "Error al intentar crear/editar modalidad"
            );
        }

        return "redirect:/admin/lessons";
    }

    private String templateModality(Model model, LessonModalityEntity lessonModalityEntity, String message) {
        model.addAttribute("lessonModalityEntity", lessonModalityEntity);
        if (message != null)
            model.addAttribute("error", message);
        return "lessons/manageModality";
    }


    @RequestMapping(value = "/actions/status/create")
    public String createStatus(Model model) {
        return templateStatus(
                model,
                new LessonStatusEntity(),
                null
        );
    }


    @GetMapping("/actions/status/edit/{lessonStatusId}")
    public String editStatus(Model model, @PathVariable("lessonStatusId") int lessonStatusId) {
        return templateStatus(
                model,
                statusService.findStatusById(lessonStatusId),
                null
        );
    }

    @PostMapping(path = "/status")
    public String createOrUpdateStatus(@ModelAttribute LessonStatusEntity lessonStatusEntity, Model model) {
        if (!statusService.checkCorrectEntity(lessonStatusEntity)) {
            return templateStatus(
                    model,
                    lessonStatusEntity,
                    "Error al crear o editar un estado, revisar que todos los campos estén completos"
            );
        }

        try {
            LessonStatusEntity newStatus = statusService.createOrUpdateStatus(lessonStatusEntity);
        } catch (Exception e) {
            return templateStatus(
                    model,
                    lessonStatusEntity,
                    "Error al intentar crear/editar estado"
            );
        }

        return "redirect:/admin/lessons";
    }

    private String templateStatus(Model model, LessonStatusEntity lessonStatusEntity, String message) {
        model.addAttribute("lessonStatusEntity", lessonStatusEntity);
        if (message != null)
            model.addAttribute("error", message);
        return "lessons/manageStatus";
    }


    @PostMapping(path = "/actions/changeLessonStatus")
    public String changeLessonStatus(@ModelAttribute ChangeLessonStatusDTO changeLessonStatusDTO, Model model) {
        String msg = null;
        try {
            lessonService.changeLessonStatus(changeLessonStatusDTO);
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

    @PostMapping(path = "/actions/changeTeacherLessonStatus")
    public String changeLessonTeacherStatus(@ModelAttribute ChangeLessonTeacherStatusDTO changeLessonTeacherStatusDTO, Model model) {
        String msg = null;
        try {
            teacherService.changeLessonTeacherStatus(changeLessonTeacherStatusDTO);
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

    @PostMapping(path = "/actions/changeLessonCategoryStatus")
    public String changeLessonCategoryStatus(@ModelAttribute ChangeLessonCategoryStatusDTO changeLessonCategoryStatusDTO, Model model) {
        String msg = null;
        try {
            categoryService.changeLessonCategoryStatus(changeLessonCategoryStatusDTO);
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


    @PostMapping(path = "/actions/changeLessonModalityStatus")
    public String changeLessonModalityStatus(@ModelAttribute ChangeLessonModalityStatusDTO changeLessonModalityStatusDTO, Model model) {
        String msg = null;
        try {
            modalityService.changeLessonModalityStatus(changeLessonModalityStatusDTO);
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


    @PostMapping(path = "/actions/changeLessonStatusesStatus")
    public String changeLessonStatusesStatus(@ModelAttribute ChangeLessonStatusesStatusDTO changeLessonStatusesStatusDTO, Model model) {
        String msg = null;
        try {
            statusService.changeLessonStatusesStatus(changeLessonStatusesStatusDTO);
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
        return "redirect:/admin/lessons";
    }


}
