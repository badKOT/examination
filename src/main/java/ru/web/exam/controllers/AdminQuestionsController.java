package ru.web.exam.controllers;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.exam.models.Course;
import ru.web.exam.models.Question;
import ru.web.exam.services.CoursesService;
import ru.web.exam.services.QuestionsService;

import java.util.*;

@Controller
@RequestMapping("/admin/questions")
public class AdminQuestionsController {
    private final QuestionsService questionsService;
    private final CoursesService coursesService;
    private Course course;

    @Autowired
    public AdminQuestionsController(QuestionsService questionsService, CoursesService coursesService) {
        this.questionsService = questionsService;
        this.coursesService = coursesService;
    }

    @GetMapping("/course/{id}")
    public String hello(@PathVariable int id, Model model) {
        course = coursesService.findOne(id);
        Hibernate.initialize(course.getQuestions());
        List<Question> questions = course.getQuestions();
        questions.sort(Comparator.naturalOrder());
        model.addAttribute("course", course);
        model.addAttribute("questions", questions);
        return "admin/questions/start";
    }
    @GetMapping("/add")
    public String newQuestion(Model model) {
        model.addAttribute("course", course);
        model.addAttribute("question", new Question());
        return "admin/questions/new";
    }

    @PostMapping("")
    public String addQuestion(@ModelAttribute("question") Question question) {
        question.setCourse(course);
        question.setCorrectAnswer(question.getAnswers().get(Integer.parseInt(question.getCorrectAnswer())));
        questionsService.save(question);
        return "redirect:/admin/questions/course/" + course.getId();
    }

    @GetMapping("/{questionId}")
    public String questionInfoAndEdit(Model model, @PathVariable int questionId) {
        Question question = questionsService.findOne(questionId);
        model.addAttribute("question", question);
        return "admin/questions/info";
    }

    @PatchMapping("/{questionId}/edit")
    public String updateQuestion(@ModelAttribute("question") Question question,
                                 @PathVariable("questionId") int questionId ) {
        question.setCourse(course);
        questionsService.update(questionId, question);
        return "redirect:/admin/questions/course/" + course.getId();
    }

    @DeleteMapping("/{questionId}/delete")
    public String deleteQuestion(@PathVariable int questionId) {
        questionsService.delete(questionId);
        return "redirect:/admin/questions/course/" + course.getId();
    }
}
/*
create table student_course_join(
    id int references student(id) on delete cascade ,
    course_id int references course(course_id) on delete cascade
);

alter table student_course_join
add constraint scj_primary_key
primary key (id, course_id);
 */