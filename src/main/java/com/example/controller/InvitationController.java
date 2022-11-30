package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.domain.Invitation;
import com.example.domain.InvitationDto;
import com.example.domain.Student;
import com.example.domain.Team;
import com.example.service.Imp.StudentServiceImp;
import com.example.service.Imp.TeamServiceImp;
import com.example.service.InvitationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/invitations")
@Slf4j
public class InvitationController {
    @Autowired
    private StudentServiceImp studentServiceImp;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private TeamServiceImp teamServiceImp;

    @RequestMapping("/create")
    @ResponseBody
    public R<String> createInvitation(Integer studentID, @RequestBody Invitation invitation) {
        if("GroupInvitation".equals(invitation.getType())){
            Student invitor = studentServiceImp.getById(studentID);
            //invitation is invalid when team is full
            List<Team> teams = teamServiceImp.getByStd(invitor.getId());
            if (!teams.isEmpty()) {//不可能为空
                Team team = teams.get(0);//unique
                if (team.getSId1() != 0 && team.getSId2() != 0 && team.getSId3() != 0 && team.getSId4() != 0) {
                    return R.error("team is full");
                }
            }
            //create invitation
            invitation.setInvitedTeamId(String.valueOf(invitor.getGroupId()));
            invitation.setInvitationTime(new Date());
            invitationService.createInvitation(invitation);
            return R.success("invite success");
        }

        if("ApplicantApply".equals(invitation.getType())){
            invitation.setInviterId(null);
            invitation.setInvitedId(null);

            String teamId = invitation.getInvitedTeamId();
            Team team = teamServiceImp.getById(teamId);
            //invitation is invalid when team is full
            if (team.getSId1() != 0 && team.getSId2() != 0 && team.getSId3() != 0 && team.getSId4() != 0) {
                return R.error("team is full");
            }
            if(team.getSId1() != 0){
                invitation.setNotifyId(String.valueOf(team.getSId1()));
                invitation.setInvitationTime(new Date());
                invitationService.createInvitation(invitation);
            }
            if(team.getSId2() != 0){
                invitation.setNotifyId(String.valueOf(team.getSId2()));
                invitation.setInvitationTime(new Date());
                invitationService.createInvitation(invitation);
            }
            if(team.getSId3() != 0){
                invitation.setNotifyId(String.valueOf(team.getSId3()));
                invitation.setInvitationTime(new Date());
                invitationService.createInvitation(invitation);
            }
            if(team.getSId4() != 0){
                invitation.setNotifyId(String.valueOf(team.getSId4()));
                invitation.setInvitationTime(new Date());
                invitationService.createInvitation(invitation);
            }

            return R.success("apply success");
        }

        return R.error("Unknown error");

    }

    @RequestMapping("/approveInviation")
    @ResponseBody
    public R<String> approveInvitation(@RequestBody Invitation invitation) {
        String invitedTeamId = invitation.getInvitedTeamId();
        if(teamServiceImp.getById(invitedTeamId) == null){
            return R.error("related team dismissed");
        }
        Student invitor = studentServiceImp.getById(invitation.getInviterId());
        int invitedStudentId = Integer.parseInt(invitation.getInvitedId());
        //change team status
        List<Team> teams = teamServiceImp.getByStd(invitor.getId());
        if (!teams.isEmpty()) {//可能发生
            Team team = teams.get(0);//unique
            if (team.getSId1() == 0) {
                team.setSId1(invitedStudentId);
            } else if (team.getSId2() == 0) {
                team.setSId2(invitedStudentId);
            } else if (team.getSId3() == 0) {
                team.setSId3(invitedStudentId);
            } else if (team.getSId4() == 0) {
                team.setSId4(invitedStudentId);
            } else {
                return R.error("team is full");//不可能发生
            }
            teamServiceImp.updateById(team);
        } else {
            return R.error("no related team exists");//不可能发生
        }
        //change invited student status
        Student invitedStudent = studentServiceImp.getById(invitation.getInvitedId());
        if (invitedStudent != null) {
            invitedStudent.setGroupStatus(1);
            invitedStudent.setStatus(1);
            invitedStudent.setGroupId(invitor.getGroupId());
            studentServiceImp.updateById(invitedStudent);
        }
        return R.success("join success");
    }

    @RequestMapping("/approveApplicant")
    @ResponseBody
    public R<String> approveApplicant(@RequestBody Invitation invitation){
        String invitedTeamId = invitation.getInvitedTeamId();
        if(teamServiceImp.getById(invitedTeamId) == null){
            return R.error("Message expiration !");
        }


        String applicantId = invitation.getApplicantId();
        Student applicantStudent = studentServiceImp.getById(applicantId);
        if (applicantStudent != null) {
            //change group status
            Team team = teamServiceImp.getById(invitation.getInvitedTeamId());
            if (team.getSId1() == 0) {
                team.setSId1(applicantStudent.getId());
            } else if (team.getSId2() == 0) {
                team.setSId2(applicantStudent.getId());
            } else if (team.getSId3() == 0) {
                team.setSId3(applicantStudent.getId());
            } else if (team.getSId4() == 0) {
                team.setSId4(applicantStudent.getId());
            } else {
                return R.error("Your team is full");//可能发生
            }
            teamServiceImp.updateById(team);
            //change student status
            applicantStudent.setGroupStatus(1);
            applicantStudent.setStatus(1);
            applicantStudent.setGroupId(Integer.parseInt(invitation.getInvitedTeamId()));
            studentServiceImp.updateById(applicantStudent);
        }

        return R.success("approve Apply");
    }


    @RequestMapping("/getInvitations")
    @ResponseBody
    public R<List<InvitationDto>> getInvitations(String Id) { //notifyId or invitedId
        List<Invitation> invitations = invitationService.getInvitations(Id);
        ArrayList<InvitationDto> invitationDtos = new ArrayList<>();



        if (!invitations.isEmpty()) {
            for (Invitation invitation : invitations) {
                Date date = invitation.getInvitationTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String newDate = simpleDateFormat.format(date);
                invitation.setInvitationStringTime(newDate);

                InvitationDto invitationDto = new InvitationDto();
                BeanUtils.copyProperties(invitation, invitationDto);
                Integer inviterId = Integer.parseInt(invitation.getInviterId());
                Integer applicantId = Integer.parseInt(invitation.getApplicantId());
                if(inviterId != 0){
                    String firstname = studentServiceImp.getById(inviterId).getFirstname();
                    String surname = studentServiceImp.getById(inviterId).getSurname();
                    invitationDto.setStudentName(firstname + ' ' + surname);
                }
                if (applicantId != 0) {
                    String firstname = studentServiceImp.getById(applicantId).getFirstname();
                    String surname = studentServiceImp.getById(applicantId).getSurname();
                    invitationDto.setStudentName(firstname + ' ' + surname);
                }
                invitationDtos.add(invitationDto);

            }
            return R.success(invitationDtos);
        } else {
            return R.error("no invited message");
        }


    }

    @RequestMapping("/updateInvitation")
    @ResponseBody
    public R<String> updateInvitation(@RequestBody Invitation invitation) {
        if (invitationService.updateInvitation(invitation) == 1) {
            if (invitation.getApproved().equals("1")) {
                if (invitation.getInvitedTeamId() != "") {
                    Team team = teamServiceImp.getById(Integer.parseInt(invitation.getInvitedTeamId()));
                    if (team.getSId1() == null || team.getSId1() == 0) {
                        team.setSId1(Integer.parseInt(invitation.getInvitedId()));
                        if (teamServiceImp.updateById(team)) {
                            return R.success(invitation.getInvitedTeamId());
                        } else {
                            return R.error("500");
                        }
                    } else if (team.getSId2() == null || team.getSId2() == 0) {
                        team.setSId2(Integer.parseInt(invitation.getInvitedId()));
                        if (teamServiceImp.updateById(team)) {
                            return R.success(invitation.getInvitedTeamId());
                        } else {
                            return R.error("500");
                        }
                    } else if (team.getSId3() == null || team.getSId3() == 0) {
                        team.setSId3(Integer.parseInt(invitation.getInvitedId()));
                        if (teamServiceImp.updateById(team)) {
                            return R.success(invitation.getInvitedTeamId());
                        } else {
                            return R.error("500");
                        }
                    } else if (team.getSId4() == null || team.getSId4() == 0) {
                        team.setSId4(Integer.parseInt(invitation.getInvitedId()));
                        if (teamServiceImp.updateById(team)) {
                            return R.success(invitation.getInvitedTeamId());
                        } else {
                            return R.error("500");
                        }
                    }
                    return R.error("Team Full");
                } else {
                    Team team = new Team();
                    team.setSId1(Integer.parseInt(invitation.getInviterId()));
                    team.setSId2(Integer.parseInt(invitation.getInvitedId()));
                    if (teamServiceImp.save(team)) {
                        return R.success(String.valueOf(teamServiceImp.getTeamIdByStudentID("StudentID1", Integer.parseInt(invitation.getInviterId())).getId()));
                    }
                }
            }
            return R.success("1");
        }
        return R.error("500");
    }



    @RequestMapping("/getUnreadMessage")
    @ResponseBody
    public R<List<Invitation>> getUnreadMessage(String Id) { //notifyId or invitedId
        LambdaQueryWrapper<Invitation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Invitation::getInvitedId,Id).eq(Invitation::getStatus,0).or();
        queryWrapper.eq(Invitation::getNotifyId,Id).eq(Invitation::getStatus,0);

        List<Invitation> invitations = invitationService.list(queryWrapper);

        if (!invitations.isEmpty()) {
            for (Invitation invitation : invitations) {
                Date date = invitation.getInvitationTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String newDate = simpleDateFormat.format(date);
                invitation.setInvitationStringTime(newDate);
            }
            return R.success(invitations);
        } else {
            return R.error("no invited message");
        }


    }

    @RequestMapping("/updateInvitationStatus")
    @ResponseBody
    public R<String> updateInvitationStatus(@RequestBody Invitation invitation){
        invitation.setStatus(1);
        invitationService.updateById(invitation);
        return R.success("update success");
    }
}
