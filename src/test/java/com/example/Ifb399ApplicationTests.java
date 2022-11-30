package com.example;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.StaffDao;
import com.example.dao.StudentDao;
import com.example.domain.Invitation;
import com.example.domain.Staff;
import com.example.domain.Student;
import com.example.service.Imp.InvitationServicesImp;
import com.example.service.Imp.StudentServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Ifb399ApplicationTests {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentServiceImp studentServiceImp;
    @Autowired
    private InvitationServicesImp invitationServicesImp;
    @Autowired
    private StaffDao staffDao;

    @Test
    void contextLoads() {
        System.out.println(studentDao.getUserByInfo("Phoebe", "123"));
    }

    @Test
    void testImp(){
        Student student = studentServiceImp.studentLogin("Phoebe", "123");
        System.out.println(student);
    }

    @Test
    void testGetByName(){
        System.out.println(studentServiceImp.studentGetByName("Tom"));
    }

    @Test
    void testEditSelf(){
        Student student = new Student();
        student.setSkills("eat11");
        student.setId(1008);
        student.setMinor("Computer");
        studentServiceImp.studentEditSelf(student);
    }

    @Test
    void testStudentList(){
        List<Student> students = studentServiceImp.studentList();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    void testStudentPagination(){
        Page page = new Page(2,3);
        studentServiceImp.page(page);
        System.out.println(page);
    }

    @Test
    void testStudentGetById(){
        System.out.println(studentServiceImp.getById(11150002));
    }


    @Test
    void testgetStudentsByManyIds(){
        List list = new ArrayList();
        list.add(1001);
        list.add(1002);
        list.add(1003);
        list.add(1004);
        List studedntList = studentServiceImp.getStuDetailsByStuIds(list);
        for (Object o : studedntList) {
            System.out.println(o);
        }

    }

    @Test
    void testInvitedTest(){
        List<Invitation> invitations = invitationServicesImp.getInvitations(String.valueOf(1002));
        System.out.println(invitations);
    }

    @Test
    void staffTest(){
        Staff staff = staffDao.selectById(1);
        System.out.println(staff);
    }



}
