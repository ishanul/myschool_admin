package edu.myschool.admin.model;

import javax.persistence.Entity;

@Entity
public class Student extends Person{
    public Student(String email, String name) {
        super(email, name);
    }

    public Student() {

    }
}
