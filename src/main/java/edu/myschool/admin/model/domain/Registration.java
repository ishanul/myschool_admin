package edu.myschool.admin.model.domain;

import javax.validation.constraints.Email;
import java.util.Set;

public class Registration {
    @Email
    private String teacher;
    private Set<String> students;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Set<String> getStudents() {
        return students;
    }

    public void setStudents(Set<String> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "teacher='" + teacher + '\'' +
                ", students=" + students +
                '}';
    }
}
