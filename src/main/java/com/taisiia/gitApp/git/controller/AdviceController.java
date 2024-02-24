package com.taisiia.gitApp.git.controller;

import com.taisiia.gitApp.git.dto.ErrorResponseDto;
import com.taisiia.gitApp.git.exception.GitHubException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(GitHubException.class)
    public ErrorResponseDto handleEntityNotFoundException(GitHubException gitHubException) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), gitHubException.getMessage());
    }
}
