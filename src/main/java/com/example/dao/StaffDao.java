package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Staff;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffDao extends BaseMapper<Staff> {
}
