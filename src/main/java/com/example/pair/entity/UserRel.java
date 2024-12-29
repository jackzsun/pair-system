package com.example.pair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user_rel")
public class UserRel implements Serializable {
    @Serial
    private static final long serialVersionUID = -6219314369564459999L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String fromNo;
    private Integer fromGender;
    private String toNo;
    private Integer toGender;
    private LocalDateTime createTime;
}
