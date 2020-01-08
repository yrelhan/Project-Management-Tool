package com.example.project_management_tool.repositories;

import com.example.project_management_tool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//Repository class to to CRUD operations on the Database
@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    //find project by id
    Project findByProjectIdentifier(String projectId);


    //Iterable function to find all projects in the database
    @Override
    Iterable<Project> findAll();

    Iterable<Project> findAllByProjectLeader(String username);

}
