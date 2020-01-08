package com.example.project_management_tool.repositories;

import com.example.project_management_tool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long> {

    Backlog findByProjectIdentifier(String identifier);
}
