package com.example.pair.controller;

import com.example.pair.constant.Constant;
import com.example.pair.entity.User;
import com.example.pair.entity.UserDTO;
import com.example.pair.service.PairService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "配对控制器")
@RestController
@RequestMapping("/pair")
public class PairController {

    @Autowired
    private PairService pairService;

    //新增用户及感兴趣的信息
    @Operation(summary = "新增用户及感兴趣的信息")
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody @Validated UserDTO userDTO) {
        pairService.save(userDTO);
        return ResponseEntity.ok("保存用户及感兴趣的信息成功");
    }

//    //更新用户
//    @Operation(summary = "更新用户")
//    @PostMapping("/updateUser")
//    public ResponseEntity<String> updateUser(@RequestBody User user) {
//        pairService.updateById(user);
//        return ResponseEntity.ok("更新用户成功");
//    }


    //查看你感兴趣的用户的信息
    @Operation(summary = "查看你感兴趣的已注册的异性用户的信息")
    @PostMapping("/viewInterestUsers")
    public ResponseEntity<List<User>> viewInterestUsers(@RequestBody User user) {
        return ResponseEntity.ok(pairService.viewInterestUsers(user, Constant.YOUR_INTEREST));
    }

    //查看对你感兴趣的用户的信息
    @Operation(summary = "查看对你感兴趣的已注册的异性用户的信息")
    @PostMapping("/viewInterestInYou")
    public ResponseEntity<List<User>> viewInterestInYou(@RequestBody User user) {
        return ResponseEntity.ok(pairService.viewInterestUsers(user, Constant.INTERESTED_IN_YOU));
    }

    //仅查看你感兴趣且对你感兴趣的用户的信息
    @Operation(summary = "仅查看你感兴趣且对你感兴趣已注册的用户的信息")
    @PostMapping("/viewBothInterest")
    public ResponseEntity<List<User>> viewBothInterest(@RequestBody User user) {
        return ResponseEntity.ok(pairService.viewBothInterest(user));
    }

}
