package ru.web.exam.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnTransformer;
import ru.web.exam.util.MyConverter;

import java.util.*;

@Entity
@Table(name = "question")
public class Question implements Comparable<Question> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = MyConverter.class)
    @ColumnTransformer(write = "?::jsonb")
    private List<String> answers;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    public Question() {
    }

    public Question(String title, List<String> answers, String correctAnswer, Course course) {
        this.title = title;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", answers='" + answers + '\'' +
                ", correct='" + correctAnswer + '\'' +
                ", course=" + course.getId() +
                '}';
    }

    @Override
    public int compareTo(Question question) {
        return Objects.compare(this.getId(), question.getId(), Comparator.naturalOrder());
    }
}
