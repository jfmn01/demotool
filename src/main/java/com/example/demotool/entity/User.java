package com.example.demotool.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbUser")
public class User {
    private Long lId;
    private String strName;
    private String strMobile;
    private String strIdentity;
}
