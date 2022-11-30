package com.example.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.StaffDao;
import com.example.domain.Staff;
import com.example.service.StaffService;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImp extends ServiceImpl<StaffDao, Staff> implements StaffService {
}
