package com.taisiia.gitApp.git.service;

import com.taisiia.gitApp.git.dto.GitHubRepo;
import com.taisiia.gitApp.git.dto.GitRepositoryDto;

import java.util.List;

public interface GitHubService {

    List<GitRepositoryDto> getRepoByUserName(String userName);
}
