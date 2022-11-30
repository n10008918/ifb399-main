package com.example.service.Imp;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.TeamDao;
import com.example.domain.Student;
import com.example.domain.Team;
import com.example.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeamServiceImp extends ServiceImpl<TeamDao, Team> implements TeamService {
    @Autowired
    private TeamDao teamDao;
    @Override
    /*first time create team*/
    public Team getTeamIdByStudentID(String studentColumn,Integer SId) {
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
        //StudentID1
        queryWrapper.eq(studentColumn,SId);
        return teamDao.selectOne(queryWrapper);
    }

    @Override
    public List<Team> getByStd(Integer sId) {
        LambdaQueryWrapper<Team> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Team::getSId1, sId).or();
        queryWrapper.eq(Team::getSId2, sId).or();
        queryWrapper.eq(Team::getSId3, sId).or();
        queryWrapper.eq(Team::getSId4, sId);
        return teamDao.selectList(queryWrapper);

    }

    @Override
    public void UpdateGroupMember(Team team,Integer sId) {
        Integer sId1 = team.getSId1();//1115001
        Integer sId2 = team.getSId2();//1115002
        Integer sId3 = team.getSId3();//1115007
        Integer sId4 = team.getSId4();//1115009
        if(sId.equals(sId1)){
            team.setSId1(0);//(0,1115002,1115007,1115009)
        }
        if(sId.equals(sId2)){
            team.setSId2(0);//(1115001,0,1115007,1115009)
        }
        if(sId.equals(sId3)){
            team.setSId3(0);//(1115001,1115002,0,1115009)
        }
        if(sId.equals(sId4)){
            team.setSId4(0);//(1115001,1115002,1115007,0)
        }

        teamDao.updateById(team);

    }

}
