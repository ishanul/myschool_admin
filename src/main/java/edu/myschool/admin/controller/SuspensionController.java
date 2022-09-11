package edu.myschool.admin.controller;

import edu.myschool.admin.model.domain.Suspension;
import edu.myschool.admin.service.SuspensionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/suspend")
public class SuspensionController {
    private static final Logger log = LoggerFactory.getLogger(SuspensionController.class);

    private SuspensionService service;

    public SuspensionController(SuspensionService service){
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void register(@Valid @RequestBody Suspension suspension) {
        log.info("Request received to suspend a student {}", suspension);
        service.suspend(suspension);
    }
}
