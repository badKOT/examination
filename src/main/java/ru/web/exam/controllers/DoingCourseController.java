package ru.web.exam.controllers;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.exam.models.Course;
import ru.web.exam.models.Question;
import ru.web.exam.services.CoursesService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/course")
public class DoingCourseController {
    private final CoursesService coursesService;
    private Course course;
    private List<Question> questions;
    private final List<Integer> userAnswers = new ArrayList<>();

    @Autowired
    public DoingCourseController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @GetMapping("{id}/start")
    public String start(@PathVariable int id) {
        course = coursesService.findOne(id);
        Hibernate.initialize(course.getQuestions());
        questions = course.getQuestions();
        questions.sort(Comparator.naturalOrder());
        return "redirect:/course/question/0";
    }

    @GetMapping("question/{index}")
    public String question(Model model, @PathVariable int index) {
        model.addAttribute("question", questions.get(index));
        model.addAttribute("index", index);
        return "courses/question";
    }

    @PostMapping("question/{index}")
    public String check(@PathVariable int index, @RequestParam("answerIndex") int answerIndex) {
        userAnswers.add(answerIndex);
        if (++index < course.getQuestions().size())
            return "redirect:/course/question/" + index;
        return "redirect:/course/" + course.getId() + "/result";
    }

    @GetMapping("{id}/result")
    public String result(@PathVariable int id, Model model) {
        model.addAttribute("course", course);
        model.addAttribute("questions", questions);
        model.addAttribute("userAnswers", userAnswers);
        return "courses/result";
    }
}
