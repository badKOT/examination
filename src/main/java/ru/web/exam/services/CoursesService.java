package ru.web.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.web.exam.models.Course;
import ru.web.exam.models.Person;
import ru.web.exam.repositories.CoursesRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CoursesService {
    private final CoursesRepository rep;
    private final PeopleService peopleService;

    @Autowired
    public CoursesService(CoursesRepository rep, PeopleService peopleService) {
        this.rep = rep;
        this.peopleService = peopleService;
    }

    public List<Course> findAll() {
        return rep.findAll();
    }

    public Course findOne(int id) {
        return rep.findByIdOrderById(id).orElse(null);
    }

//    public List<Course> searchByTitle(String title) {
//        return rep.findByTitleStartingWith(title);
//    }

    @Transactional
    public void save(Course course) {
        rep.save(course);
    }

    @Transactional
    public void update(int id, Course newCourse) {
        newCourse.setId(id);
        newCourse.setOwner(rep.findById(id).get().getOwner());
        rep.save(newCourse);
    }

    @Transactional
    public void delete(int id) {
        rep.deleteById(id);
    }
}


