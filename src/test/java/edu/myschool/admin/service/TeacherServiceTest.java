package edu.myschool.admin.service;

import edu.myschool.admin.model.Teacher;
import edu.myschool.admin.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TeacherServiceTest {
    private TeacherService service;

    private TeacherRepository repository;

    @BeforeEach
    void setup(){
        repository = mock(TeacherRepository.class);
        service = new TeacherService(repository);
    }

    @Test
    void givenNewTeacher_whenCalledAdd_thenSuccess() throws Exception{
        Teacher teacher = new Teacher("teacher@mail.com", "Teacher John");
        when(repository.save(teacher)).thenReturn(getTeacher());
        teacher = service.add(teacher);
        assertNotNull(teacher.getId());
    }

    @Test
    void givenExistingTeacher_whenCalledAdd_thenThrowException() throws Exception{
        Teacher teacher = new Teacher("teacher@mail.com", "Teacher John");
        when(repository.findByEmail(teacher.getEmail())).thenReturn(getTeacher());
        when(repository.save(teacher)).thenReturn(getTeacher());
        assertThrows(Exception.class, () -> service.add(teacher));
    }

    private Teacher getTeacher(){
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setEmail("teacher@mail.com");
        teacher.setName("Teacher John");
        return teacher;
    }
}
