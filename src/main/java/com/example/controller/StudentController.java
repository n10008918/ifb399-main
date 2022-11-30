package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Student;
import com.example.service.Imp.StudentServiceImp;
import com.example.util.GetSessionUtil;
import com.example.util.SetSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentServiceImp studentServiceImp;

    @RequestMapping("/register")
    @ResponseBody
    public R<Student> studentRegister(@RequestBody Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FirstName",student.getFirstname());
        Student one = studentServiceImp.getOne(queryWrapper);
        if(one != null){
            return R.error("user exists");
        }else{
            student.setGroupId(0);
            student.setGroupStatus(0);
            studentServiceImp.save(student);
            return R.success(student);
        }

    }

    @RequestMapping("/login")
    @ResponseBody
    public R<Student> studentLogin(@RequestBody Student student, HttpServletRequest servletRequest) {
        Student stu = studentServiceImp.studentLogin(student.getFirstname(), student.getPassword());
        if (stu != null) {
            SetSessionUtil.setSession(servletRequest,"studentSession",stu.getSurname());
            return R.success(stu);
        } else return R.error("invalid info");
    }

    @RequestMapping("/UserValidate")
    @ResponseBody
    public R<Student> UserValidate(String userEmail, HttpServletRequest servletRequest){
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getEmail, userEmail);
        Student student = studentServiceImp.getOne(queryWrapper);


        if(student != null){
            SetSessionUtil.setSession(servletRequest,"studentSession",student.getSurname());
            String studentSession = GetSessionUtil.getSession(servletRequest, "studentSession");
        }


        return student == null ? R.error("No one"): R.success(student);

    }

    @RequestMapping("/logout")
    @ResponseBody
    public R<String> studentLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("studentSession");
//        System.out.println("sessionValue:" + GetSessionUtil.getSession(request, "studentSession"));
        return R.success("exit success");
    }


    @RequestMapping("/editInfo")
    @ResponseBody
    public R<Student> studentEditInfo(@RequestBody Student student) {
        studentServiceImp.studentEditSelf(student);
        return R.success(student);
    }

    @RequestMapping("/showInfo")
    @ResponseBody
    public R<List<Student>> studentShowInfo() {
        List<Student> students = studentServiceImp.studentList();
        for (Student student : students) {
            System.out.println(student);
        }
        return R.success(students);
    }

    @RequestMapping("/pagination")
    @ResponseBody
    public R<Page> studentPagination(Integer currentPage, Integer pageSize, String searchMethod, String specificId, @RequestParam List<String> checkList, @RequestBody Student student) {

        Page page = new Page(currentPage, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (specificId.trim().length() < 1) {//search by studentID function does not work
            /*where method radio has value
             * '0' means no team, '1'means having a team*/
            if (searchMethod.equals("Group")) {
                queryWrapper.eq("group_Status", 1);
            } else if (searchMethod.equals("Member")) {
                queryWrapper.eq("group_Status", 0);
            }

            /*whether skillsForm checkList is empty*/
            if (!checkList.isEmpty()) {
                for (String s : checkList) {
                    queryWrapper.like("skills", s);
                }
            }
            /*whether major radio has value*/
            if ("All".equals(student.getMajor())) {//never work
                studentServiceImp.page(page, queryWrapper);
            } else {
                queryWrapper.eq("Major", student.getMajor());
                studentServiceImp.page(page, queryWrapper);
            }
        } else {//search by studentID works
            Student student1 = studentServiceImp.getById(specificId);
            queryWrapper.eq("id", specificId.trim());
            studentServiceImp.page(page, queryWrapper);
        }

        return R.success(page);


    }

    @RequestMapping("/pagination2")
    @ResponseBody
    public R<Page> studentPagination2(Integer currentPage, Integer pageSize) {
        Page page = new Page(currentPage, pageSize);
        studentServiceImp.page(page,null);
        return R.success(page);

    }

    /*MyGroup.html Function is to get student's group status*/
    @RequestMapping("/getStudentById")
    @ResponseBody
    public R<Student> getStudentById(Integer SId) {
        Student student = studentServiceImp.getById(SId);
        return R.success(student);
    }

    @RequestMapping("/getStudentByIdThroughForm")
    @ResponseBody
    public R<Student> getStudentByIdThroughForm(Integer SId) {
        Student student = studentServiceImp.getById(SId);
        student.setStatus(2);
        studentServiceImp.updateById(student);//
        return R.success(student);
    }

    @RequestMapping("/getStudentByIdThroughJoin")
    @ResponseBody
    public R<Student> getStudentByIdThroughJoin(Integer SId){
        Student student = studentServiceImp.getById(SId);
        student.setStatus(1);
        studentServiceImp.updateById(student);//
        return R.success(student);
    }

    /*MyGroup.html  Function is to display 4 Members Details*/
    @RequestMapping("/getGroupDetails")
    @ResponseBody
    public R<List<Student>> getGroupDetails(String stuId1, String stuId2, String stuId3, String stuId4) {
        Student stu1 = studentServiceImp.getById(stuId1);
        Student stu2 = studentServiceImp.getById(stuId2);
        Student stu3 = studentServiceImp.getById(stuId3);
        Student stu4 = studentServiceImp.getById(stuId4);
        ArrayList<Student> groupList = new ArrayList<>();
        if(stu1 != null){
            groupList.add(stu1);
        }
        if(stu2 != null){
            groupList.add(stu2);
        }
        if(stu3 != null){
            groupList.add(stu3);
        }
        if(stu4 != null){
            groupList.add(stu4);
        }

        return R.success(groupList);
    }

}
