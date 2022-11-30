package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto extends Team{
    private String studentName1;
    private String studentName2;
    private String studentName3;
    private String studentName4;
}
