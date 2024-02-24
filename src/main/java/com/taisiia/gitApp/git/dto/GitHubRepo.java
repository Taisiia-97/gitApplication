package com.taisiia.gitApp.git.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class GitHubRepo {

    private String name;
    private Owner owner;
    boolean fork;
}
