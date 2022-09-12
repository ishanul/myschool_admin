package edu.myschool.admin.service;

import edu.myschool.admin.exception.AlreadyExistingException;
import edu.myschool.admin.model.domain.Status;
import edu.myschool.admin.model.domain.Student;
import edu.myschool.admin.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    private StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }

    public Student add(@Valid Student student) {
        Optional<Student> existing = repository.findByEmail(student.getEmail());
        if(existing.isEmpty()) {
            log.info("Saving the student {}", student);
            student.setStatus(Status.ACTIVE.getValue());
            return repository.save(student);
        }
        else{
            log.info("Student already exists {}", student);
            throw new AlreadyExistingException(student.getEmail());
        }
    }

    public Optional<Student> findByEmail(String email){
        return repository.findByEmail(email);
    }

    public Student update(Student student){
        return repository.save(student);
    }
}
