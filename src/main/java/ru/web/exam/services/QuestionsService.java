package ru.web.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.web.exam.models.Course;
import ru.web.exam.models.Question;
import ru.web.exam.repositories.QuestionsRepository;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class QuestionsService {
    private final QuestionsRepository rep;
    private final CoursesService coursesService;

    @Autowired
    public QuestionsService(QuestionsRepository rep, CoursesService coursesService) {
        this.rep = rep;
        this.coursesService = coursesService;
    }

    public List<Question> findAll() {
        return rep.findAll();
    }

    public Question findOne(int id) {
        return rep.findById(id).orElse(null);
    }

    @Transactional
    public void save(Question question) {
        rep.save(question);
    }

    @Transactional
    public void update(int id, Question newQuestion) {
        newQuestion.setId(id);
        newQuestion.setCourse(rep.findById(id).get().getCourse());
        rep.save(newQuestion);
    }

    @Transactional
    public void delete(int id) {
        rep.deleteById(id);
    }
}
