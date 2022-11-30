package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Team;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamDao extends BaseMapper<Team> {
}
