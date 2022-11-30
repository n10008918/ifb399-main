package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private int id;
    @TableField(value = "studentID1")
    private Integer SId1;
    @TableField(value = "studentID2")
    private Integer SId2;
    @TableField(value = "studentID3")
    private Integer SId3;
    @TableField(value = "studentID4")
    private Integer SId4;

    private String teamIntroduction;

    public Team(int id, Integer SId1,Integer SId2,Integer SId3,Integer SId4){
        this.id = id;
        this.SId1 = SId1;
        this.SId2 = SId2;
        this.SId3 = SId3;
        this.SId4 = SId4;
    }

    public Team(Integer SId1, Integer SId2, Integer SId3, Integer SId4) {
        this.SId1 = SId1;
        this.SId2 = SId2;
        this.SId3 = SId3;
        this.SId4 = SId4;
    }
    public Team(Integer SId1){
        this.SId1 = SId1;
    }
}
