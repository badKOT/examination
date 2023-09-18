package ru.web.exam.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotNull
    private String title;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

    @OneToMany(mappedBy = "course")
    private List<Question> questions;

    public Course() {
    }

    public Course(String title, Person owner, List<Question> questions) {
        this.title = title;
        this.owner = owner;
        this.questions = questions;
    }

    public Course(String title) {
        this.title = title;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
