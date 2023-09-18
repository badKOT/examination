package ru.web.exam.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "answer1")
    private String answer1;

    @Column(name = "answer2")
    private String answer2;

    @Column(name = "answer3")
    private String answer3;

    @Column(name = "answer4")
    private String answer4;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    public Question() {
    }

    public Question(String title, String answer1, String answer2, String answer3, String answer4, String correctAnswer, Course course) {
        this.title = title;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
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

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public List<String> getAnswers() {
        return List.of(answer1, answer2, answer3, answer4);
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
                ", 1='" + answer1 + '\'' +
                ", 2='" + answer2 + '\'' +
                ", 3='" + answer3 + '\'' +
                ", 4='" + answer4 + '\'' +
                ", correct='" + correctAnswer + '\'' +
                ", course=" + course.getId() +
                '}';
    }

    @Override
    public int compareTo(Question question) {
        return Objects.compare(this.getId(), question.getId(), Comparator.naturalOrder());
    }
}
