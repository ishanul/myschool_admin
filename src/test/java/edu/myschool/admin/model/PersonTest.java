package edu.myschool.admin.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class PersonTest {

    @Test
    public void whenCalledGetEmail_thenCorrect() {
        Person person = new Person("test@mail.com", "John Doe");
        assertThat(person.getEmail()).isEqualTo("test@mail.com");
    }
    @Test
    public void whenCalledGetName_thenCorrect() {
        Person person = new Person("test@mail.com", "John Doe");
        assertThat(person.getName()).isEqualTo("John Doe");
    }

    @Test
    public void whenCalledSetName_thenCorrect() {
        Person person = new Person("test@mail.com", "John Doe");
        person.setName("John");
        assertThat(person.getName()).isEqualTo("John");
    }

    @Test
    public void whenCalledSetEmail_thenCorrect() {
        Person person = new Person("test@mail.com", "John Doe");
        person.setEmail("john@mail.com");
        assertThat(person.getEmail()).isEqualTo("john@mail.com");
    }

    @Test
    public void whenCalledtoString_thenCorrect() {
        Person person = new Person("test@mail.com", "John Doe");
        assertThat(person.toString()).isEqualTo("Person{id=1, email=test@mail.com, name=John Doe}");
    }
}
