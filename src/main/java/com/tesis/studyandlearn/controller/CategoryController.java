package com.tesis.studyandlearn.controller;

import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.service.CategoryService;
import com.tesis.studyandlearn.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String showAllCategory(Model model) {
        model.addAttribute("listLessonCategory", categoryService.showAllCategory());
        return "lessons/lessonCategory";
    }

    @GetMapping("/{categoryId}")
    public String showLessonsByCategory(@PathVariable("categoryId") int categoryId, Model model) {
        List<LessonEntity> lessonEntities = lessonService.showAllByCategory(categoryId);
        model.addAttribute("listLessons", lessonEntities);
        model.addAttribute("category", categoryService.showCategoryById(categoryId));
        return "lessons/lessons";
    }

}
