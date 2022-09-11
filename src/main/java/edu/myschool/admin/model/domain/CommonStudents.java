package edu.myschool.admin.model.domain;

import java.util.Set;

public class CommonStudents {
    public CommonStudents(Set<String> students) {
        this.students = students;
    }

    private Set<String> students;

    public Set<String> getStudents() {
        return students;
    }

    public void setStudents(Set<String> students) {
        this.students = students;
    }
}
