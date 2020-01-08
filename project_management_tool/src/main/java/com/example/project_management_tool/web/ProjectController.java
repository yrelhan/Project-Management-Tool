package com.example.project_management_tool.web;


import com.example.project_management_tool.domain.Project;
import com.example.project_management_tool.services.MapValidationErrorService;
import com.example.project_management_tool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Controller layer to pass all CRUD operations to service layer
@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = " * ", allowedHeaders = " * ")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    //creating new project
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        //if error encountered, don't save project. Throw exception.
        if(errorMap!=null)
            return errorMap;

        Project project1 = projectService.saveOrUpdateProject(project,principal.getName());
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    //getting project by projectId
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId, Principal principal){

        Project project = projectService.findProjectByIdentifier(projectId,principal.getName());
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }

    //getting all projects in the database
    @GetMapping("/all")
    public Iterable<Project> getAllProjects(Principal principal){return projectService.findAllProjects(principal.getName());}


    //deleting project by projectId
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId, Principal principal){
        projectService.deleteProjectByIdentifier(projectId, principal.getName());

        return new ResponseEntity<String>("Project with ID " + projectId + " deleted", HttpStatus.OK);
    }
}
