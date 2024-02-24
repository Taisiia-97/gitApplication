package com.taisiia.gitApp.git.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GitRepositoryDto {

    private String repositoryName;
    private String ownerLogin;
    private List<Branch> branches;

}
