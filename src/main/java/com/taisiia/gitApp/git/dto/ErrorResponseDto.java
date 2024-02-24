package com.taisiia.gitApp.git.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ErrorResponseDto {
    private int status;
    private String message;
}
