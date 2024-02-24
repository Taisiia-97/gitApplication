package com.taisiia.gitApp.git.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Branch {

    private String name;
    private Commit commit;

}
