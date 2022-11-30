package com.example;

import com.example.domain.Invitation;
import com.example.domain.Team;
import com.example.service.Imp.InvitationServicesImp;
import com.example.service.Imp.TeamServiceImp;
import com.example.service.InvitationService;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TeamTests {
    @Autowired
    private TeamServiceImp teamServiceImp;
    @Autowired
    private InvitationService invitationService;


    /*根据ID查找团队*/
    @Test
    void testSearchTeam() {
        Team team = teamServiceImp.getById(1);
        System.out.println(team);
    }

    /*创建团队*/
    @Test
    void testCreateTeam() {
        boolean flag = teamServiceImp.save(new Team());
    }

    /*删除团队*/
    @Test
    void testDeleteTeam() {
        boolean flag = teamServiceImp.removeById(3);
    }

    /*更新团队(添加成员,删除成员,'0'表示不存在成员)*/
    @Test
    void addMember() {
        Team team = new Team(3, 11151900, 11151900, 11151900, 11151900);
        teamServiceImp.updateById(team);
    }

    @Test
    void removeMember() {
        Team team = new Team(3, 0, 11151900, 11151900, 11151900);
        teamServiceImp.updateById(team);
    }

    @Test
    void testSearchTeamByStudentID() {
        String studentColumn = "StudentID1";
        Integer SId = 11150001;
        Team team = teamServiceImp.getTeamIdByStudentID(studentColumn, SId);
        System.out.println(team);
    }

    @Test
    void testSearchTeamByStudentID2() {
        int SId = 11150002;
        List<Team> list = teamServiceImp.getByStd(SId);
        System.out.println(list);

//        Team team = teamServiceImp.getByStd(SId);
//        System.out.println(team);
    }

    @Test
    void updateGroupMembers(){
        Integer teamId = 200;
        Integer studentId = 11150002;
        Team team = teamServiceImp.getById(teamId);
        teamServiceImp.UpdateGroupMember(team,studentId);
    }

    @Test
    void testNewGetTeamInfo(){
        System.out.println(teamServiceImp.getById(200));
    }

    @Test
    void Invitationtest2(){
        List<Invitation> invitations = invitationService.getInvitations(String.valueOf(11151005));
//        List<Invitation> invitations = invitationService.getInvitations(String.valueOf(11140001));
        System.out.println("333: " + invitations);
    }

}
