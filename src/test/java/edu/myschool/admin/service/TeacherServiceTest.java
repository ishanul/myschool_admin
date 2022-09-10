package edu.myschool.admin.service;

import edu.myschool.admin.exception.AlreadyExistingException;
import edu.myschool.admin.model.domain.Teacher;
import edu.myschool.admin.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TeacherServiceTest {
    private TeacherService service;

    private TeacherRepository repository;
    private Teacher teacher;

    @BeforeEach
    void setup(){
        repository = mock(TeacherRepository.class);
        service = new TeacherService(repository);
        teacher = new Teacher("teacher@mail.com", "Teacher John");
    }

    @Test
    void givenNewTeacher_whenCalledAdd_thenSuccess() throws AlreadyExistingException {
        when(repository.save(teacher)).thenReturn(getTeacher());
        teacher = service.add(teacher);
        assertNotNull(teacher.getEmail());
    }

    @Test
    void givenExistingTeacher_whenCalledAdd_thenThrowException() {
        when(repository.findByEmail(teacher.getEmail())).thenReturn(getTeacher());
        when(repository.save(teacher)).thenReturn(getTeacher());
        assertThrows(AlreadyExistingException.class, () -> service.add(teacher));
    }

    private Teacher getTeacher(){
        Teacher teacher = new Teacher();
        teacher.setEmail("teacher@mail.com");
        teacher.setName("Teacher John");
        return teacher;
    }
}
