package ru.web.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.web.exam.models.Course;
import ru.web.exam.models.Question;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Integer> {
}
