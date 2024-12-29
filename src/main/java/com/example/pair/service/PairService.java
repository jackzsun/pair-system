package com.example.pair.service;

import com.example.pair.entity.User;
import com.example.pair.entity.UserDTO;

import java.util.List;

public interface PairService {
    void save(UserDTO userDTO);

//    void updateById(User user);
    
    List<User> viewInterestUsers(User user, Integer queryType);

    List<User> viewBothInterest(User user);
}
