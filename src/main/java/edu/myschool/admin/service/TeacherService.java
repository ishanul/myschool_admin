package edu.myschool.admin.service;

import edu.myschool.admin.exception.AlreadyExistingException;
import edu.myschool.admin.model.domain.Teacher;
import edu.myschool.admin.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class TeacherService {
    private static final Logger log = LoggerFactory.getLogger(TeacherService.class);

    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository){
        this.repository = repository;
    }

    public Teacher add(@Valid Teacher teacher) {
        Optional<Teacher> existing = repository.findByEmail(teacher.getEmail());
        if(existing.isEmpty()) {
            log.info("Saving the teacher {}", teacher);
            return repository.save(teacher);
        }
        else{
            log.info("Teacher already exists {}", teacher);
            throw new AlreadyExistingException(teacher.getEmail());
        }
    }

    public Teacher update(Teacher teacher){
        return repository.save(teacher);
    }
    public Optional<Teacher> findByEmail(String email){
        return repository.findByEmail(email);
    }

}
