package edu.myschool.admin.model.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

public class Suspension {
    @NotBlank
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
