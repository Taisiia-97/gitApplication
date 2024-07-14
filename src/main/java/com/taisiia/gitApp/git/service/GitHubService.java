package com.taisiia.gitApp.git.service;

import com.taisiia.gitApp.git.dto.Branch;
import com.taisiia.gitApp.git.dto.GitHubRepo;
import com.taisiia.gitApp.git.dto.GitRepositoryDto;

import java.util.List;

public interface GitHubService {

    List<GitRepositoryDto> getRepoByUserName(String userName);

    List<Branch> getBrunchList(String owner, String repo);

    List<GitHubRepo> getGitHubRepoList( String userName);


}
