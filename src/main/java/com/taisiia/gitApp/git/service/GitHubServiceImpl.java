package com.taisiia.gitApp.git.service;

import com.taisiia.gitApp.git.dto.Branch;
import com.taisiia.gitApp.git.dto.GitHubRepo;
import com.taisiia.gitApp.git.dto.GitRepositoryDto;
import com.taisiia.gitApp.git.exception.GitHubException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GitHubServiceImpl implements GitHubService{

    @Value("${github.api.url}")
    private  String url;

    @Override
    public List<GitRepositoryDto> getRepoByUserName(String userName) {
        List<GitHubRepo> gitHubRepoList = getGitHubRepoList(userName);

        return gitHubRepoList.stream().map(element ->{
           List<Branch> brunchList = getBrunchList( element.getOwner().getLogin(), element.getName());
           return new GitRepositoryDto(element.getName(),element.getOwner().getLogin(),brunchList);
       }).collect(Collectors.toList());
    }


    public List<GitHubRepo> getGitHubRepoList( String userName){
        return WebClient.create(url)
                .get()
                .uri("/users/{username}/repos", userName)
                .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(GitHubRepo.class)
                .filter(element -> !element.isFork())
                .switchIfEmpty(Mono.error(new GitHubException("User not found")))
                .collectList()
                .onErrorResume(WebClientResponseException.NotFound.class, e -> Mono.error(new GitHubException(e.getMessage())))
                .onErrorResume(WebClientResponseException.Unauthorized.class, error -> Mono.error(new GitHubException("Unauthorized access")))
                .onErrorResume(WebClientResponseException.class, error -> Mono.error(new GitHubException("GitHub API error")))
                .block();
    }

    public List<Branch> getBrunchList( String owner, String repo){
        return WebClient.create(url).get()
                .uri("/repos/{owner}/{repo}/branches", owner, repo)
                .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(Branch.class)
                .collectList()
                .onErrorResume(WebClientResponseException.NotFound.class, e -> Mono.error(new GitHubException(e.getMessage())))
                .onErrorResume(WebClientResponseException.Unauthorized.class, error -> Mono.error(new GitHubException("Unauthorized access")))
                .onErrorResume(WebClientResponseException.class, error -> Mono.error(new GitHubException("GitHub API error")))
                .block();
    }
}
