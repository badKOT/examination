package ru.web.exam.controllers;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.web.exam.models.Course;
import ru.web.exam.models.Person;
import ru.web.exam.services.CoursesService;
import ru.web.exam.services.PeopleService;

@Controller
@RequestMapping("/courses")
public class CoursesController {
    private final CoursesService coursesService;
    private final PeopleService peopleService;

    @Autowired
    public CoursesController(CoursesService coursesService, PeopleService peopleService) {
        this.coursesService = coursesService;
        this.peopleService = peopleService;
    }

    @GetMapping("")
    public String helloPage(Model model) {
        model.addAttribute("courses", coursesService.findAll());
        return "courses/index";
    }

    @GetMapping("/{id}")
    public String courseInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("course", coursesService.findOne(id));
        return "courses/show";
    }

    @GetMapping("/{id}/owner")
    public String courseOwner(@PathVariable("id") int id, Model model) {
        Person person = coursesService.findOne(id).getOwner();
        Hibernate.initialize(person.getCourses());
        model.addAttribute("person", person);
        model.addAttribute("courses", person.getCourses());
        return "courses/owner";
    }
}
