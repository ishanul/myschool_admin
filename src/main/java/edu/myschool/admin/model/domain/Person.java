package edu.myschool.admin.model.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@MappedSuperclass
public class Person {
    @Id
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is invalid")
    private String email;
    @NotBlank(message = "Name is mandatory")
    @Size(max = 200, message = "Name is too long")
    private String name;

    public Person(){}
    public Person(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "email=" + email +
                ", name=" + name  +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(email, person.email) && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name);
    }
}
