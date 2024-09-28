package io.github.almeidagianluca.food_facts_api.controller.impl;

import io.github.almeidagianluca.food_facts_api.controller.ApplicationStatusController;
import io.github.almeidagianluca.food_facts_api.model.ApplicationStatus;
import io.github.almeidagianluca.food_facts_api.service.ApplicationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class ApplicationStatusControllerImpl implements ApplicationStatusController {

    @Autowired
    private ApplicationStatusService applicationStatusService;
    @Override
    @GetMapping
    public ApplicationStatus getApplicationStatus() {
        return applicationStatusService.getApplicationStatus();
    }
}
