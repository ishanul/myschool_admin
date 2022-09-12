package edu.myschool.admin.service;

import edu.myschool.admin.exception.PersonNotFoundException;
import edu.myschool.admin.model.domain.CommonStudents;
import edu.myschool.admin.model.domain.Person;
import edu.myschool.admin.model.domain.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QueryService {
    private static final Logger log = LoggerFactory.getLogger(QueryService.class);

    private final TeacherService teacherService;

    public QueryService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public CommonStudents findCommonStudents(List<String> teachers) {
        log.info("Querying common students for {}", teachers);
        Set<String> commonStudents = new HashSet<>();
        teachers.forEach(email -> {
            Teacher teacher = teacherService.findByEmail(email).orElseThrow(() -> new PersonNotFoundException(email));
            Set<String> students = teacher.getStudents().stream().map(Person::getEmail).collect(Collectors.toSet());
            extractCommonStudents(commonStudents, students);
        });

        return new CommonStudents(commonStudents);
    }

    private void extractCommonStudents(Set<String> commonStudents, Set<String> students) {
        if(commonStudents.isEmpty()){
            commonStudents.addAll(students);
        }
        else {
            commonStudents.retainAll(students);
        }
    }

}
