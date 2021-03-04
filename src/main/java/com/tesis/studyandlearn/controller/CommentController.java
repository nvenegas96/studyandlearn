package com.tesis.studyandlearn.controller;


import com.tesis.studyandlearn.model.LessonCommentEntity;
import com.tesis.studyandlearn.service.CommentService;
import com.tesis.studyandlearn.service.UserService;
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
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping({"/", ""})
    public String manageLessons(Model model, Authentication authentication) {
        model.addAttribute("commentDTO", commentService.showAllCommentDTO());

        return "lessons/showLessons";
    }


    @RequestMapping(value = "/actions/create")
    public String createLessonComment(Model model) {
        return template(
                model,
                new LessonCommentEntity(),
                null,
                null
        );
    }


    @PostMapping(path = "")
    public String createOrUpdateLessonComment(@ModelAttribute LessonCommentEntity lessonCommentEntity, Model model, Authentication authentication, int lessonId) {
        if (authentication == null)
            return  "redirect:/login";
        User user = (User) authentication.getPrincipal();
        if (user == null)
            return  "redirect:/login";
        if (!commentService.checkCorrectDTO(lessonCommentEntity)) {
            return template(
                    model,
                    lessonCommentEntity,
                    "Error al crear o editar un comentario, revisar que todos los campos estén completos",
                    null
            );
        }

        try {
            LessonCommentEntity newLessonComment = commentService.createOrUpdateComment(lessonCommentEntity, authentication.getName());
        } catch (Exception e) {
            return template(
                    model,
                    lessonCommentEntity,
                    "Error al intentar crear/editar comentario",
                    null
            );
        }

        return "redirect:/lessons/" + lessonId;
    }

    private String template(Model model, LessonCommentEntity lessonCommentEntity, String message, String successMsg) {
        model.addAttribute("lessonCommentEntity", lessonCommentEntity);
        if (message != null)
            model.addAttribute("Error al crear/editar comentario", message);
        if (successMsg != null)
            model.addAttribute("Comentario creado/editado con éxito", successMsg);
        return "lessons/showLessons";
    }


}
