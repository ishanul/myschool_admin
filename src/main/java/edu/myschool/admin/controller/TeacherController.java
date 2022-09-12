package edu.myschool.admin.controller;

import edu.myschool.admin.model.domain.Teacher;
import edu.myschool.admin.service.TeacherService;
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
@RequestMapping("api/teachers")
public class TeacherController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private final TeacherService service;

    public TeacherController(TeacherService service){
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> add(@Valid @RequestBody Teacher teacher) {
        log.info("Request received to save teacher {}", teacher);

        return new ResponseEntity<>(service.add(teacher), HttpStatus.CREATED);
    }
}
