package com.taisiia.gitApp.git.service;

import com.taisiia.gitApp.git.dto.Branch;
import com.taisiia.gitApp.git.dto.GitHubRepo;
import com.taisiia.gitApp.git.dto.GitRepositoryDto;
import com.taisiia.gitApp.git.exception.GitHubException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GitHubServiceImpl implements GitHubService{
    private final WebClient.Builder webClientBuilder;


    @Value("${github.api.url}")
    private String url;

    @Value("${github.api.token}")
    private String token;
    @Override
    public List<GitRepositoryDto> getRepoByUserName(String userName) {
        WebClient webClient = webClientBuilder.baseUrl(url)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
        List<GitHubRepo> gitHubRepoList = getGitHubRepoList(webClient,userName);


       return gitHubRepoList.stream().map(element ->{
           List<Branch> brunchList = getBrunchList(webClient, element.getOwner().getLogin(), element.getName());
           return new GitRepositoryDto(element.getName(),element.getOwner().getLogin(),brunchList);
       }).collect(Collectors.toList());

    }

    public List<GitHubRepo> getGitHubRepoList(WebClient webClient, String userName){
        return webClient.get()
                .uri("/users/{username}/repos", userName)
                .retrieve()
                .bodyToFlux(GitHubRepo.class)
                .filter(element -> !element.isFork())
                .switchIfEmpty(Mono.error(new GitHubException("User not found")))
                .collectList()
                .onErrorResume(WebClientResponseException.NotFound.class, e ->
                                Mono.error(new GitHubException(e.getMessage())))
                .block();
    }

    public List<Branch> getBrunchList(WebClient webClient, String owner, String repo){
        return webClient.get()
                .uri("/repos/{owner}/{repo}/branches", owner, repo)
                .retrieve()
                .bodyToFlux(Branch.class)
                .collectList()
                .onErrorResume(WebClientResponseException.NotFound.class, e ->
                Mono.error(new GitHubException(e.getMessage())))
                .block();
    }
}
