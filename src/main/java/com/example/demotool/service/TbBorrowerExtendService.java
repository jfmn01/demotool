package com.example.demotool.service;

import com.example.demotool.entity.TbBorrowers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

public class TbBorrowerExtendService {

    private JdbcTemplate jdbcTemplate;

    public int countRow(Long lUserId) {
        Long tableId = lUserId / 1000000;
        String sql = "select count(1) countRow from tbBorrowerExtends"+tableId+" where lUserId = "+ lUserId;
        List<TbBorrowers> tbBorrowers = jdbcTemplate.queryForList(sql, TbBorrowers.class);
        return tbBorrowers.size();
    }
}
