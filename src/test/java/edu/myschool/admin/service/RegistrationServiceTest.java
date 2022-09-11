package edu.myschool.admin.service;

import edu.myschool.admin.exception.PersonNotFoundException;
import edu.myschool.admin.model.domain.Registration;
import edu.myschool.admin.model.domain.Student;
import edu.myschool.admin.model.domain.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RegistrationServiceTest {
    public static final String TEACHER = "teacher@mail.com";
    public static final String STUDENT = "student@mail.com";
    private TeacherService teacherService;
    private StudentService studentService;

    private RegistrationService service;

    @BeforeEach
    void setup(){
        teacherService = mock(TeacherService.class);
        studentService = mock(StudentService.class);

        service = new RegistrationService(teacherService, studentService);
    }

    @Test
    void givenValidRecord_whenCallRegister_thenSuccess() {
        Teacher teacher = getTeacher();
        when(teacherService.findByEmail(TEACHER)).thenReturn(Optional.of(teacher));
        when(studentService.findByEmail(STUDENT)).thenReturn(Optional.of(getStudent()));

        service.register(getRegistration());
        verify(teacherService, atLeast(1)).update(teacher);

    }

    @Test
    void givenInvalidTeacher_whenCallRegister_thenFailure() {
        when(teacherService.findByEmail(TEACHER)).thenReturn(Optional.ofNullable(null));
        when(studentService.findByEmail(STUDENT)).thenReturn(Optional.of(getStudent()));

        assertThrows(PersonNotFoundException.class, () -> service.register(getRegistration()));
    }

    @Test
    void givenInvalidStudent_whenCallRegister_thenFailure() {
        when(teacherService.findByEmail(TEACHER)).thenReturn(Optional.of(getTeacher()));
        when(studentService.findByEmail(STUDENT)).thenReturn(Optional.ofNullable(null));

        assertThrows(PersonNotFoundException.class, () -> service.register(getRegistration()));
    }

    private Registration getRegistration(){
        Registration registration = new Registration();
        registration.setTeacher(TEACHER);
        Set<String> students = new HashSet<>();
        students.add(STUDENT);
        registration.setStudents(students);
        return registration;
    }

    private Teacher getTeacher(){
        Teacher teacher = new Teacher();
        teacher.setEmail(TEACHER);
        teacher.setName("Teacher John");
        return teacher;
    }

    private Student getStudent(){
        Student student = new Student();
        student.setEmail(STUDENT);
        student.setName("Student John");
        return student;
    }
}
