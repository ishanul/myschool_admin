package edu.myschool.admin.model;

import javax.persistence.Entity;

@Entity
public class Teacher extends Person{
    public Teacher(String email, String name) {
        super(email, name);
    }

    public Teacher() {
        super();
    }
}
