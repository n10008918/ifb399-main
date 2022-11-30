package com.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String surname;
    private String firstname;
    private String password;
    private String email;
    private String Major;
    private String Minor;
    private String Skills;
    private String selfIntroduction;
    private Integer groupStatus;
    private Integer groupId;
    private Integer status;

}
