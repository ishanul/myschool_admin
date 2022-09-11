package edu.myschool.admin.model.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    public Teacher(String email, String name) {
        super(email, name);
    }

    public Teacher() {
        super();
    }

    @ManyToMany
    @JoinTable(
            name = "teacher_students",
            joinColumns = @JoinColumn(name = "teacher_email"),
            inverseJoinColumns = @JoinColumn(name = "student_email"))
    private Set<Student> students;

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
