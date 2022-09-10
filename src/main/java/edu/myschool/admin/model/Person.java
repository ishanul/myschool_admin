package edu.myschool.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is invalid")
    private String email;
    @NotBlank(message = "Name is mandatory")
    @Size(max = 200, message = "Name is too long")
    private String name;
}
