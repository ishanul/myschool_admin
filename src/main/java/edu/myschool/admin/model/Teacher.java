package edu.myschool.admin.model;

import javax.persistence.Entity;

@Entity
public class Teacher extends Person{
    public Teacher(long id, String email, String name) {
        super(id, email, name);
    }

    public Teacher() {
        super();
    }
}
