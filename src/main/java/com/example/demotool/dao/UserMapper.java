package com.example.demotool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demotool.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM tbUser WHERE strMobile = #{strMobile}")
    List<User> queryByStrMobile(String strMobile);
}
