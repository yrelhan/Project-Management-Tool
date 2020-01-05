package com.example.project_management_tool.services;

import com.example.project_management_tool.domain.Backlog;
import com.example.project_management_tool.domain.Project;
import com.example.project_management_tool.domain.ProjectTask;
import com.example.project_management_tool.exceptions.ProjectNotFoundException;
import com.example.project_management_tool.repositories.BacklogRepository;
import com.example.project_management_tool.repositories.ProjectRepository;
import com.example.project_management_tool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {


    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;



    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username){

            //PTs to be added to a specific project, project != null, BL exists
            Backlog backlog =  projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();
            //backlogRepository.findByProjectIdentifier(projectIdentifier);
            //set the backlog to project
            projectTask.setBacklog(backlog);
            //we want our project sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
            Integer BacklogSequence = backlog.getPTSequence();
            // Update the BL SEQUENCE
            BacklogSequence++;
            backlog.setPTSequence(BacklogSequence);

            //Add Sequence to Project Task
            projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            //INITIAL priority when priority null
            //add projectTask check for 0
            //Check for null first
            //In the future we need projectTask.getPriority()== 0 to handle the form
            if(projectTask.getPriority()==null||projectTask.getPriority()==0){
                projectTask.setPriority(3);
            }
            //INITIAL status when status is null
            if(projectTask.getStatus()==""|| projectTask.getStatus()==null){
                projectTask.setStatus("TO_DO");
            }
            return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask>findBacklogById(String backlog_id, String username){

        projectService.findProjectByIdentifier(backlog_id, username);
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id, String username){

        projectService.findProjectByIdentifier(backlog_id, username);

        //make sure that our task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);

        if(projectTask == null){
            throw new ProjectNotFoundException("Project Task '"+pt_id+"' not found");
        }

        //make sure that the backlog/project id in the path corresponds to the right project
        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task '"+pt_id+"' does not exist in project: '"+backlog_id);
        }

        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id, String username){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id, username);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }
    //Update project task

    //find existing project task

    //replace it with updated task

    //save update


    public void deletePTByProjectSequence(String backlog_id, String pt_id, String username){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id, username);

        Backlog backlog = projectTask.getBacklog();
        List<ProjectTask> pts = backlog.getProjectTasks();
        pts.remove(projectTask);
        backlogRepository.save(backlog);

        projectTaskRepository.delete(projectTask);
    }
    //handle exception when project not found
    //project tasks to be added to a specific project, project!=null --> backlog exists
    //set backlog to the projecttask
    //take care of project sequence
    //update backlog sequence
    //add sequence to project task
    //priority
    //initial priority when priority is null
    //initial status when status is null
}

