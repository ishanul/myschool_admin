package edu.myschool.admin.model;

import javax.persistence.Entity;

@Entity
public class Student extends Person{
    public Student(long id, String email, String name) {
        super(id, email, name);
    }

    public Student() {

    }
}
