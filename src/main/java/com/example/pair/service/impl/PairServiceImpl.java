package com.example.pair.service.impl;

import com.example.pair.constant.Constant;
import com.example.pair.entity.User;
import com.example.pair.entity.UserDTO;
import com.example.pair.entity.UserRel;
import com.example.pair.service.PairService;
import com.example.pair.service.UserRelService;
import com.example.pair.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class PairServiceImpl implements PairService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRelService userRelService;



    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userService.save(user);
        LocalDateTime createTime = LocalDateTime.now();
        if (!CollectionUtils.isEmpty(userDTO.getInterestNos())) {
            List<UserRel> userRels = userDTO.getInterestNos().stream().map(no -> {
                UserRel userRel = new UserRel();
                userRel.setFromNo(userDTO.getNumber());
                userRel.setFromGender(userDTO.getGender());
                userRel.setToNo(no);
                userRel.setToGender(1 - userDTO.getGender());
                userRel.setCreateTime(createTime);
                return userRel;
            }).toList();
            userRelService.saveBatch(userRels);
        }
    }

//    @Override
//    public void updateById(User user) {
//        userService.updateById(user);
//    }

    @Override
    public List<User> viewInterestUsers(User user, Integer queryType) {
        List<UserRel> list = null;
        if (Objects.equals(queryType, Constant.YOUR_INTEREST)) {
            list = userRelService.lambdaQuery().eq(UserRel::getFromNo, user.getNumber()).eq(UserRel::getFromGender, user.getGender()).list();
        } else if (Objects.equals(queryType, Constant.INTERESTED_IN_YOU)) {
            list = userRelService.lambdaQuery().eq(UserRel::getToNo, user.getNumber()).eq(UserRel::getToGender, user.getGender()).list();
        }

        if (!CollectionUtils.isEmpty(list)) {
            if (Objects.equals(queryType, Constant.YOUR_INTEREST)) {
                List<String> toNos = list.stream().map(UserRel::getToNo).toList();
                return userService.lambdaQuery().in(User::getNumber, toNos).eq(User::getGender, 1 - user.getGender()).orderByAsc(User::getNumber).list();
            } else if (Objects.equals(queryType, Constant.INTERESTED_IN_YOU)) {
                List<String> fromNos = list.stream().map(UserRel::getFromNo).toList();
                return userService.lambdaQuery().in(User::getNumber, fromNos).eq(User::getGender, 1 - user.getGender()).orderByAsc(User::getNumber).list();
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<User> viewBothInterest(User user) {
        List<UserRel> fromList = userRelService.lambdaQuery().eq(UserRel::getFromNo, user.getNumber()).eq(UserRel::getFromGender, user.getGender()).list();
        List<UserRel> toList = userRelService.lambdaQuery().eq(UserRel::getToNo, user.getNumber()).eq(UserRel::getToGender, user.getGender()).list();
        if (!CollectionUtils.isEmpty(fromList) && !CollectionUtils.isEmpty(toList)) {
            List<String> toNos = fromList.stream().map(UserRel::getToNo).toList();
            List<String> fromNos = toList.stream().map(UserRel::getFromNo).toList();

            HashSet<String> toNoSet = new HashSet<>(toNos);
            List<String> filtered = fromNos.stream().filter(toNoSet::contains).toList();
            return userService.lambdaQuery().in(User::getNumber, filtered).eq(User::getGender, 1 - user.getGender()).orderByAsc(User::getNumber).list();
        }
        return List.of();
    }

}
