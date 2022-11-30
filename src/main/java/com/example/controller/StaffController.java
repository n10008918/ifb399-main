package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.domain.Staff;
import com.example.service.Imp.StaffServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffServiceImp staffServiceImp;

   @GetMapping
    public R<String> StaffLogin(String email, String password){
       QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
       try{
//           int staffId = Integer.parseInt(id);
           queryWrapper.eq("Email",email).eq("password",password);
           Staff staff = staffServiceImp.getOne(queryWrapper);
           if(staff == null){
               return R.error("no exist");
           }
       }catch (Exception e){
           return R.error("ClassCastException");
       }

       return R.success("login success");
   }
}
