package com.example.project_management_tool.services;

import com.example.project_management_tool.domain.Backlog;
import com.example.project_management_tool.domain.Project;
import com.example.project_management_tool.exceptions.ProjectIdException;
import com.example.project_management_tool.repositories.BacklogRepository;
import com.example.project_management_tool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//ProjectService class to perform CRUD operations through Repository
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;


    //saveOrUpdate project
    public Project saveOrUpdateProject(Project project){

        //checking if project already exists
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("ProjectID " + project.getProjectIdentifier().toUpperCase() + " already exists");
        }
    }

    //finding project by projectId
    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        //if no project with projectID was found
        if(project==null){
            throw new ProjectIdException("ProjectID " + projectId + " doesn't exists");
        }

        return project;
    }

    //finding all projects
    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    //deleting project by projectId
    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        //checking is project exists in the database
        if(project==null){
            throw new ProjectIdException("Cannot delete project with id " + projectId + ". This project doesn't exist");
        }

        projectRepository.delete(project);
    }
}
