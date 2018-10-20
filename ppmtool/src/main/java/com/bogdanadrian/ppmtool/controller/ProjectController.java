package com.bogdanadrian.ppmtool.controller;

import com.bogdanadrian.ppmtool.domain.Project;
import com.bogdanadrian.ppmtool.service.ErrorValidationService;
import com.bogdanadrian.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ErrorValidationService errorValidationService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody final Project project, final BindingResult bindingResult) {
        final ResponseEntity<?> fieldErrors = errorValidationService.mapValidationService(bindingResult);
        return fieldErrors == null ? new ResponseEntity<Project>(projectService.saveOrUpdate(project), HttpStatus.CREATED) : fieldErrors;
    }

    @GetMapping("/{projectID}")
    public ResponseEntity<?> getProjectByID(@PathVariable final String projectID) {
        final Project project = projectService.findProjectByIdentifier(projectID);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("")
    public Iterable<Project> gettAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectID}")
    public ResponseEntity<?> deleteProjectByID(@PathVariable final String projectID) {
        projectService.deleteProjectByIdentifier(projectID);
        return new ResponseEntity<String>("Project with ID: " + projectID + " was deleted.", HttpStatus.OK);
    }

}
