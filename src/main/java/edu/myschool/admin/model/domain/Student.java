package edu.myschool.admin.model.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person {
    public Student(String email, String name) {
        super(email, name);
    }

    public Student() {

    }

    private Integer status;

    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers;

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
