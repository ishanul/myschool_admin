package edu.myschool.admin.service;

import edu.myschool.admin.exception.AlreadyExistingException;
import edu.myschool.admin.model.Teacher;
import edu.myschool.admin.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Objects;

@Service
public class TeacherService {
    private TeacherRepository repository;

    public TeacherService(TeacherRepository repository){
        this.repository = repository;
    }

    public Teacher add(@Valid Teacher teacher) throws AlreadyExistingException {
        Teacher existing = repository.findByEmail(teacher.getEmail());
        if(Objects.isNull(existing)) {
            return repository.save(teacher);
        }
        else{
            throw new AlreadyExistingException(teacher.getEmail());
        }
    }
}
