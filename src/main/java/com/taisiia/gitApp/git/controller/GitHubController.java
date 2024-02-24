package com.taisiia.gitApp.git.controller;

import com.taisiia.gitApp.git.dto.GitRepositoryDto;
import com.taisiia.gitApp.git.service.GitHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/git")
public class GitHubController {

    private final GitHubService gitHubService;

    @GetMapping("/repo/{userName}")
    public List<GitRepositoryDto> getRepoByUserName(@PathVariable("userName") String userName){
        return  gitHubService.getRepoByUserName(userName);
    }

}
