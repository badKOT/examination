package ru.web.exam.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Не может быть пустым")
    @Size(min = 3, max = 100, message = "Поле должно содержать от 3 до 100 букв")
    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "owner")
    private List<Course> courses;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public Person(String username) {
        this.username = username;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + username + '}';
    }
}
