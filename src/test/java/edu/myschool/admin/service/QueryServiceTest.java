package edu.myschool.admin.service;

import edu.myschool.admin.model.domain.CommonStudents;
import edu.myschool.admin.model.domain.Student;
import edu.myschool.admin.model.domain.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class QueryServiceTest {
    public static final String TEACHER1 = "teacher1@mail.com";
    public static final String TEACHER2 = "teacher2@mail.com";
    private TeacherService teacherService;

    private QueryService service;
    List<String> teachers;

    @BeforeEach
    void setup(){
        teacherService = mock(TeacherService.class);
        service = new QueryService(teacherService);
        teachers = Arrays.asList(TEACHER1, TEACHER2);
    }

    @Test
    void givenValidTeachersWithSameStudents_whenFindCommonStudents_thenReturnsCommonStudents() {
        when(teacherService.findByEmail(TEACHER1)).thenReturn(Optional.of(getTeacher(TEACHER1, "Teacher 1", studentsSet())));
        when(teacherService.findByEmail(TEACHER2)).thenReturn(Optional.of(getTeacher(TEACHER2, "Teacher 2", studentsSet())));

        CommonStudents commonStudents = service.findCommonStudents(teachers);

        assertTrue(commonStudents.getStudents().size() == 2);
    }

    @Test
    void givenValidTeachersWithDifferentStudents_whenFindCommonStudents_thenReturnsCommonStudents() {
        Set<Student> students2 = new HashSet<>();
        students2.add(getStudent("student3@mail.com", "Student 3"));
        students2.add(getStudent("student4@mail.com", "Student 4"));
        when(teacherService.findByEmail(TEACHER1)).thenReturn(Optional.of(getTeacher(TEACHER1, "Teacher 1", studentsSet())));
        when(teacherService.findByEmail(TEACHER2)).thenReturn(Optional.of(getTeacher(TEACHER2, "Teacher 2", students2)));

        CommonStudents commonStudents = service.findCommonStudents(teachers);

        assertTrue(commonStudents.getStudents().size() == 0);
    }

    @Test
    void givenValidTeachersWithOneCommonStudent_whenFindCommonStudents_thenReturnsCommonStudents() {
        Set<Student> students2 = new HashSet<>();
        students2.add(getStudent("student2@mail.com", "Student 2"));
        students2.add(getStudent("student4@mail.com", "Student 4"));
        when(teacherService.findByEmail(TEACHER1)).thenReturn(Optional.of(getTeacher(TEACHER1, "Teacher 1", studentsSet())));
        when(teacherService.findByEmail(TEACHER2)).thenReturn(Optional.of(getTeacher(TEACHER2, "Teacher 2", students2)));

        CommonStudents commonStudents = service.findCommonStudents(teachers);

        assertTrue(commonStudents.getStudents().size() == 1);
    }

    private Student getStudent(String email, String name) {
        return new Student(email, name);
    }

    private Teacher getTeacher(String email, String name, Set<Student> students){
        Teacher teacher = new Teacher();
        teacher.setEmail(email);
        teacher.setName(name);
        teacher.setStudents(students);
        return teacher;
    }

    private Set<Student> studentsSet(){
        Set<Student> students = new HashSet<>();
        students.add(getStudent("student1@mail.com", "Student 1"));
        students.add(getStudent("student2@mail.com", "Student 2"));
        return students;
    }
}
