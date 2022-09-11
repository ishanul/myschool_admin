package edu.myschool.admin.service;

import edu.myschool.admin.exception.PersonNotFoundException;
import edu.myschool.admin.model.domain.Registration;
import edu.myschool.admin.model.domain.Student;
import edu.myschool.admin.model.domain.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);

    private TeacherService teacherService;
    private StudentService studentService;


    public RegistrationService(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    public void register(@Valid Registration registration) {
        Teacher teacher = getTeacher(registration);
        log.info("Registering students for teacher {}", teacher);
        Set<Student> students = extractStudents(registration);
        log.info("Registering students {}", students);

        updateTeacher(teacher, students);
        log.info("Registration successful");
    }

    private Teacher getTeacher(Registration registration) {
        return teacherService.findByEmail(registration.getTeacher()).orElseThrow(() -> new PersonNotFoundException(registration.getTeacher()));
    }

    private Set<Student> extractStudents(Registration registration) {
        return registration.getStudents().stream().map(student -> getStudent(student)).collect(Collectors.toSet());
    }

    private void updateTeacher(Teacher teacher, Set<Student> students) {
        if (teacher.getStudents() != null) {
            teacher.getStudents().addAll(students);
        } else {
            teacher.setStudents(students);
        }

        teacherService.update(teacher);
    }

    private Student getStudent(String student) {
        return studentService.findByEmail(student).orElseThrow(() -> new PersonNotFoundException(student));
    }


}
