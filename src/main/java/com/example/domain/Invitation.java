package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invitation {
    private int id;
    private Date invitationTime;
    @TableField(exist = false)
    private String invitationStringTime;
    private String type;
    private Integer status;
    private String inviterId;
    private String invitedId;
    private String invitedTeamId;
    private String applicantId;
    private String notifyId;
    private String approved;
    private String description;
}
