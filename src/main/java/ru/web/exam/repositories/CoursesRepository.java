package ru.web.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.web.exam.models.Course;

import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByIdOrderById(int id);
}
