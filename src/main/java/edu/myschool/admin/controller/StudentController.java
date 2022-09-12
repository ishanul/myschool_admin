package edu.myschool.admin.controller;

import edu.myschool.admin.model.domain.Student;
import edu.myschool.admin.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> add(@Valid @RequestBody Student student) {
        log.info("Request received to save student {}", student);
        return new ResponseEntity<>(service.add(student), HttpStatus.CREATED);
    }
}
