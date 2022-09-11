package edu.myschool.admin.controller;

import edu.myschool.admin.model.domain.CommonStudents;
import edu.myschool.admin.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/commonstudents")
public class QueryController {
    private static final Logger log = LoggerFactory.getLogger(QueryController.class);

    private QueryService service;

    public QueryController(QueryService service){
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonStudents commonStudents(@RequestParam List<String> teacher) {
        log.info("Request received to query students {}", teacher);
        return service.findCommonStudents(teacher);
    }
}
