package edu.myschool.admin.service;

import edu.myschool.admin.exception.PersonNotFoundException;
import edu.myschool.admin.model.domain.Student;
import edu.myschool.admin.model.domain.Suspension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SuspensionServiceTest {
    public static final String STUDENT = "student@mail.com";
    private StudentService studentService;

    private SuspensionService service;

    @BeforeEach
    void setup() {
        studentService = mock(StudentService.class);

        service = new SuspensionService(studentService);
    }

    @Test
    void givenValidRecord_whenCallSuspend_thenSuccess() {
        Student student = getStudent();
        when(studentService.findByEmail(STUDENT)).thenReturn(Optional.of(getStudent()));

        service.suspend(getSuspension());
        verify(studentService, atLeast(1)).update(student);

    }

    @Test
    void givenInvalidStudent_whenCallSuspend_thenFailure() {
        when(studentService.findByEmail(STUDENT)).thenReturn(Optional.ofNullable(null));

        assertThrows(PersonNotFoundException.class, () -> service.suspend(getSuspension()));
    }

    private Suspension getSuspension() {
        Suspension suspension = new Suspension();
        suspension.setStudent(STUDENT);
        return suspension;
    }

    private Student getStudent() {
        Student student = new Student();
        student.setEmail(STUDENT);
        student.setName("Student John");
        return student;
    }
}
