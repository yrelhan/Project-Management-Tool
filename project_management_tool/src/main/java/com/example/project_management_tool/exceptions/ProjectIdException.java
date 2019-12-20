package com.example.project_management_tool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//ProjectID Exception class to throw exceptions related to ProjectID
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException{

    public ProjectIdException(String message) {
        super(message);
    }
}
