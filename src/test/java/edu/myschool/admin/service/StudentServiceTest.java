package edu.myschool.admin.service;

import edu.myschool.admin.exception.AlreadyExistingException;
import edu.myschool.admin.model.domain.Status;
import edu.myschool.admin.model.domain.Student;
import edu.myschool.admin.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServiceTest {
    private StudentService service;

    private StudentRepository repository;
    private Student student;

    @BeforeEach
    void setup(){
        repository = mock(StudentRepository.class);
        service = new StudentService(repository);
        student = new Student("teacher@mail.com", "Student John");

    }

    @Test
    void givenNewStudent_whenCalledAdd_thenSuccess() {
        when(repository.save(student)).thenReturn(getStudent());
        student = service.add(student);
        assertNotNull(student.getEmail());
    }

    @Test
    void givenExistingStudent_whenCalledAdd_thenThrowException() {
        when(repository.findByEmail(student.getEmail())).thenReturn(Optional.of(getStudent()));
        when(repository.save(student)).thenReturn(getStudent());
        assertThrows(AlreadyExistingException.class, () -> service.add(student));
    }

    private Student getStudent(){
        Student student = new Student();
        student.setEmail("student@mail.com");
        student.setName("Student John");
        return student;
    }
}
