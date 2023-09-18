package ru.web.exam.controllers;

import jakarta.validation.Valid;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.web.exam.models.Course;
import ru.web.exam.models.Person;
import ru.web.exam.models.Question;
import ru.web.exam.security.PersonDetails;
import ru.web.exam.services.CoursesService;
import ru.web.exam.services.PeopleService;
import ru.web.exam.services.QuestionsService;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin/courses")
public class AdminCoursesController {
    private final CoursesService coursesService;
    private final PeopleService peopleService;
    private final QuestionsService questionsService;

    @Autowired
    public AdminCoursesController(CoursesService coursesService, PeopleService peopleService, QuestionsService questionsService) {
        this.coursesService = coursesService;
        this.peopleService = peopleService;
        this.questionsService = questionsService;
    }

    @GetMapping("")
    public String allCourses(Model model) {
        model.addAttribute("courses", coursesService.findAll());
        return "admin/courses/courses";
    }

    @GetMapping("/{id}")
    public String courseInfo(@PathVariable int id, Model model) {
        Course course = coursesService.findOne(id);
        Hibernate.initialize(course.getQuestions());
        List<Question> questions = course.getQuestions();
        questions.sort(Comparator.naturalOrder());
        model.addAttribute("course", course);
        model.addAttribute("questions", questions);
        return "admin/courses/info";
    }

    @GetMapping("/new")
    public String newCourse(@ModelAttribute("course") Course course) {
        return "admin/courses/new";
    }

    @PostMapping("")
    public String addCourse(@ModelAttribute("course") @Valid Course course,
                            BindingResult br, Principal principal) {
        PersonDetails d = (PersonDetails) peopleService.
                loadUserByUsername(principal.getName());
        Person currentUser = d.getPerson();
        course.setOwner(currentUser);

        if (br.hasErrors())
            return "admin/courses/new";

        coursesService.save(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("course", coursesService.findOne(id));
        return "admin/courses/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("course") Course course, @PathVariable int id) {
//        if (br.hasErrors())
//            return "admin/courses/edit";

        coursesService.update(id, course);
        return "redirect:/admin/courses";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        coursesService.delete(id);
        return "redirect:/admin/courses";
    }
}
