package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Student;
import com.example.domain.Team;

import java.util.List;

public interface TeamService extends IService<Team> {
    public Team getTeamIdByStudentID(String studentColumn,Integer SId);
    public List<Team> getByStd(Integer SId);
    public void UpdateGroupMember(Team team,Integer SId);
}
