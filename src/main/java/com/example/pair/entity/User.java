package com.example.pair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@TableName("user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -990069338543593656L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @NotBlank
    private String number;
    private String name;
    private String phone;
    //0 female, 1 male;
    @Max(value = 1)
    @Min(value = 0)
    @NotNull
    private Integer gender;
}
