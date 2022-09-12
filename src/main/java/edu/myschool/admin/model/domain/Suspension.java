package edu.myschool.admin.model.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Suspension {
    @NotBlank
    @Email
    private String student;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Suspension{" +
                "student='" + student + '\'' +
                '}';
    }
}
