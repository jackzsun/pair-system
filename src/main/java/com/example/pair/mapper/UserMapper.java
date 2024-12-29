package com.example.pair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pair.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
