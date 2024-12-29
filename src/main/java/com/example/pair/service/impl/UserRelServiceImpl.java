package com.example.pair.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pair.entity.UserRel;
import com.example.pair.mapper.UserRelMapper;
import com.example.pair.service.UserRelService;
import org.springframework.stereotype.Service;

@Service
public class UserRelServiceImpl extends ServiceImpl<UserRelMapper, UserRel> implements UserRelService {
}
