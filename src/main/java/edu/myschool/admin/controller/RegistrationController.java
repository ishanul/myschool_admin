package edu.myschool.admin.controller;

import edu.myschool.admin.model.domain.Registration;
import edu.myschool.admin.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private final RegistrationService service;

    public RegistrationController(RegistrationService service){
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void register(@Valid @RequestBody Registration registration) {
        log.info("Request received to register students {}", registration);
        service.register(registration);
    }
}
