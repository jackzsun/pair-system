package com.example.pair.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserDTO extends User {
    @Serial
    private static final long serialVersionUID = -3313832193327657269L;

    private List<String> interestNos;
    //1 对别人感兴趣， 2 对我感兴趣
    private Integer queryType;
}
