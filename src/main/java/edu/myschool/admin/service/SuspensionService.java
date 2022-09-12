package edu.myschool.admin.service;

import edu.myschool.admin.exception.PersonNotFoundException;
import edu.myschool.admin.model.domain.Status;
import edu.myschool.admin.model.domain.Student;
import edu.myschool.admin.model.domain.Suspension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class SuspensionService {
    private static final Logger log = LoggerFactory.getLogger(SuspensionService.class);

    private final StudentService studentService;


    public SuspensionService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void suspend(@Valid Suspension suspension) {
        log.info("Suspending student {}", suspension);
        Student student = getStudent(suspension.getStudent());
        log.info("Suspending students {}", student);
        student.setStatus(Status.SUSPENDED.getValue());
        studentService.update(student);
        log.info("Suspension successful");
    }

    private Student getStudent(String student) {
        return studentService.findByEmail(student).orElseThrow(() -> new PersonNotFoundException(student));
    }
}
