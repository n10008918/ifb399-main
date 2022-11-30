package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Student;
import com.example.domain.Team;
import com.example.domain.TeamDto;
import com.example.service.Imp.StudentServiceImp;
import com.example.service.Imp.TeamServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/team")
@Slf4j
public class TeamController {
    @Autowired
    private TeamServiceImp teamServiceImp;
    @Autowired
    private StudentServiceImp studentServiceImp;

    @RequestMapping("/create")
    @ResponseBody
    public R<Student> createTeam(Integer studentID) {
        //update team status
        teamServiceImp.save(new Team(studentID,0,0,0));
        Team team = teamServiceImp.getTeamIdByStudentID("StudentID1", studentID);
        int TId = team.getId();

        //update student status
        Student student = studentServiceImp.getById(studentID);
        student.setGroupStatus(1);
        student.setStatus(2);
        student.setGroupId(TId);
        studentServiceImp.updateById(student);
        return R.success(student);
    }

    /*for 'MyGroup.html', the function is to display the team info where the student has */
    @RequestMapping("/getTeamInfo")
    @ResponseBody
    public R<List<TeamDto>> getTeamInfo(Integer studentId) {
        List<Team> teamList = teamServiceImp.getByStd(studentId);
        ArrayList<TeamDto> teamDtoArrayList = new ArrayList<>();
        BeanUtils.copyProperties(teamList, teamDtoArrayList);

        for (Team team : teamList) {
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(team, teamDto);
            if(team.getSId1() != 0){
                teamDto.setStudentName1(studentServiceImp.getById(team.getSId1()).getFirstname());
            }
            if(team.getSId2() != 0){
                teamDto.setStudentName2(studentServiceImp.getById(team.getSId2()).getFirstname());
            }
            if(team.getSId3() != 0){
                teamDto.setStudentName3(studentServiceImp.getById(team.getSId3()).getFirstname());
            }
            if(team.getSId4() != 0){
                teamDto.setStudentName4(studentServiceImp.getById(team.getSId4()).getFirstname());
            }
            teamDtoArrayList.add(teamDto);
        }
        return R.success(teamDtoArrayList);
    }

    @RequestMapping("/withdraw")
    @ResponseBody
    public R<Team> withDraw(@RequestBody Student student) {
        student = studentServiceImp.getById(student.getId());// It should have been deleted, but for kick member function it must exist
        /*update student detail(groupStatus,groupId)*/
        Team team = teamServiceImp.getById(student.getGroupId());
        teamServiceImp.UpdateGroupMember(team, student.getId());

        /*update team detail(reduce this guy)*/
        student.setGroupStatus(0);
        student.setGroupId(0);
        student.setStatus(0);
        studentServiceImp.updateById(student);

        /*delete this team(If this team is empty after last student withdraw)*/
        if ( (team.getSId1() == null || team.getSId1() == 0) &&
             (team.getSId2() == null || team.getSId2() == 0) &&
             (team.getSId3() == null || team.getSId3() == 0) &&
             (team.getSId4() == null || team.getSId4() == 0)){
            teamServiceImp.removeById(team);
        }

        return R.success(team);

    }

    /*for 'Search.html', the function is to display all teams' info */
    @RequestMapping("/pagination")
    @ResponseBody
    public R<Page> getAllTeamsInfo(Integer TeamsCurrentPage, Integer TeamsPageSize, String specificId){
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();

        Page<Team> page = new Page<>(TeamsCurrentPage, TeamsPageSize);
        Page<TeamDto> dtoPage = new Page<>(TeamsCurrentPage, TeamsPageSize);
        if(specificId.trim().length() >= 1){
            queryWrapper.eq("id",specificId.trim());
        }
        teamServiceImp.page(page,queryWrapper);


        BeanUtils.copyProperties(page, dtoPage, "records");
        List<Team> records = page.getRecords();
        ArrayList<TeamDto> teamDtoArrayList = new ArrayList<>();
        for (Team record : records) {
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(record, teamDto);

            Student stu1 = studentServiceImp.getById(record.getSId1());
            if(stu1 != null){
                teamDto.setStudentName1(stu1.getFirstname() + " " +stu1.getSurname());
            }else{
                teamDto.setStudentName1("");
            }

            Student stu2 = studentServiceImp.getById(record.getSId2());
            if(stu2 != null){
                teamDto.setStudentName2(stu2.getFirstname() + " " +stu2.getSurname());
            }else{
                teamDto.setStudentName2("");
            }

            Student stu3 = studentServiceImp.getById(record.getSId3());
            if(stu3 != null){
                teamDto.setStudentName3(stu3.getFirstname() + " " +stu3.getSurname());
            }else{
                teamDto.setStudentName3("");
            }

            Student stu4 = studentServiceImp.getById(record.getSId4());
            if(stu4 != null){
                teamDto.setStudentName4(stu4.getFirstname() + " " +stu4.getSurname());
            }else{
                teamDto.setStudentName4("");
            }
            teamDtoArrayList.add(teamDto);

        }

        dtoPage.setRecords(teamDtoArrayList);
        return R.success(dtoPage);
    }

    @RequestMapping("/pagination2")
    @ResponseBody
    public R<Page> getAllTeamsInfo2(Integer TeamsCurrentPage, Integer TeamsPageSize){
        Page<Team> pageInfo = new Page<>(TeamsCurrentPage, TeamsPageSize);
        Page<TeamDto> dtoPageInfo = new Page<>();
        teamServiceImp.page(pageInfo,null);

        ArrayList<TeamDto> teamDtoArrayList = new ArrayList<>();//new records
        BeanUtils.copyProperties(pageInfo, dtoPageInfo, "records");//copy pageInfo

        List<Team> records = pageInfo.getRecords();
        for (Team record : records) {
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(record, teamDto);

            Student student1 = studentServiceImp.getById(record.getSId1());
            if(student1 != null){
                String studentName = student1.getFirstname();
                teamDto.setStudentName1(studentName);
            }else{
                teamDto.setStudentName1("(Empty)");
            }

            Student student2 = studentServiceImp.getById(record.getSId2());
            if(student2 != null){
                String studentName = student2.getFirstname();
                teamDto.setStudentName2(studentName);
            }else{
                teamDto.setStudentName2("(Empty)");
            }

            Student student3 = studentServiceImp.getById(record.getSId3());
            if(student3 != null){
                String studentName = student3.getFirstname();
                teamDto.setStudentName3(studentName);
            }else{
                teamDto.setStudentName3("(Empty)");
            }

            Student student4 = studentServiceImp.getById(record.getSId4());
            if(student4 != null){
                String studentName = student4.getFirstname();
                teamDto.setStudentName4(studentName);
            }
            else{
                teamDto.setStudentName4("(Empty)");
            }
            teamDtoArrayList.add(teamDto);//new records

        }

        dtoPageInfo.setRecords(teamDtoArrayList);
        return R.success(dtoPageInfo);
    }

    @RequestMapping("/editTeamInfo")
    @ResponseBody
    public R<Team> editTeamInfo(@RequestBody Team team){
        teamServiceImp.updateById(team);
        return R.success(team);
    }


}
